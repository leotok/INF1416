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
//		user01@inf1416.puc-rio.br
		
//		String pastaKeys = "/home/ecp/e1312737/e1312737.pds.V2/Documents/INF1416/Trabalho_3/Pacote-T3/Keys/";
//		String pastaArquivos = "/home/ecp/e1312737/e1312737.pds.V2/Documents/INF1416/Trabalho_3/Pacote-T3/Files/";
//		String email = "user01@inf1416.puc-rio.br";
//		String senha = "321456";
//		String grupo = "usuario";
//		String fraseSecreta = "user01";
//		String pathChavePrivada = pastaKeys + "user01-pkcs8-pem-des.key";
//		String pathCertificado = pastaKeys + "user01-x509.crt";
//		
//		
//		DBManager.insereRegistro(1001);
//		Auth.cadastraUsuario(grupo, senha, pathCertificado);
//		HashMap user = Auth.autenticaEmail(email);
//		if (user == null) {
//			System.out.println("Email não autenticado");
//		}
//		else {
//			System.out.println("Email autenticado");
//		}
//		
//		if (Auth.autenticaSenha(senha, user) == false) {
//			System.out.println("Senha incorreta");
//		}
//		else {
//			System.out.println("Senha correta");
//			System.out.println(user);
//			
//			PrivateKey chavePrivada = Auth.leChavePrivada(fraseSecreta, pathChavePrivada);
//			System.out.println("Chave privada:");
//			System.out.println(chavePrivada);
//			System.out.println("Teste da chave privada: ");
//			
//			if (Auth.testaChavePrivada(chavePrivada, (String) user.get("certificado"), user)) {
//				System.out.println("Chave privada ok");
//			}
//			else {
//				System.out.println("Chave privada errada");
//			}
//			
//			String index = null;
//			try {
//				index = new String(Auth.decriptaArquivo(user, pastaArquivos, "index", chavePrivada), "UTF8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(index);
//			
//			System.out.println(Auth.acessarArquivo(user, index, "teste01.docx", chavePrivada, pastaArquivos));
//			
//			System.out.println(Auth.geraTanList("../", 10, email));
//			
//			
//			System.out.println(DBManager.retornaTanList("user01@inf1416.puc-rio.br"));
//			DBManager.marcaTanUsada("user01@inf1416.puc-rio.br", 1);
//			DBManager.marcaTanUsada("user01@inf1416.puc-rio.br", 2);
//			System.out.println(DBManager.retornaTanList("user01@inf1416.puc-rio.br"));
//		}
		
	}
}
