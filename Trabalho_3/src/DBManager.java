import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBManager {
	private Connection conn = null;
	
	public void connect() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
			Statement stat = conn.createStatement();
			stat.setQueryTimeout(30);
			stat.executeUpdate("DROP TABLE IF EXISTS User");
			stat.executeUpdate("CREATE TABLE User (name STRING, email STRING, groupName STRING, salt STRING, passwordDigest STRING)");
			
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public boolean addUser(String name, String email, String group, String salt, String passwordDigest) {
		try {
			Statement stat = conn.createStatement();
			stat.setQueryTimeout(30);
			stat.executeUpdate(String.format("INSERT INTO User VALUES ('%s', '%s', '%s', '%s', '%s')", name, email, group, salt, passwordDigest));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public ResultSet getUser(String email) {
		try {
			Statement stat = conn.createStatement();
			stat.setQueryTimeout(30);
			return stat.executeQuery(String.format("SELECT * FROM User WHERE email = '%s'", email));
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public boolean closeConn() {
		try {
			if (conn != null) 
				conn.close();
		}
		catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
	
}
