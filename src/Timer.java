import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

public class Timer extends JPanel implements Runnable{
	
	private JLabel clockLbl;
	Calendar calendar;
	
	public Timer() {
		setLayout(new MigLayout("", "[grow,center]", "[grow]"));
		clockLbl = new JLabel("New label");
		setOpaque(false);
		add(clockLbl, "cell 0 0");
	}
	
	// Turn on the clock
	public void run() {
		while (true) {
			calendar = Calendar.getInstance();
			clockLbl.setText(calendar.getTime().toString());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

}
