package controler;

import java.util.HashMap;

import entity.Employee;

public class LogIn_controller extends Controller{

	private String username;
	private String password;
	private HashMap<String,Employee> employeeMap;
	private Employee connectedEmployee;
	
	/**
	 * LogIn_controller is the controller that check for validity of the employee
	 * @param host to connect to server
	 * @param employeeMap is holds all the employees that in the system
	 * @param username of the employee that want to connect
	 * @param passwordof the employee that want to connect
	 */
	public LogIn_controller(String host, HashMap<String, Employee> employeeMap, String username, String password) {
		super(host);
		setUsername(username);
		setPassword(password);
		this.employeeMap=employeeMap;
	}
	
	
	/**
	 * checkValidity is checking the user name and password of the employee and
	 * also update him as connected in the DB if all info is correct.
	 * @return true if user name and password are correct else false
	 */
	public boolean checkValidity() {
		if (username.equals(employeeMap.get(getUsername()).getUserName()) && password.equals(employeeMap.get(getUsername()).getPassword())) {
			
			if (checkedIfAlreadyLoggedIn(employeeMap.get(getUsername()).getLogin()) == false) {
				setConnectedEmployee(employeeMap.get(getUsername()));
				updateAsLoggedIn(username);
				showSeccussesMsg("Login was seccessfully acomplished");
				return true;
			}

			else
				showSeccussesMsg("You are already loggedin");
		}
		
		if (!username.equals(employeeMap.get(getUsername()).getUserName()) || !password.equals(employeeMap.get(getUsername()).getPassword()))
					showWarningMsg("Invalid username or password");
		
		return false;
	}

	
	/**
	 * checkedIfAlreadyLoggedIn is getting a string with the status of connection of the employee.
	 * @param connectedStatus is holds the status of connection of the employee
	 * @return true if employee is not connected
	 */
	public boolean checkedIfAlreadyLoggedIn(String connectedStatus) {

		if (connectedStatus.equals("NO"))
			return false;

		return true;
	}
	
	/**
	 * updateAsLoggedIn is changing the connection status of the employee to connect.
	 * @param username of the employee
	 */
	public void updateAsLoggedIn(String username) {
		Object[] sqlsMsg = { "UPDATE  vcp_employ.employ SET login=? WHERE username=?;" ,"YES",username};
		sendQueryToServer(sqlsMsg);
		closeConnection();
		employeeMap.get(username).setLogin("YES");
	}
	
	/**
	 * updateAsNotLoggedIn is sign the employee connection status to disconnect.
	 */
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


	public Employee getConnectedEmployee() {
		return connectedEmployee;
	}


	public void setConnectedEmployee(Employee connectedEmployee) {
		this.connectedEmployee = connectedEmployee;
	}

}
