package controler;

import gui.VCP_Main_Frame;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import clientServer.ClientConsole;

public class LogIn_controller   implements Controller{
	
	private String username;
	private String password;
	//HashMap<String,Integer> loggedin=new HashMap<String,Integer>();
	private ClientConsole c;
	private VCP_Main_Frame
		
	public LogIn_controller(String username,String password){
		this.username=username;
		this.password=password;
	    c=new ClientConsole()
		checkValidity();
	}
	
	public void checkValidity(){
		 
		  if(username.equals("gal") && password.equals("1234")){
			 
			  if(checkedIfAlreadyLoggedIn()==false){
					
				  updateAsLoggedIn();
				  showSeccussesMsg("Login was seccessfully acomplished");
			  }
			  
			  else
				  showSeccussesMsg("You are already loggedin");
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
	
	public boolean checkedIfAlreadyLoggedIn(){
		
		if(loggedin.get(this.username)==1){
			System.out.println("a");
			return true;
		}
			
		
		return false;
	}
	
	public void updateAsLoggedIn(){
		  loggedin.put(this.username,1);
		  //System.out.println(loggedin.get(this.username));
	}

	@Override
	public void ClientSideConnection(Object[] msg) {
		// TODO Auto-generated method stub
		
	}
}
