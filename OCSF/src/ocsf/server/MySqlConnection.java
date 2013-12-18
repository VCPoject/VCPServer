package ocsf.server;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

public class MySqlConnection {

	private ArrayList<Object> result = new ArrayList<Object>();
	private Connection conn;
	private ResultSet rs = null;

	public MySqlConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {/* handle the error */
			System.out.println("Im here: " + ex.getMessage());
		}

		try {
			conn = (Connection) DriverManager
					.getConnection(
							"jdbc:mysql://localhost/bsi_db?CharSet=utf8&useUnicode=true&characterEncoding=utf8",
							"root", "Op8448060");
			conn.setAutoCommit(false);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			setResult("SQLException: " + ex.getMessage());
			setResult("SQLState: " + ex.getSQLState());
			setResult("VendorError: " + ex.getErrorCode());
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void update(Connection conn, Object[] msg) {

		String command = (String) msg[0];
		try {
			if (command.contains("SELECT")) {
				readDB(conn, msg);
			}

			else if (command.contains("UPDATE")) {
				updateDB(conn, msg);
			} else if (command.contains("INSERT")) {
				insertDB(conn, msg);
			}
			conn.close();
		} catch (Exception e) {
			setResult("update error:" + e.getMessage());
		}

	}

	private void readDB(Connection con, Object[] getStatment) {
		boolean thereIsRslt = false;
		try {
			String selectSQL = (String) getStatment[0];
			PreparedStatement selectData = con.prepareStatement(selectSQL);
			for (int i = 1; i < getStatment.length; i++) {
				if (getStatment[i] instanceof String)
					selectData.setString(i, (String) getStatment[i]);
				else if (getStatment[i] instanceof Integer)
					selectData.setInt(i, (Integer) getStatment[i]);
			}
			rs = selectData.executeQuery();
			ArrayList<Object> list = new ArrayList<Object>();
			ResultSetMetaData metadata = rs.getMetaData();
			int numberOfColumns = metadata.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++){
					Object obj = rs.getObject(i);
					if(obj instanceof String)
						list.add((String)obj);
					else if (obj instanceof Integer)
						list.add((Integer)obj);
				}
				thereIsRslt = true;
			}
			if (thereIsRslt) {
				rs.close();
				for (Object str : list)
					setResult(str);
				thereIsRslt = false;
			} else {
				rs.close();
				setResult("No Result");
			}
		} catch (Exception e) {
			System.out.println("readDB error:" + e.getMessage());
			setResult("readDB error:" + e.getMessage());
		}

	}

	private void updateDB(Connection con, Object[] getStatment) {
		try {
			PreparedStatement updataData = con
					.prepareStatement((String) getStatment[0]);
			for (int i = 1; i < getStatment.length; i++) {
				if (getStatment[i] instanceof String)
					updataData.setString(i, (String) getStatment[i]);
				else if (getStatment[i] instanceof Integer)
					updataData.setInt(i, (Integer) getStatment[i]);
			}
			updataData.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("updateDB error:" + e.getMessage());
			setResult("updateDB error:" + e.getMessage());
		}
	}

	private void insertDB(Connection con, Object[] getStatment) {
		try {
			PreparedStatement updataData = con
					.prepareStatement((String) getStatment[0]);
			for (int i = 1; i < getStatment.length; i++) {
				if (getStatment[i] instanceof String)
					updataData.setString(i, (String) getStatment[i]);
				else if (getStatment[i] instanceof Integer)
					updataData.setInt(i, (Integer) getStatment[i]);
			}
			updataData.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("insertDB error:" + e.getMessage());
			setResult("insertDB error:" + e.getMessage());
		}

	}

	private void setResult(Object result) {
		this.result.add(result);
	}

	public ArrayList<Object> getResult() {
		return this.result;
	}

}
