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
		private byte[] hashMD5 = null;
		private byte[] hashSHA1 = null;
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
			if (calculator.calculaDigest(arq)) {
				calculator.procuraDigest(arq);
			}
		}

	}
	
	
	
	void saveInfoToFile(String nomeArq, String tipoDigest, byte[] digest) {
		
		String content = String.format("%s %s %s\n", nomeArq, tipoDigest, digest);
		try {
			Files.write(Paths.get("listaDigests.txt"), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	boolean calculaDigest(String caminhoArq)  {
		List<String> content;
		try{
			content = Files.readAllLines(Paths.get(caminhoArq));	
		}
		catch (IOException e) {
			System.out.println(String.format("Failed to read file %s", caminhoArq));
			return false;
		}
		
		System.out.print("Conteudo:");
		
		for (String str: content) {
			System.out.println(str);
			this.messageDigest.update(str.getBytes());	
		}
		
		
		return true;
		
	}

	boolean procuraDigest(String caminhoArq) {
		ArqInfo info = buscaInfoPorNomeArq(caminhoArq);
		byte [] digest = this.messageDigest.digest();
		
		System.out.print("Procurando digest:");
		hexPrint(digest);
		
		if (info != null) {
			// encontrou arquivo
			if ((this.tipoDigest == "MD5" && Arrays.equals(digest, info.hashMD5)) || (this.tipoDigest == "SHA1" && Arrays.equals(digest, info.hashSHA1))) {
				// STATUS OK - arquivo encontrado contem o mesmo digest
			}
			else {
				// arquivo nao contem mesmo digest, procurar por colisao
				ArqInfo colisaoInfo = buscaInfoPorHash(digest);
				
				if (colisaoInfo != null) {
					// STATUS COLISION
				
				}
				else {
					// STATUS NOT OK
				}
			}
		}
		else {
			// nao encontrou arquivo
			ArqInfo colisaoInfo = buscaInfoPorHash(digest);
			if (colisaoInfo != null) {
				// STATUS NOT OK
			
			}
			else {
				// STATUS NOT FOUND
				saveInfoToFile(caminhoArq, this.tipoDigest, digest);
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
			
			if (lineInfo[1] == "MD5") info.hashMD5 = lineInfo[2].getBytes();
			else if (lineInfo[1] == "SHA1") info.hashSHA1 = lineInfo[2].getBytes();
			
			if (lineInfo.length > 3) {
				if (lineInfo[3] == "MD5") info.hashMD5 = lineInfo[4].getBytes();
				else if (lineInfo[3] == "SHA1") info.hashSHA1 = lineInfo[4].getBytes();
			}
			
			this.infoListaDigest.add(info);
		}
	}
	
	public ArqInfo buscaInfoPorNomeArq(String value) {
		for (ArqInfo i: this.infoListaDigest) {
			if (i.nomeArq == value) {
				return i;
			}
		}
		return null;
	}
	
	public ArqInfo buscaInfoPorHash(byte[] value) {
		for (ArqInfo i: this.infoListaDigest) {
			if ((this.tipoDigest == "MD5" && i.hashMD5 == value)|| (this.tipoDigest == "MD5" && i.hashSHA1 == value)) {
				return i;
			}
		}
		return null;
	}
	
	public static void hexPrint(byte[] bytes) {
		  
		// converte o signature para hexadecimal
		StringBuffer buf = new StringBuffer();
	    for(int i = 0; i < bytes.length; i++) {
	    	String hex = Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1);
	    	buf.append((hex.length() < 2 ? "0" : "") + hex);
	    }
	
	    // imprime o signature em hexadecimal
	    System.out.println( buf.toString() );
	}
}
