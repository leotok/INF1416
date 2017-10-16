import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		new MainView();
		DBManager db = new DBManager();
		db.connect();
		db.addUser("Leo", "leo@leo.com", "grupo", "sal", "senha");
		ResultSet set = db.getUser("leo@leo.com");
		System.out.println(set.getString("name"));
		db.closeConn();
		
		
	}
}
