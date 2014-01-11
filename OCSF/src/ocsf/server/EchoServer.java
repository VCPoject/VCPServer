package ocsf.server; 

// This file contains material supporting section 3.7 of the textbook:// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

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
	 * @param dbPassword 
	 * @param dbUser 
	 * @param dbIp 
	 */
	
	private String dbIp;
	private String dbUser;
	private String dbPassword;
	public EchoServer(int port, String dbIp, String dbUser, String dbPassword) {
		super(port);
		this.dbIp = dbIp;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
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
			MySqlConnection toDB = new MySqlConnection(dbIp,dbUser,dbPassword);
			toDB.update(toDB.getConn(), (Object[]) msg);
			this.sendToAllClients(toDB.getResult());
		} catch (Exception e) {
			System.out.println("handleMessageFromClient error:"	+ e.getMessage());
		}

	}

	public void handleMessageFromClient(Object[] msg, ConnectionToClient client) {
		try {
			MySqlConnection toDB = new MySqlConnection(dbIp,dbUser,dbPassword);
			toDB.update(toDB.getConn(), msg);
			this.sendToAllClients(toDB.getResult());
			toDB.resultReset();
		} catch (Exception e) {
			System.out.println("handleMessageFromClient error:"	+ e.getMessage());
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
