package controler;

import java.util.ArrayList;

public class LogIn_controller extends Controller{

	private ArrayList<Object> resultCopy;

	public LogIn_controller(String host) {
		super(host);
	}
	
	public boolean checkValidity(String username,String password) {
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
	
	public void updateAsNotLoggedIn(String username) {
		Object[] sqlsMsg = { "UPDATE  vcp_employ.employ SET login=? WHERE username=?;" ,
				"NO",username};
		sendQueryToServer(sqlsMsg);
	}

}
