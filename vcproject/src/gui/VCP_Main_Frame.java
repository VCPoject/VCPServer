package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controler.Reminder;
public class VCP_Main_Frame extends JFrame {

	/**
	 * 
	 */
	final public int DEFAULT_PORT = 5555;
	public String host = null;
	private static final long serialVersionUID = 1L;
	private Main_Panel mainPanel;
	private LogIn_Panel loginpanel;
	private Order_Panel orderPanel;
	private Register_Panel registerPanel;
	private Payment_Frame paymentFrame;
	private CheckInOut_Frame CheckInOutFrame;
	private CancelOrder_Panel cancelOrder;
	private Complain_Panel complain;
	private ComplainFu_Panel complainFu;

	public VCP_Main_Frame() {
	}

	public VCP_Main_Frame(String[] args) {
		super();

		try {
			host = args[0];
		}

		catch (Exception e) {
			this.host = "localhost";
		}
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
			
				loginframe.getLogIn_Panel().getBtnReturn().addActionListener(new ActionListener(){

							public void windowClosed(WindowEvent arg0) {
								loginframe = null;
							}
						});

						getLogIn_Frame().getLogIn_Panel().getBtnExit()
								.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						loginframe.dispose();
						enableMainFrame();
										int result = JOptionPane
												.showConfirmDialog(
														frame,
														"Are you sure you want to exit the application?",
														"Exit Application",
														JOptionPane.YES_NO_OPTION);

										if (result == JOptionPane.YES_OPTION) {
											frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
											getLogIn_Frame().closeLoginFrame();
											logincontroller = null;
										}
					}
					
				});
				
				loginframe.getLogIn_Panel().getBtnSubmit().addActionListener(new ActionListener(){
								.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						 
						String username=loginframe.getLogIn_Panel().getUserText().getText();
					}
					
				});
				
				loginframe.getLogIn_Panel().getPswdText().addKeyListener(new KeyAdapter(){
								.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e){
				        if (e.getKeyCode() == KeyEvent.VK_ENTER)
				        	loginframe.getLogIn_Panel().getBtnSubmit().doClick();
													.getBtnSubmit().doClick();
				        
				     }
				 });

				loginframe.getLogIn_Panel().getUserText().addKeyListener(new KeyAdapter(){
								.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e){
				        if (e.getKeyCode() == KeyEvent.VK_ENTER)
				        	loginframe.getLogIn_Panel().getBtnSubmit().doClick();
													.getBtnSubmit().doClick();
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

		mainPanel.getBtnRegister().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getRegisterPanel());
				getRegisterPanel().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								setContentPane(getMainPanel());
							}
						});
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

		getLogIn_Panel().getBtnReturn().addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				setContentPane(getMainPanel());
				
			}
			
		});
		
		getLogIn_Panel().getBtnSubmit().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				 
				
				System.out.println(getLogIn_Panel().getPswdText().getText());
				 System.out.println(getLogIn_Panel().getUserText().getText());
			}
			
		});
		
		getLogIn_Panel().getPswdText().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
		        if (e.getKeyCode() == KeyEvent.VK_ENTER)
		        	getLogIn_Panel().getBtnSubmit().doClick();
		        
		     }
		 });
		
		getLogIn_Panel().getUserText().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
		        if (e.getKeyCode() == KeyEvent.VK_ENTER)
		        	getLogIn_Panel().getBtnSubmit().doClick();
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
			}
		});

		getCancelOrderPanel().getBtnReturn().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setContentPane(getMainPanel());
					}
				});
		
		mainPanel.getBtnComplain().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			setContentPane(getComplainPanel());
		}
		});
		
		getComplainPanel().getBtnReturn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getMainPanel());
			}
		});
		
		mainPanel.getBtnComplainFu().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			setContentPane(getComplainPanelFu());
		}
		});
		
		getComplainPanelFu().getBtnReturn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getMainPanel());
			}
		});
	}
	

	public   Complain_Panel getComplainPanel() {
		if (complain == null) {
			complain = new Complain_Panel();
		}
		return complain;
		
	}
	
	public   ComplainFu_Panel getComplainPanelFu() {
		if (complainFu == null) {
			complainFu = new ComplainFu_Panel();
		}
		return complainFu;
		
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

	public LogIn_Panel getLogIn_Panel() {

		if(loginpanel==null)
			loginpanel=new LogIn_Panel();

		return loginpanel; 
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

	public LogIn_controller getLogincontroller() {
		if (logincontroller == null)
			logincontroller = new LogIn_controller(getLogIn_Frame()
					.getLogIn_Panel().getUserText().getText(), getLogIn_Frame()
					.getLogIn_Panel().getPswdText().getText());
		return logincontroller;
	}

	public String getHost() {
		return host;
	}
}
