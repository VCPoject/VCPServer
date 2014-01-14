package controler;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import clientServer.ClientConsole;
import entity.Car;
import entity.ClientEntity;
import entity.Employee;
import entity.FinancialCard;
import entity.NotWorkingPlaces;
import entity.Order;
import entity.Parking_Places;
import entity.Reservation;
import entity.Subscribe;

public abstract class Controller {
	final public static int DEFAULT_PORT = 5555;
	/**
	 * server for connecting to server side
	 */
	private ClientConsole server;
	/**
	 * host for connecting the server
	 */
	private String host;
	/**
	 * port for connecting the server
	 */
	private int port;

	/**
	 * Controller is the basic controller for all the system controllers
	 * this constructor connect with default port that is 5555 and with local host
	 */
	public Controller() {
		this("localhost", DEFAULT_PORT);
	}

	/**
	 * Controller is the basic controller for all the system controllers
	 * this constructor connect with default port that is 5555
	 * @param host for connecting the server
	 * 
	 */
	public Controller(String host) {
		this(host, DEFAULT_PORT);
	}

	/**
	 * Controller is the basic controller for all the system controllers
	 * @param host for connecting the server
	 * @param port for connecting the server
	 */
	public Controller(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * showWarningMsg display pop up massage of warning
	 * @param msg to be display
	 */
	public void showWarningMsg(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg, "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * showSeccussesMsg display pop up massage of success
	 * @param msg to be display
	 */
	public void showSeccussesMsg(String msg) {
		JFrame frame = new JFrame();
		JOptionPane
				.showMessageDialog(frame, msg, "", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * sendQueryToServer send entity to server by identify the instance of the entity
	 * @param entity to be send to server
	 */
	public void sendQueryToServer(Object entity) {
		openConnection();
		Object[] toServer = { null };
		if (entity instanceof Parking_Places) {
			toServer = ((Parking_Places) entity).toObject();
		}else if (entity instanceof Subscribe) {
			toServer = ((Subscribe) entity).toObject();
		} else if (entity instanceof ClientEntity) {
			toServer = ((ClientEntity) entity).toObject();
		} else if (entity instanceof Car) {
			toServer = ((Car) entity).toObject();
		} else if (entity instanceof Order) {
			toServer = ((Order) entity).toObject();
		}else if (entity instanceof Employee) {
			toServer = ((Employee) entity).toObject();
		}else if (entity instanceof FinancialCard) {
			toServer = ((FinancialCard) entity).toObject();
		}else if(entity instanceof Reservation){
			toServer=((Reservation) entity).toObject();
		}else if(entity instanceof NotWorkingPlaces){
			toServer=((NotWorkingPlaces) entity).toObject();
		}else if (entity instanceof Object[]) {
			toServer = (Object[]) entity;	
		}
		server.accept(toServer);

	}

	/**
	 * openConnection is opening connection to the server side using host and port parameters
	 * that was defined in the constructor
	 */
	private void openConnection() {
		if (server == null || !server.isConnected())
			server = new ClientConsole(this.host, this.port);
	}

	/**
	 * getResult is getting result from server side.
	 * @return result from the sever side
	 */
	public ArrayList<Object> getResult() {
		ArrayList<Object> result = null;
		boolean flag = false;
		while (!flag) {
			try {
				result = server.getResult();
				if (result.get(0).equals("done") || result != null) {
					flag = true;
				}
			} catch (Exception e2) {
				result = null;
				flag = false;
			}

		}
		flag = false;
		return result;
	}

	/**
	 * closeConnection is closing the connection with the server side
	 * only if the connection is connected
	 */
	public void closeConnection() {
		try {
			if(isConnected())
				server.getClient().closeConnection();
		} catch (IOException e) {
			showWarningMsg("error while closing connection: " + e.getMessage());
		}
	}

	/**
	 * isConnected is checking if the connection to server is open
	 * @return true if the connection to server is open, else false.
	 */
	public boolean isConnected() {
		if(server != null)
			return server.isConnected();
		else return false;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

}
