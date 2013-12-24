package controler;  

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import clientServer.ClientConsole;

public abstract class Controller {
	private ClientConsole server;
	private String host;
	private int port;

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

	public void sendQueryToServer(Object[] msg) {
		openConnection();
		server.accept(msg);
	}

	private void openConnection() {
		if(server == null || !server.isConnected())
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
			server.getClient().closeConnection();
		} catch (IOException e) {
			showWarningMsg("error while closing connection: "+e.getMessage());
		}
	}
	
	public boolean isConnected()
	{
		return server.isConnected();
	}

}
