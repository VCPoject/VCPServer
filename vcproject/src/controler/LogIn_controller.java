package controler;

import gui.VCP_Main_Frame;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sun.awt.windows.ThemeReader;
import clientServer.ClientConsole;

public class LogIn_controller implements Controller {

	private String username;
	private String password;
	private ClientConsole c;
	private VCP_Main_Frame v;

	public LogIn_controller(String username, String password) {
		this.username = username;
		this.password = password;
		v = new VCP_Main_Frame();
		checkValidity();
	}

	public void checkValidity() {
		Object[] sqlsMsg = {
				"SELECT e.username,e.password, e.login FROM vcp_employ.employ e WHERE e.username=? OR e.password = ?;",
				this.username, this.password };
		c = new ClientConsole(v.host, 5555);
		c.accept(sqlsMsg);
		ArrayList<Object> resultCopy = null;
		System.out.println("1");
		while((resultCopy = c.getResult())==null){}
		
		if (username.equals(resultCopy.get(0).toString())
				&& password.equals(resultCopy.get(1).toString())) {

			if (checkedIfAlreadyLoggedIn((String) resultCopy.get(2)) == false) {

				// updateAsLoggedIn();
				showSeccussesMsg("Login was seccessfully acomplished");
			}

			else
				showSeccussesMsg("You are already loggedin");
		}
		if (!username.equals(resultCopy.get(0).toString()) || !password.equals(resultCopy.get(1).toString()))
			showWarningMsg("Invalid username or password");
	}

	public void showWarningMsg(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg, "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showSeccussesMsg(String msg) {
		JFrame frame = new JFrame();
		JOptionPane
				.showMessageDialog(frame, msg, "", JOptionPane.PLAIN_MESSAGE);
	}

	public boolean checkedIfAlreadyLoggedIn(String str) {

		if (str.equals("NO"))
			return false;

		return true;
	}
	
	

	public void updateAsLoggedIn() {
		Object[] sqlsMsg = { "UPDATE  vcp_employ"
				+ "FROM employ e WHERE e.username=? AND e.password = ?;" };
		c.accept(sqlsMsg);
	}

}
