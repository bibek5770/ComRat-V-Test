package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class insertWithoutDuplication {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String DB_URL;
	private String databaseName;
	private String userName = "root";
	private String password = "password";
	private String tableName;

	public insertWithoutDuplication(String databaseName, String userName,
			String password, String tableName) {
		this.databaseName = databaseName;
		this.userName = userName;
		this.password = password;
		this.tableName = tableName;
		DB_URL = "jdbc:mysql://localhost/" + this.databaseName;
	}

	public synchronized void insert(int frequency, String firstWord,
			String secondWord) {
		Connection connection = null;
		Statement stmts = null;
		firstWord = firstWord.trim().toLowerCase();
		secondWord = secondWord.trim().toLowerCase();
		if (!firstWord.isEmpty() && !secondWord.isEmpty() && frequency > 0) {
			try {
				// STEP 2: Register JDBC driver
				Class.forName(JDBC_DRIVER);
				// STEP 3: Open a connection
				connection = DriverManager.getConnection(DB_URL, userName,
						password);
				stmts = connection.createStatement();
				String sql = "SELECT * FROM " + tableName
						+ " where queryword='" + firstWord
						+ "' AND relatedword='" + secondWord + "'";
				ResultSet rs = stmts.executeQuery(sql);
				if (!rs.isBeforeFirst()) {
					rs.close();
					stmts.close();
					stmts = connection.createStatement();
					sql = "SELECT * FROM " + tableName + " where queryword = '"
							+ secondWord + "' AND relatedword = '" + firstWord
							+ "'";

					rs = stmts.executeQuery(sql);
					if (!rs.isBeforeFirst()) {
						rs.close();
						stmts.close();
						stmts.close();
						stmts = connection.createStatement();
						sql = "INSERT INTO " + tableName
								+ " (queryWord,relatedWord,frequency)"
								+ " VALUES('" + firstWord + "','" + secondWord
								+ "'," + frequency + ")";
						stmts.execute(sql);
						rs.close();
					} else {
						int oldFrequency2 = 0;
						while (rs.next()) {
							oldFrequency2 = rs.getInt("frequency");
						}
						rs.close();
						stmts.close();
						oldFrequency2 = oldFrequency2 + frequency;
						stmts = connection.createStatement();
						sql = "UPDATE " + tableName + " SET frequency = "
								+ oldFrequency2 + " where queryword='"
								+ secondWord + "' AND relatedword = '"
								+ firstWord + "'";
						stmts.execute(sql);
						rs.close();
					}
				} else {
					int oldFrequency1 = 0;
					while (rs.next()) {
						oldFrequency1 = rs.getInt("frequency");
					}
					rs.close();
					stmts.close();
					oldFrequency1 = oldFrequency1 + frequency;
					stmts = connection.createStatement();
					sql = "UPDATE " + tableName + " SET frequency = "
							+ oldFrequency1 + " where queryword='" + firstWord
							+ "' AND relatedword = '" + secondWord + "'";
					stmts.execute(sql);
					rs.close();
				}
				// return rs;
			} catch (Exception e) {
				System.out.println("error getting data");
				e.printStackTrace();
			} finally {
				try {
					System.out.println("");
					stmts.close();
					connection.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void createTable() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, userName, password);
			stmt = conn.createStatement();
			String sql = "CREATE TABLE " + "final "
					+ "(id INTEGER not NULL AUTO_INCREMENT, "
					+ "queryWord VARCHAR(255), "
					+ " relatedWord VARCHAR(255), " + "frequency INTEGER, "
					+ "PRIMARY KEY ( id ))";
			stmt.execute(sql);
			System.out.println("table Created");
		} catch (Exception e) {
			System.out.println("error getting data");
			e.printStackTrace();
		} finally {
			try {
				System.out.println("");
				stmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void dropTable() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, userName, password);
			stmt = conn.createStatement();
			String sql = "Drop Table Test";
			;
			stmt.execute(sql);
			System.out.println("table Dropped");
		} catch (Exception e) {
			System.out.println("error getting data");
			e.printStackTrace();
		} finally {
			try {
				System.out.println("");
				stmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
