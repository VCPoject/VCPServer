package gui;  

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import javax.swing.UnsupportedLookAndFeelException;
import controler.VcpInfo;
import entity.Parking_Lot;
import java.awt.image.BufferedImage;
import java.io.File;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VCP_Main_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	/**DEFAULT_PORT is the port of the server side*/
	final public int DEFAULT_PORT = 5555;
	/**host is the host of the server side*/
	final public String host;
	/** vcpInfo is a controller that run on start-up of the 
	 * application and download all the info form the DB
	 * its contains all:
	 * order,subscribed,reservation,employees,parking lot,
	 * parking places,clients,default parking lot,cars
	 */
	private VcpInfo vcpInfo;
	
	/**
	 * defaultParkinglotNum contain the current parking lot number
	 */
	private int defaultParkinglotNum;
	private Main_Panel mainPanel;
	private LogIn_Frame loginframe;
	private Order_Panel orderPanel;
	private Register_Panel registerPanel;
	private CheckInOut_Frame CheckInOutFrame;
	private CancelOrder_Panel cancelOrder;
	private Employee_Panel employee_panel;
	private Complain_Panel complainPanel;
	private ComplainFu_Panel complainFuPanel;
	private ParkingLot_Panel parkingLotPanel;
	private SavingParkingPlace_Panel savingparkingplace;
	private NotWorkingPlaces_Panel notworkingplaces;
	private FindAltParkingLot findaltparkinglot;
	private ResubscribePanel resubscribePanel;
	private EmpComplainGui empComplainGui;
	private ChangePricingPanel changePricingPanel;
	private PricingRequestPanel pricingRequestPanel;
	private ParkingLotInit parkinglotinit;
	private StatisticsPanel statisticsPanel;
	private ReportsGui reports;
	private QuarterlyGui quaterly;
	private SystemData systemData;
	
	 
	/**This is the main frame of the VCP system
	 * @param host for make connection with server side
	 */
	public VCP_Main_Frame(String host) {
		super();
		this.host = host;
		getVcpInfo();
		initializeParkingLot();
	}
	
	

	/**For initilize the parking lot to know where the system is
	 * 
	 */
	public void initializeParkingLot() {
		getLogIn_Frame();
		getLogIn_Frame().setVisible(true);
		getLogIn_Frame().getLogIn_Panel().getBtnSubmit().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (getLogIn_Frame().getLogIn_Panel().checkValidity()){
					getLogIn_Frame().getLogIn_Panel().getLogincontroller().updateAsNotLoggedIn();
					getLogIn_Frame().getLogIn_Panel().getLogincontroller().closeConnection();
						getLogIn_Frame().setContentPane(getParkingLotInit());
				}
							
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
								frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								getLogIn_Frame().closeLoginFrame();
								System.exit(0);
							}
						}
					});
		
		getParkingLotInit().getbtnSave().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 defaultParkinglotNum=(Integer.parseInt(getParkingLotInit().getcomboBoxParkingLot().getSelectedItem().toString()));
				 getVcpInfo().setDefultParkingLot(getVcpInfo().getParkingLotInfo().get(defaultParkinglotNum - 1));
				for(Parking_Lot parkinglot: getVcpInfo().getParkingLot())
					
					if(parkinglot.getIdparkinglot()== defaultParkinglotNum){
						getVcpInfo().setDefultParkingLot(parkinglot);
						getLogIn_Frame().closeLoginFrame();
						parkinglotinit = null;
						loginframe=null;
						initialize();
					}
			}
			 
		 });
		}

	/**
	 * Initialize the frame of VCP
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this,
					"setLookAndFeel error: " + e.getMessage(),
					"setLookAndFeel ERRORE", JOptionPane.ERROR_MESSAGE);
		}
		setTitle("VCP Project");
		setDefaultCloseOperation(VCP_Main_Frame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		this.setContentPane(getMainPanel());
		getContentPane().setBackground(SystemColor.activeCaption);
		this.setSize(800, 600);
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		listners();
	}
	
	/**
	 * Listeners for switch panels by pressing on the buttens
	 */
	private void listners() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				getMainPanel().getBtnExit().doClick();
			}
		});
		
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

		getMainPanel().getBtnEmploeyLogin().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getLogIn_Frame();
						getLogIn_Frame().setVisible(true);
						getLogIn_Frame().getLogIn_Panel().getBtnExit().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JFrame frame = new JFrame();
								int result = JOptionPane.showConfirmDialog(frame,
												"Are you sure you want to exit the application?",
												"Exit Application", JOptionPane.YES_NO_OPTION);
								if (result == JOptionPane.YES_OPTION) {
									frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									getLogIn_Frame().getLogincontroller().closeConnection();
									getLogIn_Frame().closeLoginFrame();
									loginframe = null;
								}
							}
						});
						
						getLogIn_Frame().getLogIn_Panel().getBtnSubmit().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (getLogIn_Frame().getLogIn_Panel().checkValidity()){
									getEmployeePanel().setConectedEmployee(getLogIn_Frame().getConnectedEmployee());
									getEmployeePanel().setBtnEnableByEmpRole(); 
									getLogIn_Frame().closeLoginFrame();
									loginframe=null;
									setContentPane(getEmployeePanel());
									getEmployeePanel().getbtnParkingStatus().addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											setContentPane(getParkingLot_Panel());
											getParkingLot_Panel().getBtnMakePdf().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													makePanelImage(getParkingLot_Panel().getPanelFloor1(),"Floor1");
													makePanelImage(getParkingLot_Panel().getPanelFloor2(),"Floor2");
													makePanelImage(getParkingLot_Panel().getPanelFloor3(),"Floor3");
													makePanelImage(getParkingLot_Panel().getPanelButtons(),"buttons");
													makePdfFile();
												}
											});
											getParkingLot_Panel().getBtnReturn().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													setContentPane(getEmployeePanel());
													parkingLotPanel = null;
												}
											});
										}
									});
									
									getEmployeePanel().getbtnSaveParkin().addActionListener(new ActionListener() {

										public void actionPerformed(ActionEvent arg0) {
											setContentPane(getSavingParkingPlace_Panel());
											getSavingParkingPlace_Panel().getBtnExit().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													JFrame frame = new JFrame();
													int result = JOptionPane.showConfirmDialog(frame,
																	"Are you sure you want to exit the application?",
																	"Exit Application",
																	JOptionPane.YES_NO_OPTION);
														if (result == JOptionPane.YES_OPTION) {
															setContentPane(getEmployeePanel());
															getSavingParkingPlace_Panel().getParkingLot_controller().closeConnection();
															savingparkingplace=null;
														}
													}
											});
										}
									});
									
									getEmployeePanel(). getbtnExit().addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											JFrame frame = new JFrame();
											int result = JOptionPane.showConfirmDialog(frame,
															"Are you sure you want to exit the application?",
															"Exit Application",	JOptionPane.YES_NO_OPTION);
											if (result == JOptionPane.YES_OPTION) {
												getLogIn_Frame().getLogIn_Panel().getLogincontroller().updateAsNotLoggedIn(getEmployeePanel().getConectedEmployee());
												setContentPane(getMainPanel());
												getLogIn_Frame().getLogIn_Panel().getLogincontroller().closeConnection();
												employee_panel = null;
											}
										}
									});
									
									getEmployeePanel().getbtnSignAsnotWorking().addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											setContentPane(getNotWorkingPlaces_Panel());
											getNotWorkingPlaces_Panel().getbtnExit().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													JFrame frame = new JFrame();
													int result = JOptionPane
															.showConfirmDialog(
																	frame,
																	"Are you sure you want to exit from not working places?",
																	"Exit Application",
																	JOptionPane.YES_NO_OPTION);
														if (result == JOptionPane.YES_OPTION) {
															getNotWorkingPlaces_Panel().getParkingLot_controller().closeConnection();
															setContentPane(getEmployeePanel());
															notworkingplaces=null;
														}
													}
											});
											
										}
									});
									
									getEmployeePanel().getbtnFindAltParkin().addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											setContentPane(getFindaltparkinglot());
											getFindaltparkinglot().getbtnExit().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													JFrame frame = new JFrame();
													int result = JOptionPane
															.showConfirmDialog(frame,
																	"Are you sure you want to exit from find alternative parking lot?",
																	"Exit Application",	JOptionPane.YES_NO_OPTION);
														if (result == JOptionPane.YES_OPTION) {
															getFindaltparkinglot().getParkingLot_controller().closeConnection();
															setContentPane(getEmployeePanel());
															findaltparkinglot=null;
														}
												}
											});
										}
									});
									
									/* 	Setting complains panel and listener to return button */
									getEmployeePanel().getbtnComplains().addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											setContentPane(getEmpComplainGui());
											getEmpComplainGui().getBtnReturn().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													setContentPane(getEmployeePanel());
													getEmpComplainGui().getEmpComplainController().closeConnection();
													empComplainGui = null;
												}
											});
										}
									});
									
									getEmployeePanel().getBtnChangePricing().addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											getChangePricingPanel();
											getChangePricingPanel().setConectedEmployee(getEmployeePanel().getConectedEmployee());
											setContentPane(getChangePricingPanel());
											/* button return listener */
											getChangePricingPanel().getBtnReturn().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													setContentPane(getEmployeePanel());
													getChangePricingPanel().getPricingController().closeConnection();
													changePricingPanel = null;
												}
											});
											
										}
									});
									
									getEmployeePanel().getbtnCreateReport().addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											setContentPane(getReportsGui());
											getReportsGui().getBtnReturn().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													getReportsGui().getManagerStats().closeConnection();
													setContentPane(getEmployeePanel());
													reports = null;
												}
											});
										}

										});
										
									
									getEmployeePanel().getBtnReviewPricingRequests().addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											setContentPane(getPricingRequestPanel());
											getPricingRequestPanel().getBtnReturn().addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent e) {
													setContentPane(getEmployeePanel());
													getPricingRequestPanel().getPricingController().closeConnection();
													pricingRequestPanel = null;
												}
											});
										}
									});
									
									
								}
							}
						});

						
					}
				});

		/* Main Panel Make Order Button Listener */
		getMainPanel().getBtnMakeOrder().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setContentPane(getOrderPanel());
						getOrderPanel().getBtnReturn().addActionListener(
								new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										getOrderPanel().getMakeOrderController().closeConnection();
										setContentPane(getMainPanel());
										orderPanel = null;
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
								getRegisterPanel().getRegisterController().closeConnection();
								setContentPane(getMainPanel());
								registerPanel = null;
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
								getCheckInFrame(true).getCheckInPanel().getCheckInController().closeConnection();
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
								getCheckInFrame(false).getCheckOutPanel().getCheckOutController().closeConnection();
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
								getCancelOrderPanel().getCancelOrderController().closeConnection();
								setContentPane(getMainPanel());
								cancelOrder = null;
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
								getComplain_Panel().getComplainController().closeConnection();
								setContentPane(getMainPanel());
								complainPanel = null;
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
								getComplainFu_Panel().getComplainController().closeConnection();
								setContentPane(getMainPanel());
								complainFuPanel = null;
							}
						});

			}
		});
		
		getMainPanel().getBtnResubscribe().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(getResubscribePanel());
				getResubscribePanel().getBtnReturn().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getResubscribePanel().getRegisterController().closeConnection();
						setContentPane(getMainPanel());
						resubscribePanel = null;
					}
				});
			}
		});
		
		
			
		getEmployeePanel().getbtnStatistics().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                    setContentPane(getStatistics());
                    getStatistics().getBtnReturn().addActionListener(
                                    new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                            	getStatistics().getDailyStatistic().closeConnection();
                                                setContentPane(getEmployeePanel());
                                                statisticsPanel = null;
                                            }
                                    });

            }
    });  
		
		getEmployeePanel().getSystemData().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(getSystemData());
				getSystemData().getBtnReturn().addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								getSystemData().getSystemDataControler().closeConnection();
								setContentPane(getEmployeePanel());
								quaterly = null;
								
							}
						});

			}
		});	
    
    getEmployeePanel().getbtnQuarterly().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                    setContentPane(getQuarterlyGui());
                    getQuarterlyGui().getBtnReturn().addActionListener(
                                    new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                            		getQuarterlyGui().getQuarterly().closeConnection();
                                                    setContentPane(getEmployeePanel());
                                                    quaterly = null;
                                                    
                                            }
                                    });

            }
    });
    
    
    
		
		
		
	}

	/**
	 * For closing the main frame
	 */
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
			cancelOrder = new CancelOrder_Panel(host,DEFAULT_PORT,getVcpInfo().getParkingPricingInfo());
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
			orderPanel = new Order_Panel(this.host, DEFAULT_PORT,getVcpInfo());
		}
		return orderPanel;
	}

	public Register_Panel getRegisterPanel() {
		if (registerPanel == null) {
			registerPanel = new Register_Panel(host,DEFAULT_PORT,getVcpInfo());
		}
		return registerPanel;
	}
	
	/**
	 * @param isCheckIn for know if we need to set the check in or out panel
	 * @return the check in\out frame
	 */
	public CheckInOut_Frame getCheckInFrame(boolean isCheckIn) {
		if (CheckInOutFrame == null)
			CheckInOutFrame = new CheckInOut_Frame(host,DEFAULT_PORT,getVcpInfo(),isCheckIn);
		return CheckInOutFrame;
	}

	public Employee_Panel getEmployeePanel() {
		if (employee_panel == null)
			employee_panel = new Employee_Panel();
		return employee_panel;
	}
	
	/**
	 * Disappear the main frame
	 */
	protected void disableMainFrame() {
		this.setEnabled(false);
		this.setFocusable(false);
		this.setVisible(false);
	}

	
	/**
	 * Make the main frame appear
	 */
	protected void enableMainFrame() {
		this.setEnabled(true);
		this.setFocusable(true);
		this.setVisible(true);
	}

	
	public ParkingLot_Panel getParkingLot_Panel() {
		if (parkingLotPanel == null)
			parkingLotPanel = new ParkingLot_Panel(getVcpInfo());
		return parkingLotPanel;
	}

	public Complain_Panel getComplain_Panel() {
		if (complainPanel == null) {
			complainPanel = new Complain_Panel(this.host, DEFAULT_PORT,vcpInfo);
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
			savingparkingplace=new SavingParkingPlace_Panel(this.host, DEFAULT_PORT,getVcpInfo());
		return savingparkingplace;
	}
	
	public NotWorkingPlaces_Panel getNotWorkingPlaces_Panel(){
		if(notworkingplaces==null)
			notworkingplaces=new NotWorkingPlaces_Panel(host,DEFAULT_PORT,getVcpInfo());
		return notworkingplaces;
	}
	
	public FindAltParkingLot getFindaltparkinglot(){
		if(findaltparkinglot==null)
			findaltparkinglot=new FindAltParkingLot(host, DEFAULT_PORT, getVcpInfo());
		return findaltparkinglot;
	}
	
	public ParkingLotInit getParkingLotInit(){
		if(parkinglotinit==null)
			parkinglotinit=new ParkingLotInit(host,getVcpInfo().getParkingLot());
		
		return parkinglotinit;
	}
	
	public VcpInfo getVcpInfo() {
		if(vcpInfo == null)
			vcpInfo = new VcpInfo(host);
		return vcpInfo;
	
	}

	public ResubscribePanel getResubscribePanel() {
		if(resubscribePanel == null){
			resubscribePanel = new ResubscribePanel(host, DEFAULT_PORT, getVcpInfo());
		}
		return resubscribePanel;
	}
	
	public StatisticsPanel getStatisticsPanel() {
		if(statisticsPanel == null){
			statisticsPanel = new StatisticsPanel(host, DEFAULT_PORT);
		}
		return statisticsPanel;
	}



	public ChangePricingPanel getChangePricingPanel() {
		if(changePricingPanel == null){
			changePricingPanel = new ChangePricingPanel(host, DEFAULT_PORT);
		}
		return changePricingPanel;
	}

	
	public ReportsGui getReportsGui() {
		if(reports == null){
			reports = new ReportsGui(host, DEFAULT_PORT, vcpInfo);
		}
		return reports;
	}
	
	
	public QuarterlyGui getQuarterlyGui() {
		if(quaterly == null){
			quaterly = new QuarterlyGui(host, DEFAULT_PORT, vcpInfo,getEmployeePanel().getConectedEmployee());
		}
		return quaterly;
	}


	public PricingRequestPanel getPricingRequestPanel() {
		if(pricingRequestPanel == null){
			pricingRequestPanel = new PricingRequestPanel(host, DEFAULT_PORT,getVcpInfo().getParkingPricingInfo());
		}
		return pricingRequestPanel;
	}
	
	/**
	 * make a component snapshot and save him in the workspace
	 * @param panel for make his snapshot
	 * @param filename of the BMP file to be save
	 */
	private void makePanelImage(Component panel,String filename)
    {
        Dimension size = panel.getSize();
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        try
        {
            ImageIO.write(image, "bmp", new File(filename + ".bmp"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
	public EmpComplainGui getEmpComplainGui() {
		if(empComplainGui == null)
		{
			empComplainGui = new EmpComplainGui(host, DEFAULT_PORT);
		}
		return empComplainGui;
	}
	
	
	/**
	 * Generate PDF files and open him
	 */
	private void makePdfFile(){
		Document doc = new Document();
		doc.setPageSize(PageSize.A3);
		Image img;
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("Parking Lot Pic.pdf"));
			doc.open();
			Font font = new Font(Font.FontFamily.COURIER,30, Font.BOLD, new BaseColor(0,0,0));
			Paragraph title = new Paragraph("Parking Lot Picture", font);
			title.setAlignment(Element.TITLE);
			doc.add(title);

			String[] pic = {"buttons.bmp","Floor1.bmp","Floor2.bmp","Floor3.bmp"};
			for (int i = 0; i < pic.length; i++) {
	            img = Image.getInstance(String.format(pic[i]));
	            img.setAlignment(Element.ALIGN_CENTER);
	            if (img.getScaledWidth() > 521 || img.getScaledHeight() > 200) {
	                img.scaleToFit(520, 200);
	            }
	            doc.add(img);
	        }
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Font footerFont = new Font(Font.FontFamily.COURIER,11, Font.NORMAL, new BaseColor(173,173,173));
			Paragraph footer = new Paragraph("Vcp Parking Status Picture Created On: " + format.format(date), footerFont);
			footer.setAlignment(Element.ALIGN_BOTTOM);
			doc.add(footer);
			doc.close();
			File myPDF = new File("Parking Lot Pic.pdf");
	        Desktop.getDesktop().open(myPDF);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * addDays is adding to give date number of x days
	 * @param date to be add
	 * @param days to add to date
	 * @return
	 */
	public Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public SystemData getSystemData() {
		if(systemData == null)
		{
			systemData = new SystemData(host, DEFAULT_PORT,vcpInfo);
		}
		return systemData;
	}
	
	public StatisticsPanel getStatistics() {
        if(statisticsPanel == null){
        	statisticsPanel = new StatisticsPanel(host, DEFAULT_PORT);
        }
        return statisticsPanel;
}
}


