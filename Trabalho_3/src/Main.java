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
		DBManager.insereRegistro(1001);
		new LoginView();		
	}
}
// admin@inf1416.puc-rio.br
// select email, texto from Registro JOIN Mensagem ON Mensagem.id = Registro.messageId order by created;