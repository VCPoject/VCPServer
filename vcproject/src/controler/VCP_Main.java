package controler;
   
import gui.VCP_Main_Frame;

public class VCP_Main {

	/**
	 * This is the main of all system.
	 * @param args for getting host and port
	 */
	public static void main(String[] args) {
		VCP_Main_Frame mainFrame;
		
		if (args.length == 0){
			mainFrame = new VCP_Main_Frame("192.168.16.78");
		}
		else{
			mainFrame = new VCP_Main_Frame(args[0].toString());
		}
		mainFrame.setVisible(false);
	}
}

