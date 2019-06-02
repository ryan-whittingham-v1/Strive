import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProjectCard extends JPanel {
	private JLabel nameLbl;
	private String name;
    private Date dueDate;
    private String dueDateString;
    private int duration;
    private String timeAvailable;
	private String details;
	private JTextPane detailsTxtPn; 
	private JLabel durationLbl;
	private CountdownTimer timer;
	JLabel dueDateLbl;
	private boolean behindSchedule;

	ProjectList parentList;

	JPanel panel;
	JButton btnEdit;
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	JButton btnNewButton;
	SimpleDateFormat stringToDate = new SimpleDateFormat("MM/dd/yyyy");
	DateFormat dateToString = new SimpleDateFormat("MM/dd/yyyy");
	private JLabel lblTimeAvailable;
	private JLabel timeAvailLbl;
	private JLabel lblStatus;
	private JLabel onScheduleLbl;
	
	//Getters and Setters
	
	public ProjectList getParentList() {
		return parentList;
	}

	public void setParentList(ProjectList parentList) {
		this.parentList = parentList;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDueDate() {
		return dateToString.format(dueDate);
	}

	public void setDueDate(String date) {
		dueDateString = date;
		try {
			this.dueDate = stringToDate.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}	
	
	public String toString() {
		return this.name;
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


	// Constructor
	public ProjectCard() {
	
		
		// When clicked notify parent Project List of selected project
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentList.selectProject((ProjectCard) e.getSource());
				showOptions();
			}
		});
		
		//Card Details
		
		//this.parent = parent;
		this.setBackground(Color.orange);
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.setLayout(new MigLayout("", "[::20px,grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
		nameLbl = new JLabel();
		nameLbl.setFont(new Font("Dialog", Font.BOLD, 29));
		nameLbl.setText("Sample Project");
		add(nameLbl, "cell 1 0 2 1");
		
	
		
		btnNewButton = new JButton("X");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentList.deleteProject();
			}
		});
		
		JLabel lblDue = new JLabel("Due:");
		lblDue.setFont(new Font("Dialog", Font.BOLD, 18));
		this.add(lblDue, "cell 3 0,alignx right,aligny");
		
		dueDateLbl = new JLabel();
		dueDateLbl.setForeground(Color.RED);
		dueDateLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		dueDateLbl.setText("06/03/2019");
		this.add(dueDateLbl, "cell 4 0,alignx left");
		add(btnNewButton, "cell 5 0,alignx center,aligny top");
		
		//Description
		detailsTxtPn = new JTextPane();
		detailsTxtPn.setFont(new Font("Dialog", Font.PLAIN, 20));
		detailsTxtPn.setText("Descripton");
		detailsTxtPn.setEditable(false);
		detailsTxtPn.setOpaque(false);
		this.add(detailsTxtPn, "cell 1 1 4 2,grow");
		
		// Duration
		JLabel lblDuration = new JLabel("Duration (hours):");
		lblDuration.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDuration, "cell 1 3,alignx right,aligny bottom");
		
		durationLbl = new JLabel();
		durationLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		durationLbl.setText("1");
		add(durationLbl, "cell 2 3,alignx left,aligny bottom");
		
		lblTimeAvailable = new JLabel("Time Available:");
		lblTimeAvailable.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblTimeAvailable, "cell 1 4,alignx right,aligny bottom");
		
		btnNewButton_2 = new JButton("Move Down");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentList.moveDown();
			}
		});
		
		btnNewButton_1 = new JButton("Move Up");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentList.moveUp();
			}
		});
		
		timeAvailLbl = new JLabel("New label");
		timeAvailLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		add(timeAvailLbl, "cell 2 4,aligny bottom");
		
		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblStatus, "cell 1 5,alignx right,aligny bottom");
		
		onScheduleLbl = new JLabel("on-schedule");
		onScheduleLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		add(onScheduleLbl, "cell 2 5,aligny bottom");
		
		btnEdit = new JButton("Edit");
		add(btnEdit, "cell 1 6");
		add(btnNewButton_1, "cell 4 6,alignx center");
		add(btnNewButton_2, "cell 5 6,alignx left");
		hideOptions();
	}
	
	// Add countdown timer to project
	public void setTimer() {
		timer = new CountdownTimer(dueDateString);
	}
	
	// Show the card options
		public void showOptions() {
			btnEdit.setVisible(true);
			btnNewButton_1.setVisible(true);
			btnNewButton_2.setVisible(true);
			btnNewButton.setVisible(true);
		}
		
		// Hide the card options
			public void hideOptions() {
				btnEdit.setVisible(false);
				btnNewButton_1.setVisible(false);
				btnNewButton_2.setVisible(false);
				btnNewButton.setVisible(false);
			}
			
		// Refresh text on card
			public void refresh() {
				nameLbl.setText(name);
				detailsTxtPn.setText(details);
				durationLbl.setText(Integer.toString(duration));
				//timeAvailLbl.setText(timer.getTimeAvailable());
				dueDateLbl.setText(dueDateString);
				onScheduleLbl.setText(calculateTimeAvailability());
			}
			
		// Calculate if time available to meet duedate
			public String calculateTimeAvailability() {
				String response = "";
				int remainingDaysTillDue;
				
				// Get total hours till duedate
				int totalHoursTillDue = timer.getHoursTillDue();
				
				// Calculate how many weeks till due date
				int weeksTillDue = totalHoursTillDue / 168;
				if (weeksTillDue != 0) { // More then a week till due date
					remainingDaysTillDue = (totalHoursTillDue%weeksTillDue) / 24; // Calculate remaining days 
				}
				else {
					remainingDaysTillDue = totalHoursTillDue / 24; // less than a week, calc days
				}
				
				int remainingHoursTillDue = totalHoursTillDue - (weeksTillDue * 168) - // Calc remaining hours
						(remainingDaysTillDue * 24);
				
				// Calculate available work hours till due date using list time commitment settings 				
				int workTimeAvailable = weeksTillDue * parentList.getHoursPerWeek() + (remainingDaysTillDue 
						* Integer.parseInt(parentList.getHoursPerDay()));
				
				
				if (remainingHoursTillDue > Integer.parseInt(parentList.getHoursPerDay())) {
					workTimeAvailable = workTimeAvailable + Integer.parseInt(parentList.getHoursPerDay());
				}
				
				System.out.println("Due in: " + weeksTillDue + " weeks " + remainingDaysTillDue + " days "
									+ remainingHoursTillDue + " hours.");
				System.out.println("Available work hours: " + workTimeAvailable);
				
				// Subtract total hours of existing projects in the list and new projects duration	
				int workTimeDifference = workTimeAvailable - parentList.getTotalProjectHours() - duration;
				System.out.println(parentList.getTotalProjectHours());
				System.out.println("time difference: " + workTimeDifference);
				
				if (workTimeDifference < 0) {
					response = ("Behind schedule by " + Math.abs(workTimeDifference)  + " hours.");
					behindSchedule = true;
				}
				else {
					response =  "On schedule";
				}
				return response;
			}
}
