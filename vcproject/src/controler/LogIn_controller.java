package controler;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LogIn_controller   implements Controller{
	
	private String username;
	private String password;
	
	public LogIn_controller(String username,String password){
		this.username=username;
		this.password=password;
	}
	
	public void checkValidity(){
		 
		  if(username.equals("gal") && password.equals("1234"))
			  showSeccussesMsg("LogIn was seccessfully acomplished");
		  
		  if(!password.equals("1234") && username.equals("gal"))
			showWarningMsg("Invalid password");
		
		  if(!username.equals("gal") && password.equals("1234"))
			showWarningMsg("Invalid username");
		  
		  if(!username.equals("gal") && !password.equals("1234"))
			  showWarningMsg("Invalid username and password");
	  
	 
			
	}
	
	public void showWarningMsg(String msg){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,msg,"Warning",JOptionPane.WARNING_MESSAGE);
	} 
	
	public void showSeccussesMsg(String msg){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,msg,"",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void stam(){};
	
	
}
