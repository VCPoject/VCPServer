package controler;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Employee;

public class LogIn_controller extends Controller{

	private ArrayList<Object> result;
	private String username;
	private String password;
	private HashMap<String,Employee> employeeMap;
	
	public LogIn_controller(String host, HashMap<String, Employee> employeeMap, String username, String password) {
		super(host);
		setUsername(username);
		setPassword(password);
		this.employeeMap=employeeMap;
	}
	
	public boolean checkValidity() {
		
		
		if (username.equals(employeeMap.get(getUsername()).getUserName())
				&& password.equals(employeeMap.get(getUsername()).getPassword())) {
			
			if (checkedIfAlreadyLoggedIn(employeeMap.get(getUsername()).getLogin()) == false) {
				updateAsLoggedIn(username);
				showSeccussesMsg("Login was seccessfully acomplished");
				return true;
			}

			else
				showSeccussesMsg("You are already loggedin");
		}
		
		if (!username.equals(employeeMap.get(getUsername()).getUserName()) || 
				!password.equals(employeeMap.get(getUsername()).getPassword()))
					showWarningMsg("Invalid username or password");
		
		return false;
	}

	
	public boolean checkedIfAlreadyLoggedIn(String str) {

		if (str.equals("NO"))
			return false;

		return true;
	}
	
	public void updateAsLoggedIn(String username) {
		Object[] sqlsMsg = { "UPDATE  vcp_employ.employ SET login=? WHERE username=?;" ,"YES",username};
		sendQueryToServer(sqlsMsg);
		closeConnection();
		employeeMap.get(username).setLogin("YES");
	}
	
	public void updateAsNotLoggedIn() {
		Object[] sqlsMsg = { "UPDATE  vcp_employ.employ SET login=? WHERE username=?;" ,
				"NO",getUsername()};
		sendQueryToServer(sqlsMsg);
		closeConnection();
		employeeMap.get(getUsername()).setLogin("NO");
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
