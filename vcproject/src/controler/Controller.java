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
import entity.Order;
import entity.Parking_Places;

public abstract class Controller {
	final public static int DEFAULT_PORT = 5555;
	private ClientConsole server;
	private String host;
	private int port;

	public Controller() {
		this("localhost", DEFAULT_PORT);
	}

	public Controller(String host) {
		this(host, DEFAULT_PORT);
	}

	public Controller(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void showWarningMsg(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg, "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showSeccussesMsg(String msg) {
		JFrame frame = new JFrame();
		JOptionPane
				.showMessageDialog(frame, msg, "", JOptionPane.PLAIN_MESSAGE);
	}

	public void sendQueryToServer(Object entity) {
		openConnection();
		Object[] toServer = { null };
		if (entity instanceof Parking_Places) {
			toServer = ((Parking_Places) entity).toObject();
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
		}else if (entity instanceof Object[]) {
			toServer = (Object[]) entity;
		}
		server.accept(toServer);

	}

	private void openConnection() {
		if (server == null || !server.isConnected())
			server = new ClientConsole(this.host, this.port);
	}

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

	public void closeConnection() {
		try {
			if(isConnected())
				server.getClient().closeConnection();
		} catch (IOException e) {
			showWarningMsg("error while closing connection: " + e.getMessage());
		}
	}

	public boolean isConnected() {
		if(server != null)
			return server.isConnected();
		else return false;
	}

}
