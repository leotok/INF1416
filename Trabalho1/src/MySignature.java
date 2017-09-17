import java.security.*;
import java.util.Arrays;

import javax.crypto.*;

/**
 * INF1416 - Trabalho 1
 * 
 * Enunciado Construir um programa Java utilizando a JCA que recebe um texto na linha de comando e assina o mesmo.
 * O processo de geração da assinatura e verificação da mesma deve ser feito sem a utilização da classe Signature,
 * detalhando-se na saída padrão cada um dos passos executados, inclusive apresentando o digest e a assinatura
 * no formato hexadecimal. A classe MySignature deve ser implementada com os métodos initSign, update, sign, 
 * initVerify e verify com funcionalidades equivalentes aos respectivos métodos da classe Signature.
 * 
 * @author Leonardo E. Wajnsztok
 */


public class MySignature {
	
	private PrivateKey key;
	private MessageDigest messageDigest;
	private Cipher cipher; 
	
	public static void main (String[] args) throws Exception {
		
		if (args.length !=1) {
			System.err.println("Usage: java MySignature text");
			System.exit(1);
		}
	    byte[] plainText = args[0].getBytes("UTF8");
	    
	    // gera o par de chaves RSA
	    System.out.println( "\nStart generating RSA key" );
	    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
	    keyGen.initialize(1024);
	    KeyPair key = keyGen.generateKeyPair();
	   
	    // Inicializa objeto de assinatura
	    MySignature sig = new MySignature();
	    sig.initSign(key.getPrivate());
	    
	    // assina o texto plano com chave privada
	    sig.update(plainText);
	    byte[] signature = sig.sign();
	    
	    System.out.println("Signature:");
	    hexPrint(signature);
	    
	    // verifica a assinatura com a chave publica
	    sig.initVerify(key.getPublic());
	    sig.update(plainText);
	    
	    if (sig.verify(signature)) {
	    	System.out.println("Signature verified");
	    }
	    else {
	    	System.out.println("Signature failed");
	    }
	    
	}
	  
	  // Public methods
	  
	public MySignature() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		this.messageDigest = MessageDigest.getInstance("MD5");	
		this.cipher = Cipher.getInstance("RSA");
	}
	
	public void initSign(PrivateKey key) throws InvalidKeyException {		
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
	}
	  
	public void update(byte[] data) {
		// get a message digest object using the MD5 algorithm
	    this.messageDigest.update(data);
	}
	  
	public byte[] sign() throws IllegalBlockSizeException, BadPaddingException {
		byte[] digest = this.messageDigest.digest();
		return this.cipher.doFinal(digest);
		
	}
	  
	public void initVerify(PublicKey key) throws InvalidKeyException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
	}
	  
	public boolean verify(byte[] signature) throws IllegalBlockSizeException, BadPaddingException {
		byte[] decryptedMessageDigest = cipher.doFinal(signature);
				
		return Arrays.equals(this.messageDigest.digest(), decryptedMessageDigest);
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
