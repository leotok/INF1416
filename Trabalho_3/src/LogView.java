import java.util.HashMap;
import java.util.List;

public class LogView {

	public static void main(String[] args) {
		List<HashMap> logs = DBManager.getLog();
		
		for (HashMap log: logs) {
			String user = (String)log.get("email");
			if (user.equals("null")) {
				System.out.println(String.format("%s) %s", (Integer)log.get("id"), (String)log.get("texto")));
			}
			else {
				System.out.println(String.format("%s) %s: %s", (Integer)log.get("id"), user, (String)log.get("texto")));
			}
		}
	}

}
