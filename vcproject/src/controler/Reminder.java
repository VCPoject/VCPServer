package controler;
 
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Reminder {
	
	Toolkit toolkit;
	public Timer timer;
	private JFrame frame;
	
	  public Reminder(int seconds,JFrame frame) {
		this.frame=frame;
	    toolkit = Toolkit.getDefaultToolkit();
	    timer = new Timer();
	    timer.schedule(new RemindTask(), seconds * 1000);
	
	  }
	  
	  
	  class RemindTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's up!");
	      toolkit.beep();
	      frame.dispose();
	      frame=null;
	      timer.cancel(); //Not necessary because we call System.exit
	     // System.exit(0); //Stops the AWT thread (and everything else)
	    }
	  }
}
