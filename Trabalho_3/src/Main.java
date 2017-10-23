import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		new LoginView();
		
		
//		String pastaKeys = "/Users/LeoWajnsztok/Documents/PUC/INF1416/Trabalho_3/Pacote-T3/Keys/";
//		String pastaArquivos = "/Users/LeoWajnsztok/Documents/PUC/INF1416/Trabalho_3/Pacote-T3/Files/";
//		
//		DBManager.insereRegistro(1001);
////		Auth.cadastraUsuario("usuario", "321456", pastaKeys + "user01-x509.crt");
//		HashMap user = Auth.autenticaEmail("user01@inf1416.puc-rio.br");
//		System.out.println(user);
//		
//		PrivateKey chavePrivada = Auth.leChavePrivada("user01", pastaKeys + "user01-pkcs8-pem-des.key");
//		System.out.println(Auth.testaChavePrivada(chavePrivada, (String) user.get("certificado"), user));
//		
//		String index = null;
//		try {
//			index = new String(Auth.decriptaArquivo(user, pastaArquivos, "index", chavePrivada), "UTF8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(index);
//		
//		System.out.println(Auth.acessarArquivo(user, index, "teste01.docx", chavePrivada, pastaArquivos));
//		
//		System.out.println(Auth.geraTanList("/Users/LeoWajnsztok/Documents/PUC/INF1416/Trabalho_3/", 10, "user01@inf1416.puc-rio.br"));
//		
//		
////		System.out.println(db.retornaTanList("user01@inf1416.puc-rio.br"));
////		DBManager.marcaTanUsada("user01@inf1416.puc-rio.br", 1);
////		DBManager.marcaTanUsada("user01@inf1416.puc-rio.br", 2);
////		System.out.println(DBManager.retornaTanList("user01@inf1416.puc-rio.br"));
	}
}
