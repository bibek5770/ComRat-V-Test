package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JdbcDerbyConnection {

	//public static void main(String args[]) {
		//insert();
		///VratAlgorythm algorythm=new VratAlgorythm();
		//Solution finalSolution=algorythm.solve(null);
	/*	databaseTask database1=new databaseTask();
		database1.testOrCreateConnection();
		try {
			database1.createTable("knowledgeBase");
			ResultSet toCopy = database.databaseQuery();
			String a;
			String b;
			int f;
			int i=1;
			while (toCopy.next()) {
				a = toCopy.getString("queryWord");
				b = toCopy.getString("relatedWord");
				f = toCopy.getInt("frequency");
				System.out.println(i+"=>"+a+"\t"+b+"\t"+f);
				insert(a,b,f);
				i++;
			}
			toCopy.getStatement().getConnection().close();
			} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("evrything went fine");*/
		
	//}
	
	public static synchronized void insert() {
		String sql;
		//sql = "INSERT INTO knowledgeBase (queryWord,relatedWord,frequency) "
			//	+ "VALUES ('" + a + "','" + b + "'," + f + ")";
		Statement stmt=null;
		sql="SELECT name FROM sqlite_master WHERE type='table'";

		Connection c=null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database/vRAT.db");
			//System.out.println("Opened database successfully");
			stmt = c.createStatement(); 
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("TABLE_NAME"));
			}
			System.out.println("successful");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}
	}
}
