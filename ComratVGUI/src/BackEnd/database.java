package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String USERNAME = "root";
	static final String PASSWORD = "password";//comratv.mixedknowledgebasever4
	static String DB_URL = "jdbc:mysql://localhost/Test";

	public static synchronized ResultSet databaseQuery() {
		//String firstWord,String secondWord,int frequency
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			ResultSet rs =stmt.executeQuery("SELECT * FROM final");
					//stmt.execute(sql);
			return rs;
		} catch (Exception e) {
			System.out.println("error getting data");
			e.printStackTrace();
		}finally{
			try {
				System.out.println("");
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		return null;
	}
}
