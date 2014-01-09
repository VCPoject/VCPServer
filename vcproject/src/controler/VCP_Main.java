package controler;  
   
import gui.LogIn_Frame;
import gui.VCP_Main_Frame;

public class VCP_Main {

	public static void main(String[] args) {
		VCP_Main_Frame mainFrame;
		
		if (args.length == 0){
			mainFrame = new VCP_Main_Frame("localhost");
		}
		else{
			mainFrame = new VCP_Main_Frame(args[0].toString());
		}
		mainFrame.setVisible(false);
	}
}

