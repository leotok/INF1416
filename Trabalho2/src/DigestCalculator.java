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
		
		for (int i = 2; i < args.length; i++) {
			caminhosArqs.add(args[i]);
		}
		
		for (String arq: caminhosArqs) {
			String digest = calculator.calculaDigest(arq);
			if (digest != null) {
				String status = calculator.procuraDigest(arq, digest);
				System.out.println(String.format("%s %s %s %s", arq, calculator.tipoDigest, digest, status));
			}
		}
		calculator.saveInfoToFile();

	}
	
	
	
	void saveInfoToFile() {
		String content = "";
		
		for (ArqInfo i: this.infoListaDigest) {
			
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
		
		for (String str: content) {
			this.messageDigest.update(str.getBytes());	
		}
		
		return toHex(this.messageDigest.digest());
		
	}

	String procuraDigest(String caminhoArq, String digest) {
		
		ArqInfo info = buscaInfoPorNomeArq(caminhoArq);
		
		if (info != null) {
			// encontrou arquivo
			if ((this.tipoDigest.equals("MD5") && digest.equals(info.hashMD5)) || (this.tipoDigest.equals("SHA1") && digest.equals(info.hashSHA1))) {
				// STATUS OK - arquivo encontrado contem o mesmo digest
				return "OK";
			}
			else if ((this.tipoDigest.equals("MD5") && info.hashMD5 == null) || (this.tipoDigest.equals("SHA1") && info.hashSHA1 == null)) {
				// Arquivo existe, mas nao existe o Hash em questao, apenas o outro
				
				if (this.tipoDigest.equals("MD5")) info.hashMD5 = digest;
				else if (this.tipoDigest.equals("SHA1")) info.hashSHA1 = digest;
				
				return "NOT FOUND";
				
			}
			else {
				// arquivo nao contem mesmo digest, procurar por colisao
				ArqInfo colisaoInfo = buscaInfoPorHash(digest);
				
				if (colisaoInfo != null) {
					// STATUS COLISION
					return "COLISION";
				
				}
				else {
					// STATUS NOT OK, encontrou arquivo, mas nao colide
					return "NOT OK";
				}
			}
		}
		else {
			// nao encontrou arquivo
			ArqInfo colisaoInfo = buscaInfoPorHash(digest);
			if (colisaoInfo != null) {
				// STATUS COLISION, nao encontrou arquivo, mas colide
				return "COLISION";
			
			}
			else {
				// STATUS NOT FOUND
				// cria novo ArqInfo e adiciona a lista. Um dos campos de hash deve ser null
				
				ArqInfo novoInfo = new ArqInfo();
				novoInfo.nomeArq = caminhoArq;
				
				if (this.tipoDigest.equals("MD5")) {
					novoInfo.hashMD5 = digest;
				}
				else if (this.tipoDigest.equals("SHA1")) {
					novoInfo.hashSHA1 = digest;
				}
				
				this.infoListaDigest.add(novoInfo);
				return "NOT FOUND";
			}
			
		}
	}
	
	public void setTipoDigest(String tipo) throws NoSuchAlgorithmException {
		this.tipoDigest = tipo;
		this.messageDigest = MessageDigest.getInstance(tipo);	
	}

	public void setInfoListaDigest(String caminhoArqListaDigest) {
		
		List<String> content = null;
		try{
			content = Files.readAllLines(Paths.get(caminhoArqListaDigest));
		}
		catch (IOException e) {
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
			if ((this.tipoDigest.equals("MD5") && value.equals(i.hashMD5)) || (this.tipoDigest.equals("SHA1") && value.equals(i.hashSHA1))) {
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
