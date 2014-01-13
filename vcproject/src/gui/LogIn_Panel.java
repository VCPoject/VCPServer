package gui;
 
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.*;

import controler.LogIn_controller;
import entity.Employee;

public class LogIn_Panel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private String host;
	private JTextField usertxtfieled;
	private JButton btnexit;
	private JButton btnsubmmit;
	private LogIn_controller logIncontroller;
	private JPasswordField passwordField;
	private HashMap<String,Employee> employeeMap;

	/**
	 * This panel is for the login screen of the employees
	 * @param host for connecting to server side
	 * @param employeeMap contains all the employees from DB
	 */
	public LogIn_Panel(String host,HashMap<String,Employee> employeeMap){
		super();
		this.employeeMap=employeeMap;
		this.host = host;
		initialize();
	}
	/**
	 * Initialize the panel of saving parking place
	 */
	private void initialize(){
		this.setBounds(10, 11, 494, 372);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		usertxtfieled = new JTextField();
		usertxtfieled.setText("sapir");
		usertxtfieled.setBounds(170, 130, 228, 22);
		add(usertxtfieled);
		usertxtfieled.setColumns(10);
		
		btnsubmmit = new JButton("Submmit");
		btnsubmmit.setBounds(346, 305, 138, 56);
		add(btnsubmmit);
		
		btnexit= new JButton("Exit\r\n");
		btnexit.setBounds(10, 305, 138, 56);
		add(btnexit); 
		
		JLabel lblUserName = new JLabel("Username\r\n:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblUserName.setBounds(55, 130, 104, 22);
		add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPassword.setBounds(55, 170, 104, 17);
		add(lblPassword);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblLogIn.setBounds(188, 11, 117, 44);
		add(lblLogIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(170, 170, 228, 22);
		add(passwordField);
	}
	
	public JButton getBtnSubmit(){
		return btnsubmmit;
	}
	
	
	public JButton getBtnExit(){
		return btnexit;
	}
	
	public String getPswdText(){
		return new String(passwordField.getPassword());
	}
	
	public String getUserText(){
		return usertxtfieled.getText();
	}

	public boolean checkValidity() {
		return getLogincontroller().checkValidity();
	}
	
	public LogIn_controller getLogincontroller(){
		if(logIncontroller == null)
		{
			logIncontroller = new LogIn_controller(this.host,employeeMap,getUserText(),getPswdText());
		}
		logIncontroller.setPassword(getPswdText());
		logIncontroller.setUsername(getUserText());
		return logIncontroller;
	}
}
