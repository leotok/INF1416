import java.util.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.*;
import java.util.HashMap;
import java.util.Map;


public class DigestCalculator {
	
	private class ArqInfo {
		private String hashMD5 = null;
		private String hashSHA1 = null;
		private String nomeArq = null;
	}

	private List<ArqInfo> infoListaDigest = new ArrayList<ArqInfo>();
	private MessageDigest messageDigest;
	private String tipoDigest;
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		if (args.length < 3) {
			System.err.println("Usage: java DigestCalulator Tipo_Digest Caminho_ArqListaDigest CaminhoArq1... CaminhoArqN");
			System.exit(1);
		}
		
		DigestCalculator calculator = new DigestCalculator();
		List<String> caminhosArqs = new ArrayList<String>();
		
		calculator.setTipoDigest(args[0]);
		calculator.setInfoListaDigest(args[1]);
		
		System.out.println(calculator.tipoDigest);
		
		for (int i = 2; i < args.length; i++) {
			System.out.println(args[i]);
			caminhosArqs.add(args[i]);
		}
		
		for (String arq: caminhosArqs) {
			String digest = calculator.calculaDigest(arq);
			if (digest != null) {
				calculator.procuraDigest(arq, digest);
			}
		}
		calculator.saveInfoToFile();

	}
	
	
	
	void saveInfoToFile() {
		System.out.println("\nSalvando arquivo:");
		String content = "";
		
		for (ArqInfo i: this.infoListaDigest) {
			System.out.println(i.nomeArq);
			System.out.println(i.hashMD5);
			System.out.println(i.hashSHA1);
			
			if (i.hashMD5 != null && i.hashSHA1 != null) {
				content += String.format("%s %s %s %s %s\n", i.nomeArq, "MD5", i.hashMD5, "SHA1", i.hashSHA1);
			}
			else if (i.hashMD5 != null) {
				content += String.format("%s %s %s\n", i.nomeArq, "MD5", i.hashMD5);
			}
			else if (i.hashSHA1 != null) {
				content += String.format("%s %s %s\n", i.nomeArq, "SHA1", i.hashSHA1);
			}
			else {
				System.out.println("Arquivo deve conter pelo menos um Hash!");
				System.exit(1);
			}
			
		}
		
		try {
			Files.write(Paths.get("listaDigests.txt"), content.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	String calculaDigest(String caminhoArq)  {
		List<String> content;
		try{
			content = Files.readAllLines(Paths.get(caminhoArq));	
		}
		catch (IOException e) {
			System.out.println(String.format("Failed to read file %s", caminhoArq));
			return null;
		}
		
		System.out.print("Conteudo:");
		
		for (String str: content) {
			System.out.println(str);
			this.messageDigest.update(str.getBytes());	
		}
		
		return toHex(this.messageDigest.digest());
		
	}

	boolean procuraDigest(String caminhoArq, String digest) {
		System.out.print("Procurando digest:");
		System.out.println(digest);
		
		ArqInfo info = buscaInfoPorNomeArq(caminhoArq);
		
		if (info != null) {
			// encontrou arquivo
			if ((this.tipoDigest.equals("MD5") && digest.equals(info.hashMD5)) || (this.tipoDigest.equals("SHA1") && digest.equals(info.hashSHA1))) {
				// STATUS OK - arquivo encontrado contem o mesmo digest
				System.out.println("OK");
				
			}
			else if ((this.tipoDigest.equals("MD5") && info.hashMD5 == null) || (this.tipoDigest.equals("SHA1") && info.hashSHA1 == null)) {
				// Arquivo existe, mas nao existe o Hash em questao, apenas o outro
				System.out.println("Existe, mas nao com esse tipo de hash");
				
				if (this.tipoDigest.equals("MD5")) info.hashMD5 = digest;
				else if (this.tipoDigest.equals("SHA1")) info.hashSHA1 = digest;
				
			}
			else {
				// arquivo nao contem mesmo digest, procurar por colisao
				ArqInfo colisaoInfo = buscaInfoPorHash(digest);
				
				if (colisaoInfo != null) {
					// STATUS COLISION
					System.out.println("COLISION");
				
				}
				else {
					// STATUS NOT OK, encontrou arquivo, mas nao colide
					System.out.println("NOT OK com arquivo");
				}
			}
		}
		else {
			// nao encontrou arquivo
			ArqInfo colisaoInfo = buscaInfoPorHash(digest);
			if (colisaoInfo != null) {
				// STATUS NOT OK, nao encontrou arquivo, mas colide
				System.out.println("NOT OK sem aruivo");
			
			}
			else {
				// STATUS NOT FOUND
				System.out.println("NOT FOUND");
				// cria novo ArqInfo e adiciona a lista. Um dos campos de hash deve ser null
				
				ArqInfo novoInfo = new ArqInfo();
				novoInfo.nomeArq = caminhoArq;
				
				System.out.println(this.tipoDigest);
				if (this.tipoDigest.equals("MD5")) {
					System.out.println("Salvando MD5");
					novoInfo.hashMD5 = digest;
				}
				else if (this.tipoDigest.equals("SHA1")) {
					System.out.println("Salvando SHA1");
					novoInfo.hashSHA1 = digest;
				}
				
				this.infoListaDigest.add(novoInfo);
			}
			
		}
		
		return true;
	}
	
	public void setTipoDigest(String tipo) throws NoSuchAlgorithmException {
		this.tipoDigest = tipo;
		this.messageDigest = MessageDigest.getInstance(tipo);	
	}

	public void setInfoListaDigest(String caminhoArqListaDigest) {
		
		System.out.println(caminhoArqListaDigest);
		List<String> content = null;
		try{
			content = Files.readAllLines(Paths.get(caminhoArqListaDigest));
		}
		catch (IOException e) {
			System.out.println(String.format("Failed to read file %s", caminhoArqListaDigest));
			return;
		}
		
		for (String line: content) {
			String[] lineInfo = line.split(" ");
			ArqInfo info = new ArqInfo();
			info.nomeArq = lineInfo[0];
			
			if (lineInfo[1].equals("MD5")) info.hashMD5 = lineInfo[2];
			else if (lineInfo[1].equals("SHA1")) info.hashSHA1 = lineInfo[2];
			
			if (lineInfo.length > 3) {
				if (lineInfo[3].equals("MD5")) info.hashMD5 = lineInfo[4];
				else if (lineInfo[3].equals("SHA1")) info.hashSHA1 = lineInfo[4];
			}
			
			this.infoListaDigest.add(info);
		}
	}
	
	public ArqInfo buscaInfoPorNomeArq(String value) {
		for (ArqInfo i: this.infoListaDigest) {
			if (i.nomeArq.equals(value)) {
				return i;
			}
		}
		return null;
	}
	
	public ArqInfo buscaInfoPorHash(String value) {
		for (ArqInfo i: this.infoListaDigest) {
			if ((this.tipoDigest.equals("MD5") && value.equals(i.hashMD5))|| (this.tipoDigest.equals("MD5") && value.equals(i.hashSHA1))) {
				return i;
			}
		}
		return null;
	}
	
	public static String toHex(byte[] bytes) {
		  
		// converte byte para hexadecimal
		StringBuffer buf = new StringBuffer();
	    for(int i = 0; i < bytes.length; i++) {
	    	String hex = Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1);
	    	buf.append((hex.length() < 2 ? "0" : "") + hex);
	    }
	
	    return buf.toString();
	}
}
