package controler;
 
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Reminder extends Controller{
	
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
	      toolkit.beep();
	      showWarningMsg("Time's up");
	      frame.dispose();
	      frame=null;
	      timer.cancel(); 
	    }
	  }
}
