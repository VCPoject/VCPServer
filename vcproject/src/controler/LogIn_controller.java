package controler;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LogIn_controller   implements Controller{
	
	private String username;
	private String password;
	HashMap<String,Integer> loggedin=new HashMap<String,Integer>();
		
	public LogIn_controller(String username,String password){
		this.username=username;
		this.password=password;
		loggedin.put(this.username,0);
	}
	
	public void checkValidity(){
		 
		  if(username.equals("gal") && password.equals("1234")){
			  showSeccussesMsg("Login was seccessfully acomplished");
			  loggedin.put(this.username,1);
		  }
		  
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
	
	public void checkedIfAlreadyLoggedIn(){
		if(loggedin.get(this.username)==1)
			showWarningMsg("You are already loggedin");
	}
}
