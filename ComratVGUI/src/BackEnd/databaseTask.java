package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class databaseTask {
	private String databaseSource;
	private String tableName;
	private String JDBC_DRIVER = "jdbc:sqlite:";
	private String className="org.sqlite.JDBC";
	private String userName="";
	private String password="";
	Connection connection=null;

	public databaseTask() {
		databaseSource = "database/vRAT.db";
		tableName = "KnowledgeBase";
	}

/*	public boolean setUpConnection(){
		try {
			if(connection.isValid(0)){
				connection.close();
			}
			Class.forName(className);
			connection = DriverManager.getConnection(JDBC_DRIVER + databaseSource,userName,password);
			System.out.println("Database connected successfully");
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
		}
	}*/
	public String getDatabaseSource() {
		synchronized(this){
		return databaseSource;
		}
	}

	public void setDatabaseSource(String database) {
		synchronized(this){
		this.databaseSource = database;
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		synchronized(this){
		this.tableName = tableName;
		}
	}

	boolean testOrCreateConnection() {
		Connection c = null;
		try {
			Class.forName(className);
			c = DriverManager.getConnection(JDBC_DRIVER + databaseSource,userName,password);
			System.out.println("Database connected successfully");
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
		}
	}
	private  Statement getConnection(){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(JDBC_DRIVER + databaseSource);
			stmt = conn.createStatement();
			return stmt;
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	void createTable(String tName) {
		Statement stmt = null;
		try {
			stmt=getConnection();
			String sql = "CREATE TABLE " + tName
					+ " (id INTEGER PRIMARY KEY AUTOINCREMENT not Null,"
					+ " QueryWord           TEXT    NOT NULL, "
					+ " relatedWord         TEXT     NOT NULL, "
					+ " frequency        INT NOT NULL)";
			stmt.execute(sql);
		} catch (SQLException e) {
			//stmt.getConnection().rollback();
			e.printStackTrace();
		} finally {
			closeDatabase(stmt,null);
		}
	}

	public void dropTable(String tName) {
		Statement stmt = null;
		try {
			stmt=getConnection();
			String sql = "DROP TABLE " + tName;
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDatabase(stmt,null);
		}
	}

	public Map<String, Integer> getMatch(String word) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		Statement stmt = null;
		int frequency = 0;
		ResultSet rs = null;
		String query = "SELECT queryWord,frequency FROM " + tableName
				+ " where relatedword='" + word + "'";
		String name = "";
		try {
			stmt = getConnection();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				name = rs.getString("queryWord");
				frequency = rs.getInt("frequency");
				map.put(name.toLowerCase().trim(), frequency);
			}
			rs = stmt.executeQuery("SELECT relatedWord,frequency FROM "
					+ tableName + " where queryWord='" + word + "'");
			while (rs.next()) {
				name = rs.getString("relatedWord");
				frequency = rs.getInt("frequency");
				map.put(name.toLowerCase().trim(), frequency);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDatabase(stmt,rs);
		return map;
	}

	@SuppressWarnings("resource")
	public int getWordCount(String word) {
		int count = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			stmt =getConnection();
			rs = stmt.executeQuery("SELECT SUM(frequency) FROM " + tableName
					+ " where queryWord='" + word + "'");
			while (rs.next()) {
				count += rs.getInt(1);
			}
			rs = stmt.executeQuery("SELECT SUM(frequency) FROM " + tableName
					+ " where relatedWord='" + word + "'");
			while (rs.next()) {
				count += rs.getInt(1);
			}
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			closeDatabase(stmt,rs);
		}
	}

	private synchronized void closeDatabase(Statement stmt,ResultSet rs) {
		try {
			if(rs!=null){
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (stmt.getConnection() != null) {
				stmt.getConnection().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
