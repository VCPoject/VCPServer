package controler;

import gui.VCP_Main_Frame;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import clientServer.ClientConsole;

public class LogIn_controller{

	private String username;
	private String password;
	private ClientConsole c;
	private VCP_Main_Frame v;
	private ArrayList<Object> resultCopy;
	
	public LogIn_controller(String username, String password) {
		this.username = username;
		this.password = password;
		v = new VCP_Main_Frame();
		checkValidity();
		//checkValidity();
	}

	public boolean checkValidity() {
		Object[] sqlsMsg = {
				"SELECT username,password, login FROM vcp_employ.employ WHERE username=? ;",
				this.username };
		c = new ClientConsole(v.host, 5555);
		c.accept(sqlsMsg);
		resultCopy = null;
		while((resultCopy = c.getResult()) == null){
			threadSleep();
		}

		if (username.equals(resultCopy.get(0).toString())
				&& password.equals(resultCopy.get(1).toString())) {

			if (checkedIfAlreadyLoggedIn((String) resultCopy.get(2)) == false) {

				updateAsLoggedIn();
				showSeccussesMsg("Login was seccessfully acomplished");
				return true;
			}

			else
				showSeccussesMsg("You are already loggedin");
		}
		
		if (!username.equals(resultCopy.get(0).toString()) || !password.equals(resultCopy.get(1).toString()))
			showWarningMsg("Invalid username or password");
		
		return false;
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
		Object[] sqlsMsg = { "UPDATE  vcp_employ.employ SET login=? WHERE username=?;" ,
				"YES",this.username};
		c.accept(sqlsMsg);
	}
	
	public void updateAsNotLoggedIn() {
		Object[] sqlsMsg = { "UPDATE  vcp_employ.employ SET login=? WHERE username=?;" ,
				"NO",this.username};
		c.accept(sqlsMsg);
	}
	
	public void threadSleep(){
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
