package ocsf.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlConnection {

	private Object result = null;

	public mysqlConnection(Object msg) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {/* handle the error */
			System.out.println("Im here: " + ex.getMessage());
		}

		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/vcp_db", "root", "Op8448060");
			conn.setAutoCommit(false);
			update(conn, msg);
			// Connection conn =
			// DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
			System.out.println("SQL connection succeed");
			// createTableCourses(conn);
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			setResult("SQLException: " + ex.getMessage());
			setResult("SQLState: " + ex.getSQLState());
			setResult("VendorError: " + ex.getErrorCode());
		}
	}

	public void update(Connection conn, Object msg) {

		String[] command = (String[]) msg;
		try {
			if (command[0].compareTo("readDB") == 0) {
				readDB(conn, msg);
			}

			else if (command[0].compareTo("updateDB") == 0) {
				updateDB(conn, msg);
			}
			conn.close();
		} catch (Exception e) {
			setResult("update error:" + e.getMessage());
		}

	}

	private void readDB(Connection con, Object msg) {
		String[] getMsg = (String[]) msg;
		String ans = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(getMsg[1]);
			while (rs.next()) {
				ans = ans + rs.getString(1);
			}
			rs.close();
			setResult(ans);
		} catch (Exception e) {
			System.out.println("readDB error:" + e.getMessage());
			setResult("readDB error:" + e.getMessage());
		}

	}

	private void updateDB(Connection con, Object msg) {

		String[] getStatment = (String[]) msg;
		String setMsg = getStatment[1];
		try {
			PreparedStatement updataData=con.prepareStatement("UPDATE prototype SET val= ? WHERE val= 'A';");
			updataData.setString(1,setMsg);
			updataData.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("updateDB error:" + e.getMessage());
			setResult("updateDB error:" + e.getMessage());
		}
		setResult(true);
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getResult() {
		return this.result;
	}

	/*
	 * public void addSubscribe(Connection conn, Object msg) {
	 * 
	 * Statement statement = null; String[] command = (String[]) msg;
	 * System.out.println("im here");
	 * 
	 * String insertTableSQL = "INSERT INTO vcp_db.subscribed (id,name) VALUES("
	 * + Integer.parseInt(command[1]) + "," + "'"+command[2]+"'" +");";
	 * 
	 * try { statement = conn.createStatement(); System.out.println("im here2");
	 * // execute insert SQL stetement statement.executeUpdate(insertTableSQL);
	 * System.out.println("Record is inserted into DBUSER table!");
	 * 
	 * } catch (SQLException e) {
	 * 
	 * System.out.println(e.getMessage());
	 * 
	 * } finally {
	 * 
	 * if (statement != null) { try { statement.close(); } catch (SQLException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * if (conn != null) { try { conn.close(); } catch (SQLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * } }
	 */

}
