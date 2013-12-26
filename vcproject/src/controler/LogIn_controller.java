package controler; 

import java.util.ArrayList;

public class LogIn_controller extends Controller{

	private ArrayList<Object> resultCopy;
	private String username;
	private String password;

	public LogIn_controller(String host) {
		super(host);
	}
	
	public boolean checkValidity(String username,String password) {
		setUsername(username);
		setPassword(password);
		Object[] sqlsMsg = {
				"SELECT username,password, login FROM vcp_employ.employ WHERE username= ? AND password = ? ;",
				username, password};
		sendQueryToServer(sqlsMsg);
		resultCopy = getResult();

		if (username.equals(resultCopy.get(0).toString())
				&& password.equals(resultCopy.get(1).toString())) {

			if (checkedIfAlreadyLoggedIn((String) resultCopy.get(2)) == false) {

				updateAsLoggedIn(username);
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

	public boolean checkedIfAlreadyLoggedIn(String str) {

		if (str.equals("NO"))
			return false;

		return true;
	}
	
	public void updateAsLoggedIn(String username) {
		Object[] sqlsMsg = { "UPDATE  vcp_employ.employ SET login=? WHERE username=?;" ,
				"YES",username};
		sendQueryToServer(sqlsMsg);
	}
	
	public void updateAsNotLoggedIn() {
		Object[] sqlsMsg = { "UPDATE  vcp_employ.employ SET login=? WHERE username=?;" ,
				"NO",getUsername()};
		sendQueryToServer(sqlsMsg);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
