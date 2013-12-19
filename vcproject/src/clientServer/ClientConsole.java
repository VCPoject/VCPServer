package clientServer;

// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.sql.Time;
import java.util.*;

import common.*;
import client.*;

/**
 * This class constructs the UI for a chat client. It implements the chat
 * interface in order to activate the display() method. Warning: Some of the
 * code here is cloned in ServerConsole
 * 
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientConsole implements ClientIF {
	// Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;
	private ArrayList<Object> result = new ArrayList<Object>();
	// Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	Client client;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the ClientConsole UI.
	 * 
	 * @param host
	 *            The host to connect to.
	 * @param port
	 *            The port to connect on.
	 */
	public ClientConsole(String host, int port) {
		try {
			client = new Client(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!"
					+ " Terminating client.");
			System.exit(1);
		}
	}

	// Instance methods ************************************************

	/**
	 * This method waits for input from the console. Once it is received, it
	 * sends it to the client's message handler.
	 */
	public void accept(Object[] msg) {
		try {
			Object[] message;
			message=msg;
			//while (true) {
				Thread.sleep(10);
				if (message != null) {
					client.handleMessageFromClientUI(message);
					message=null;
				}

			//}
		} catch (Exception ex) {
			ArrayList<Object> error = new ArrayList<Object>();
			String errMsg = "Unexpected error while reading from console! error: " + ex.getMessage();
			error.add(errMsg);
			setResult(error);
		}
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 * 
	 * @param message
	 *            The string to be displayed.
	 */
	public void display(String msg) {
		ArrayList<Object> message = new ArrayList<Object>();
		message.add(msg);
		display(message);
	}
	
	public void display(ArrayList<Object> message) {
		System.out.println("> " + message);
		setResult(message);
	}

	private void setResult(ArrayList<Object> message) {
		this.result = message;
	}
	
	public ArrayList<Object> getResult() {
		//ArrayList<Object> resultCopy = new ArrayList<Object>();
		//Collections.copy(this.result, resultCopy);
		//result = null;
		//System.out.println(result.get(0).toString());
		return result;
	}

	// Class methods ***************************************************
}
// End of ClientConsole class
