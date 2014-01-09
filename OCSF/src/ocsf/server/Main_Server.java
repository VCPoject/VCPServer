package ocsf.server;  

import serverGui.Main_Frame;

public class Main_Server {
	
	final public static int DEFAULT_PORT = 5555;

	public static void main(String[] args) {
		int port = 0; // Port to listen on
		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}
		
		Main_Frame mainFrame = new Main_Frame(port);
		mainFrame.setVisible(true);

	}

}
