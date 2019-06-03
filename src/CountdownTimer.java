import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class CountdownTimer extends JPanel implements Runnable {
	private JLabel clockLbl;
	private String dueDateString;
	Calendar startTime;
	Calendar endTime;
	Calendar calendarCurrent;
	Calendar calendarDueDate;
	SimpleDateFormat dateToStringFormat;
	SimpleDateFormat stringToDateFormat;
	Date dueDate; 
	Date currentDate;
	Date timeLeft;
	long timeAvailable;
	
	public CountdownTimer(String newDueDate) {
		stringToDateFormat = new SimpleDateFormat("MM/dd/yyyy"); // Format for string to date conversion
		dateToStringFormat = new SimpleDateFormat("MM/dd/yyyy");  // Format for date to string conversion
		
		dueDateString = newDueDate;  // Assign newDueDate arg
		
		// Assign dueDate
		calendarDueDate = Calendar.getInstance();
		try {
			calendarDueDate.setTime(stringToDateFormat.parse(dueDateString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// Assign dueDate to date object
		dueDate = calendarDueDate.getTime(); 
		
		setLayout(new MigLayout("", "[grow,center]", "[grow]"));
		clockLbl = new JLabel("New label");
		setOpaque(false);
		add(clockLbl, "cell 0 0");
	}
	
	
	// Get hours till due date
		public int getHoursTillDue() {
			
			calendarCurrent = Calendar.getInstance(); // update current time
			
			// Assign currentDate to date object
			currentDate = calendarCurrent.getTime();
			System.out.println("todays date " + currentDate);
						
			// Calculate time available
			timeAvailable = dueDate.getTime() - currentDate.getTime();
			System.out.println("time till duedate: " + timeAvailable);
			
			int diffHours = (int)((timeAvailable / (60 * 60 * 1000)));
			System.out.println("total Hours till due date: " + diffHours);
			
			return (diffHours);
			
		}
		
	// Turn on Countdown timer
	public void run() {
		
		while (true) {
			calendarCurrent = Calendar.getInstance(); // update current time
			
			// Assign currentDate to date object
			currentDate = calendarCurrent.getTime();
			
			// Calculate time available
			timeAvailable = dueDate.getTime() - currentDate.getTime();

			// Convert to proper time intervals
			long diffSeconds = timeAvailable / 1000 % 60;
			long diffMinutes = timeAvailable / (60 * 1000) % 60;
			long diffHours = timeAvailable / (60 * 60 * 1000) % 24;
			long diffDays = timeAvailable / (24 * 60 * 60 * 1000);
				
			String answer = (Long.toString(diffDays) + " days, " + Long.toString(diffHours) + " hours, "
					+ Long.toString(diffMinutes) + " minutes, " + Long.toString(diffSeconds) + " seconds till deadline.");
			
			// Upate the clock text	
			clockLbl.setText(answer);		
		}
	}

}
