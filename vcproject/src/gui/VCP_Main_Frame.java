package gui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VCP_Main_Frame extends JFrame {

	/**
	 * 
	 */
	final public int DEFAULT_PORT = 5555;
	final public String host;
	private static final long serialVersionUID = 1L;
	private Main_Panel mainPanel;
	private LogIn_Frame loginframe;
	private Order_Panel orderPanel;
	private Register_Panel registerPanel;
	private Payment_Frame paymentFrame;
	private CheckInOut_Frame CheckInOutFrame;
	private CancelOrder_Panel cancelOrder;
	private LogIn_controller logincontroller;
	private Employee_Panel employee_panel;
	private Complain_Panel complainPanel;
	private ComplainFu_Panel complainFuPanel;
	private ParkingLot_Panel parkingLotPanel;
	private SavingParkingPlace_Panel savingparkingplace;

	public VCP_Main_Frame(String host) {
		super();
		this.host = host;
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this,
					"setLookAndFeel error: " + e.getMessage(),
					"setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getMainPanel());
		getContentPane().setBackground(SystemColor.activeCaption);
		this.setSize(800, 600);
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		listners();
	}

	private void listners() {
		
		getMainPanel().getBtnExit().addActionListener(new ActionListener() {/*
																			 * Exit
																			 * Button
																			 * Listener
																			 */
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();

				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					// getLogincontroller().updateAsNotLoggedIn();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					closeMainFrame();
					System.exit(0);
				}

			}
		});

		getMainPanel().getBtnEmploeyLogin().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getLogIn_Frame();
						getLogIn_Frame().setVisible(true);
						getLogIn_Frame().addWindowListener(new WindowAdapter() {
							public void windowClosed(WindowEvent arg0) {
								loginframe = null;
							}
						});
						getLogIn_Frame().getLogIn_Panel().getBtnExit()
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										JFrame frame = new JFrame();
										int result = JOptionPane
												.showConfirmDialog(
														frame,
														"Are you sure you want to exit the application?",
														"Exit Application",
														JOptionPane.YES_NO_OPTION);
										if (result == JOptionPane.YES_OPTION) {
											//getLogincontroller().updateAsNotLoggedIn();
											frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
											getLogIn_Frame().closeLoginFrame();
											logincontroller = null;
										}
									}
								});

						getLogIn_Frame().getLogIn_Panel().getBtnSubmit()
								.addActionListener(new ActionListener() {
									
									public void actionPerformed(ActionEvent e) {
										if (getLogIn_Frame().getLogIn_Panel().checkValidity()) {
											getLogIn_Frame().closeLoginFrame();
											setContentPane(getEmployeePanel());
										}
									}
								});
					}
				});

		getMainPanel().getBtnMakeOrder().addActionListener(
				new ActionListener() {/* Main Panel Make Order Button Listener */
					public void actionPerformed(ActionEvent e) {
						setContentPane(getOrderPanel());
						getOrderPanel().getBtnReturn().addActionListener(
								new ActionListener() {/*
													 * Order Return Button
													 * Listener
													 */
									public void actionPerformed(ActionEvent e) {
										setContentPane(getMainPanel());
									}
								});
					}

				});

		getMainPanel().getBtnRegister().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getRegisterPanel());
				getRegisterPanel().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								setContentPane(getMainPanel());
							}
						});
				getRegisterPanel().getBtnSubmit().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								getPaymentFrame();
								getPaymentFrame().setVisible(true);
								disableMainFrame();
							}
						});

				getPaymentFrame().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								getPaymentFrame().dispose();
								enableMainFrame();
							}
						});
			}
		});

		mainPanel.getBtnCheckIn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getCheckInFrame(true);
				getCheckInFrame(true).setVisible(true);
				disableMainFrame();
				getCheckInFrame(true).getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								getCheckInFrame(true).dispose();
								enableMainFrame();
								CheckInOutFrame = null;
							}
						});
			}
		});

		mainPanel.getBtnCheckOut().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCheckInFrame(false);
				getCheckInFrame(false).setVisible(true);
				disableMainFrame();
				getCheckInFrame(false).getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								getCheckInFrame(false).dispose();
								enableMainFrame();
								CheckInOutFrame = null;
							}
						});
			}
		});

		mainPanel.getBtnCancelOrder().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(getCancelOrderPanel());
				getCancelOrderPanel().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setContentPane(getMainPanel());
							}
						});
			}
		});

		mainPanel.getBtnComplain().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(getComplain_Panel());
				getComplain_Panel().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setContentPane(getMainPanel());
							}
						});
			}
		});

		mainPanel.getBtnComplainFu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(getComplainFu_Panel());
				getComplainFu_Panel().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setContentPane(getMainPanel());
							}
						});

			}
		});
		getEmployeePanel().getbtnSaveParkin().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				setContentPane(getParkingLot_Panel());
			}
		});
		
		getMainPanel().getBtnDofek().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getParkingLot_Panel());
			}
		});
			
			
		
	}

	private void closeMainFrame() {
		this.setVisible(false);
		this.dispose();
	}

	public Main_Panel getMainPanel() {

		if (mainPanel == null)
			mainPanel = new Main_Panel();

		return mainPanel;
	}

	public CancelOrder_Panel getCancelOrderPanel() {

		if (cancelOrder == null) {
			cancelOrder = new CancelOrder_Panel();
		}
		return cancelOrder;
	}

	public LogIn_Frame getLogIn_Frame() {

		if (loginframe == null)
			loginframe = new LogIn_Frame(host);

		return loginframe;
	}

	private Order_Panel getOrderPanel() {
		if (orderPanel == null) {
			orderPanel = new Order_Panel(this.host, DEFAULT_PORT);
		}
		return orderPanel;
	}

	public Register_Panel getRegisterPanel() {
		if (registerPanel == null) {
			registerPanel = new Register_Panel();
		}
		return registerPanel;
	}

	public Payment_Frame getPaymentFrame() {
		if (paymentFrame == null) {
			paymentFrame = new Payment_Frame();
		}
		return paymentFrame;
	}

	public CheckInOut_Frame getCheckInFrame(boolean isCheckIn) {
		if (CheckInOutFrame == null)
			CheckInOutFrame = new CheckInOut_Frame(isCheckIn);
		return CheckInOutFrame;
	}

	public Employee_Panel getEmployeePanel() {
		if (employee_panel == null)
			employee_panel = new Employee_Panel();
		return employee_panel;
	}

	protected void disableMainFrame() {
		this.setEnabled(false);
		this.setFocusable(false);
		this.setVisible(false);
	}

	protected void enableMainFrame() {
		this.setEnabled(true);
		this.setFocusable(true);
		this.setVisible(true);
	}

	public ParkingLot_Panel getParkingLot_Panel() {
		if (parkingLotPanel == null)
			parkingLotPanel = new ParkingLot_Panel(8);
		return parkingLotPanel;
	}

	public Complain_Panel getComplain_Panel() {
		if (complainPanel == null) {
			complainPanel = new Complain_Panel(this.host, DEFAULT_PORT);
		}
		return complainPanel;
	}

	public ComplainFu_Panel getComplainFu_Panel() {
		if (complainFuPanel == null) {
			complainFuPanel = new ComplainFu_Panel(this.host, DEFAULT_PORT);
		}
		return complainFuPanel;
	}
	
	public SavingParkingPlace_Panel getSavingParkingPlace_Panel(){
		if(savingparkingplace==null)
			savingparkingplace=new SavingParkingPlace_Panel();
		
		return savingparkingplace;
	}
}
