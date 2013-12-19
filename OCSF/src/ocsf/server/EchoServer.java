package ocsf.server; 

// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ocsf.server.*;
import serverGui.Main_Frame;
import sun.security.jca.GetInstance.Instance;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 * 
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 * 
	 * @param port
	 *            The port number to connect on.
	 */
	public EchoServer(int port) {
		super(port);
	}

	
	public int getNumberOfConnections(){
		return getNumberOfClients();
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 * 
	 * @param msg
	 *            The message received from the client.
	 * @param client
	 *            The connection from which the message originated.
	 */

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		try {
			MySqlConnection toDB = new MySqlConnection();
			toDB.update(toDB.getConn(), (Object[]) msg);
			this.sendToAllClients(toDB.getResult());
		} catch (Exception e) {
			System.out.println("handleMessageFromClient error:"
					+ e.getMessage());
		}

	}

	public void handleMessageFromClient(Object[] msg, ConnectionToClient client) {
		try {
			MySqlConnection toDB = new MySqlConnection();
			toDB.update(toDB.getConn(), msg);
			this.sendToAllClients(toDB.getResult());
		} catch (Exception e) {
			System.out.println("handleMessageFromClient error:"
					+ e.getMessage());
		}

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port "
				+ getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * stops listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************
}
// End of EchoServer class
