package gui;

import javax.swing.*;
import controler.EmpComplainController;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EmpComplainGui extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnReturn;
	private JTable table;
	private EmpComplainController empController;
	private String host;
	private int port = 5555;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JLabel lblComplains;
	private JLabel lblResponse;
	private JLabel lblRefound;
	private JButton btnSubmit;
	private JTextArea textArea;

	public EmpComplainGui(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		initialize();
		listners();
	}

	private void initialize() {
		setLayout(null);
		this.setSize(785, 575);
		setBackground(Color.LIGHT_GRAY);

		btnReturn = new JButton("Return");
		btnReturn.setBounds(10, 519, 93, 35);
		add(btnReturn);

		JLabel lblCustomerSupport = new JLabel("Customer Support - Complains");
		lblCustomerSupport.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerSupport.setBounds(255, 11, 274, 22);
		add(lblCustomerSupport);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(121, 62, 654, 268);
		add(scrollPane);

		table = new JTable(getEmpComplainController().obtainValues(),
				getEmpComplainController().obtainFields()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data, int columnNames) {
				return false;
			}

		};
		table.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(672, 518, 93, 35);
		add(btnSubmit);

		textArea = new JTextArea();
		textArea.setBounds(200, 353, 575, 84);
		add(textArea);

		textField = new JTextField();
		textField.setBounds(200, 448, 178, 35);
		add(textField);
		textField.setColumns(10);

		lblComplains = new JLabel("Complains:");
		lblComplains.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComplains.setBounds(10, 62, 101, 35);
		add(lblComplains);

		lblResponse = new JLabel("Response:");
		lblResponse.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblResponse.setBounds(24, 352, 103, 22);
		add(lblResponse);

		lblRefound = new JLabel("Refound:");
		lblRefound.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRefound.setBounds(24, 458, 103, 22);
		add(lblRefound);
	}

	private void listners() {
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				empController.complainReplay(
						table.getValueAt(table.getSelectedRow(), 0),
						table.getValueAt(table.getSelectedRow(), 1),
						textField.getText(), textArea.getText());
				btnSubmit.setEnabled(false);

			}
		});

	}

	public EmpComplainController getEmpComplainController() {
		if (empController == null || !empController.isConnected()) {
			empController = new EmpComplainController(host, port);
		}

		return empController;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}
}