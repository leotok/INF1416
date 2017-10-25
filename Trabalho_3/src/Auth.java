import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.xml.bind.DatatypeConverter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;


public class Auth {
	
//	TODO:
//	- TAN list
	
	public static boolean verificaArvoreSenha(Node root, HashMap user, String senhaFormada) {
		if (root.dir == null && root.esq == null) {
//			System.out.println(senhaFormada);
			return Auth.autenticaSenha(senhaFormada, user);
		}
		boolean ret1 = verificaArvoreSenha(root.esq, user, senhaFormada + root.esq.opcao);
		boolean ret2 = verificaArvoreSenha(root.dir, user, senhaFormada + root.dir.opcao);
		
		return ret1 || ret2;
	}
	
	public static boolean acessarArquivo(HashMap user, String index, String nomeArquivo, PrivateKey chavePrivada, String pastaArquivos) {
		try {
			String[] linhasIndex = index.split("\n");
			for (String linha: linhasIndex) {
				String[] params = linha.split(" ");
				String nomeSecreto = params[1];
				
				if (nomeSecreto.equals(nomeArquivo)) {
					String email = params[2];
					String grupo = params[3];
					if (user.get("email").equals(email) || user.get("groupName").equals(grupo)) {
						String nomeCodigoArquivo = params[0];
						byte[] conteudoArquivo = Auth.decriptaArquivo(user, pastaArquivos, nomeCodigoArquivo, chavePrivada);
						Files.write(Paths.get(pastaArquivos + "/" + nomeSecreto), conteudoArquivo);
						return true;
					}
					else {
						return false;
					}
				}
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	public static byte[] decriptaArquivo(HashMap user, String caminho, String filename, PrivateKey chavePrivada) {
		try {
			
			byte[] arqEnv = Files.readAllBytes(Paths.get(caminho + "/" + filename + ".env"));
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
			cipher.update(arqEnv);
			
			byte [] semente = cipher.doFinal();
			
			byte[] arqEnc = Files.readAllBytes(Paths.get(caminho + "/" + filename + ".enc"));			
			SecureRandom rand = SecureRandom.getInstance("SHA1PRNG", "SUN");
			rand.setSeed(semente);
			
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			keyGen.init(56, rand);
			Key chaveSecreta = keyGen.generateKey();
			
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, chaveSecreta);
			byte[] index = cipher.doFinal(arqEnc);
			
			X509Certificate cert = Auth.leCertificadoDigital(((String) user.get("certificado")).getBytes());
			Signature assinatura = Signature.getInstance("MD5withRSA");
			assinatura.initVerify(cert.getPublicKey());
			assinatura.update(index);
			
			byte[] arqAsd = Files.readAllBytes(Paths.get(caminho + "/" + filename + ".asd"));
			if (assinatura.verify(arqAsd) == false) {
				System.out.println(filename + " pode ter sido adulterado");
				DBManager.insereRegistro(8005, (String) user.get("email"));
				return null;
			}
			else {
				System.out.println("Decriptou index ok");
				return index;
			}
		} 
		catch (Exception IOError) {
			DBManager.insereRegistro(8008, (String) user.get("email"));
			return null;
		}
	}
	
	public static PrivateKey leChavePrivada(String fraseSecreta, String pathString, HashMap user) {
		try {			
			SecureRandom rand = SecureRandom.getInstance("SHA1PRNG", "SUN");
			rand.setSeed(fraseSecreta.getBytes());
			
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			keyGen.init(56, rand);
			Key chave = keyGen.generateKey();
			
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			try {
				cipher.init(Cipher.DECRYPT_MODE, chave);
			}
			catch (Exception e) {
				DBManager.insereRegistro(8004, (String) user.get("email"));
				return null;
			}
			
			byte[] bytes = null;
			try {
				Path path = Paths.get(pathString);
				bytes = Files.readAllBytes(path);
			}
			catch (Exception e) {
				DBManager.insereRegistro(8003, (String) user.get("email"));
				return null;
			}
			
			String chavePrivadaBase64 = new String(cipher.doFinal(bytes), "UTF8");
			chavePrivadaBase64 = chavePrivadaBase64.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").trim();
			byte[] chavePrivadaBytes = DatatypeConverter.parseBase64Binary(chavePrivadaBase64);
			
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return factory.generatePrivate(new PKCS8EncodedKeySpec(chavePrivadaBytes));
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean testaChavePrivada(PrivateKey chavePrivada, HashMap user) {
		try {
			byte[] teste = new byte[1024];
			SecureRandom.getInstanceStrong().nextBytes(teste);
			Signature assinatura = Signature.getInstance("MD5withRSA");
			assinatura.initSign(chavePrivada);
			assinatura.update(teste);
			byte[] resp = assinatura.sign();
			
			PublicKey chavePublica = Auth.leCertificadoDigital(((String) user.get("certificado")).getBytes()).getPublicKey();
			assinatura.initVerify(chavePublica);
			assinatura.update(teste);
			
			if (assinatura.verify(resp)) {
				System.out.println("Chave válida!");
				return true;
			}
			else {
				DBManager.incrementaNumChavePrivadaErrada((String) user.get("email"));
				System.out.println("Chave rejeitada!");
				return false;
			}
		}
		catch (Exception e) {
			System.out.println("Erro ao testar chave privada");
			return false;
		}
	}
	
	public static X509Certificate leCertificadoDigital(byte[] bytes) {
		try {
			
			InputStream stream = new ByteArrayInputStream(bytes);
			CertificateFactory factory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) factory.generateCertificate(stream);
			stream.close();
			return cert;
		} 
		catch (IOException | CertificateException e) {
			System.out.println("Certificado digital inválido");
			return null;
		}
	}
	
	public static String certToString(X509Certificate cert) {
	    StringWriter sw = new StringWriter();
	    try {
	        sw.write("-----BEGIN CERTIFICATE-----\n");
	        sw.write(DatatypeConverter.printBase64Binary(cert.getEncoded()).replaceAll("(.{64})", "$1\n"));
	        sw.write("\n-----END CERTIFICATE-----\n");
	    } catch (CertificateEncodingException e) {
	        e.printStackTrace();
	    }
	    return sw.toString();
	}
	
	
	public static boolean cadastraUsuario(String grupo, String senha, String pathCert, String pathTanList) {
		if (Auth.verificaRegrasSenha(senha) == false)
			return false;
		
		Path cdPath = Paths.get(pathCert);
		byte[] certDigBytes = null;
		try {
			certDigBytes = Files.readAllBytes(cdPath);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		X509Certificate cert = leCertificadoDigital(certDigBytes);
		String subjectDN = cert.getSubjectDN().getName();
		int start = subjectDN.indexOf("=");
		int end = subjectDN.indexOf(",");
		String email = subjectDN.substring(start + 1, end);
		
		start = subjectDN.indexOf("=", end);
		end = subjectDN.indexOf(",", start);
		String nome = subjectDN.substring(start + 1, end);
		
		String salt = Auth.geraSalt();
		String senhaProcessada = Auth.geraSenhaProcessada(senha, salt);    
		
		boolean ret = DBManager.addUser(nome, email, grupo, salt, senhaProcessada, certToString(cert));
		if (ret) {
			List<String> list = Auth.geraTanList(pathTanList, 10,  email);
			if (list == null) {
				DBManager.insereRegistro(6005, email);
				return false;
			}
		}
		return ret;
	}

	public static HashMap autenticaEmail(String email) {
		List<HashMap> list = null;
		try {
			list = DBManager.getUser(email);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (list.size() == 1)
			return list.get(0);
		return null;
	}
	
	public static boolean autenticaSenha(String senha, HashMap user)  {
		String senhaDigest = Auth.geraSenhaProcessada(senha, (String) user.get("salt"));
		if (user.get("passwordDigest").equals(senhaDigest))
			return true;
		return false;
	}
	
	public static List<String> geraTanList(String path, int num, String email) {
		try {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < num; i++) {
				String tan = Auth.geraTan();
				list.add(Integer.toString(i) +" "+ tan);
				DBManager.insereTan(tan, email, i);
			}
			Files.write(Paths.get(path + "/tanList.txt"), String.join("\n", list).getBytes());
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String geraTan() {
		String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
	    Random rand = new Random();
	    for (int i = 0; i < 5; i++) {
	        sb.append(candidateChars.charAt(rand.nextInt(candidateChars
	                .length())));
	    }
		return sb.toString();
	}
	
	public static String geraSenhaProcessada(String senha, String salt) {
		MessageDigest sha1 = null;
		try {
			sha1 = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Não encontrou algoritmo SHA1");
			return null;
		}
		sha1.update((senha + salt).getBytes());
		return Util.toHex(sha1.digest());
	}
	
	private static String geraSalt() {
		SecureRandom rand = new SecureRandom();
		StringBuffer salt = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			salt.append(new Integer(rand.nextInt(9)).toString());
		}
		return salt.toString();
	}
	
	public static boolean verificaRegrasSenha(String senha) {
		int len = senha.length();
		if (len < 6 || len > 8)
			return false;
		
		for (int i = 0; i < len; ++i) {
	        if (!Character.isDigit(senha.charAt(i))) {
	            return false;
	        }
	    }
		
		boolean crescente = true;
		boolean decrescente = true;
		
		for (int i = 0; i < len - 1; i++) {
			char c = senha.charAt(i);
			char cProx = senha.charAt(i+1);
			
			if (Character.getNumericValue(cProx) != Character.getNumericValue(c) + 1)
				crescente = false;
			if (Character.getNumericValue(cProx) != Character.getNumericValue(c) - 1)
				decrescente = false;
			if (cProx == c )
				return false;	
		}
		return (!crescente) && (!decrescente);
	}
}
