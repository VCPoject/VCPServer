package gui;
 
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;

public class LogIn_Panel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField usertxtfieled;
	private JTextField pswdtxtfield;
	private JButton btnexit;
	private JButton btnsubmmit;
	
	

	public LogIn_Panel(){
		super();
		initialize();
		listners();
	}
	
	private void initialize(){
		this.setBounds(10, 11, 455, 342);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		usertxtfieled = new JTextField();
		usertxtfieled.setText("sapir");
		usertxtfieled.setBounds(136, 132, 228, 20);
		add(usertxtfieled);
		usertxtfieled.setColumns(10);
		
		 pswdtxtfield = new JTextField();
		 pswdtxtfield.setText("1234");
		 pswdtxtfield.setBounds(136, 217, 228, 20);
		 add( pswdtxtfield);
		 pswdtxtfield.setColumns(10);
		
		btnsubmmit = new JButton("Submmit");
		btnsubmmit.setBounds(307, 274, 138, 56);
		add(btnsubmmit);
		
		btnexit= new JButton("Exit\r\n");
		btnexit.setBounds(10, 274, 138, 56);
		add(btnexit);
		
		
		
		JLabel lblUserName = new JLabel("Username\r\n");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblUserName.setBounds(22, 123, 104, 33);
		add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPassword.setBounds(22, 216, 94, 17);
		add(lblPassword);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblLogIn.setBounds(158, 39, 138, 46);
		add(lblLogIn);
		
	}
	
	public void listners(){
	}
	
	public JButton getBtnSubmit(){
		return btnsubmmit;
	}
	
	public JButton getBtnExit(){
		return btnexit;
	}
	
	public JTextField getPswdText(){
		return pswdtxtfield;
	}
	
	public JTextField getUserText(){
		return usertxtfieled;
	}

}
