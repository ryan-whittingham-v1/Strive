import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectList extends JPanel implements ActionListener{
	private LinkedList<ProjectCard> list;
	private JPanel scrollViewPort;
	private String name;
	private int hoursPerDay;
	private int daysPerWeek;
	private int hoursPerWeek;
	private int totalProjectHours;
	private ProjectList listInstance;
	private ProjectCard selectedProject;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewProject;
	private JButton btnDelete;
	private ProjectForm createProject;
	private JLabel nameLbl;
	private JLabel lblHoursPerDay;
	private JLabel hoursPerDayLbl;
	private JLabel lblDaysPerWeek;
	private JLabel daysPerWeekLbl;
	private JLabel lblAvailability;
	private JLabel lblTotalProjectHours;
	private JLabel totalProjectHoursLbl;
	
	// Constructor to create new linked list
	public ProjectList() {
		totalProjectHours = 0;
		this.setLayout(new MigLayout("", "[::75px,grow][grow][grow][grow][grow][grow][grow][grow][::75px,grow]", "[25px:25px:25px][25px:25px:25px,grow][grow][grow][grow][grow][]"));
		this.setBackground(Color.green);
		
		// Create Linked list for project cards
		list = new LinkedList<ProjectCard>(); 
		
		// Name 
		nameLbl = new JLabel("List Name");
		nameLbl.setFont(new Font("Dialog", Font.BOLD, 26));
		add(nameLbl, "cell 0 0 4 1");
		
		// Availablity Header
		lblAvailability = new JLabel("Time Commitment");
		lblAvailability.setFont(new Font("Dialog", Font.BOLD, 22));
		add(lblAvailability, "cell 1 2,aligny bottom");
		
		// Scrollpane for the list
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(scrollPane, "cell 5 2 3 4,grow");
		scrollViewPort = new JPanel();
		scrollViewPort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deselectProjects();
			}
		});
		scrollPane.setViewportView(scrollViewPort);
		scrollViewPort.setLayout(new MigLayout("", "[grow,center]", "[grow]"));
		
		// Create Project button
		btnNewProject = new JButton("Create Project");
		btnNewProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passList();
				scrollViewPort.add(createProject, "aligny top, grow, wrap");
				refresh();
			}
		});
		
		lblDaysPerWeek = new JLabel("Days Per Week:");
		add(lblDaysPerWeek, "cell 1 3,aligny bottom");
		
		daysPerWeekLbl = new JLabel("5");
		add(daysPerWeekLbl, "cell 2 3,aligny bottom");
		
		lblHoursPerDay = new JLabel("Hours Per Day :");
		add(lblHoursPerDay, "cell 1 4,aligny top");
		
		hoursPerDayLbl = new JLabel("8");
		add(hoursPerDayLbl, "cell 2 4,aligny top");
		
		lblTotalProjectHours = new JLabel("Total Project Hours:");
		add(lblTotalProjectHours, "cell 1 5");
		
		totalProjectHoursLbl = new JLabel(Integer.toString(totalProjectHours));
		add(totalProjectHoursLbl, "cell 2 5");
		add(btnNewProject, "cell 7 6,alignx right");
		
		// Create a CreateProject custom jpanel for adding to the list with button click
		createProject = new ProjectForm();
		
				
	}
	
	// Set the instance of the class
	public void setInstanceId(ProjectList listInstance) {
		this.listInstance = listInstance;
	}
	
	// Pass the instance of the list to the Project Form pane
	public void passList() {
		createProject.setList(listInstance);
	}
	
	// Refresh the list
	public void refresh() {
		nameLbl.setText(name);
		int i = 0;
		while(i < list.size()) {
			scrollViewPort.add(list.get(i), "aligny top, grow, wrap");
			i++;
		}
		
		revalidate();
		repaint();
	}
	
	// Add new project
	public void addProject(ProjectCard newProject) {
		list.add(newProject); // add to linked list		
		newProject.refresh();
		refresh();
		//updateTimeAvailable();
		updateTimeTotal();
		//checkTimeAvailable();
		
	}
	
	// Get all projects time total
	public int updateTimeTotal() {
		int total = 0;
		int i = 0;
		while(i < list.size()) {
			total = total + list.get(i).getDuration();
			i++;
		}
		totalProjectHours = total;
		totalProjectHoursLbl.setText(Integer.toString(total));
		return total;
	}
	
	// Get all projects time total
	//public int updateTimeAvailable() {
		//	int total = 0;
			//total = maxTimeAvailable - updateTimeTotal();
			//timeAvailableLbl.setText(Integer.toString(total));
			//return total;
		//}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		nameLbl.setText(this.name);
	}

	public String getHoursPerDay() {
		return Integer.toString(hoursPerDay);
	}

	public void setHoursPerDay(String hoursPerDay) {
		this.hoursPerDay = Integer.parseInt(hoursPerDay);
		hoursPerDayLbl.setText(hoursPerDay);
	}

	public String getDaysPerWeek() {
		return Integer.toString(daysPerWeek);
	}

	public void setDaysPerWeek(String daysPerWeek) {
		this.daysPerWeek = Integer.parseInt(daysPerWeek);
		daysPerWeekLbl.setText(daysPerWeek);
	}

	// Check if time available in the list
	//public void checkTimeAvailable() {
		//if (updateTimeTotal() > maxTimeAvailable) {
		//	this.setBackground(Color.red);
		//}
		//else {
			//this.setBackground(Color.green);

	//	}
	//}

	public int getHoursPerWeek() {
		hoursPerWeek = hoursPerDay * daysPerWeek;
		return hoursPerWeek;
	}

	public void setHoursPerWeek(int hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public int getTotalProjectHours() {
		return totalProjectHours;
	}

	public void setTotalProjectHours(int totalProjectHours) {
		this.totalProjectHours = totalProjectHours;
	}

	// Actionlistener test
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Delete this project" + e.getSource());
		
	}
	
	// Select project
	public void selectProject(ProjectCard selectedProject) {
		this.selectedProject = selectedProject;
		int i = 0;
		while(i < list.size()) {
			list.get(i).setBackground(Color.orange);
			list.get(i).hideOptions();
			i++;
		}
		selectedProject.setBackground(Color.blue);
		selectedProject.showOptions();
	}
	
	// Deselect all projects
	public void deselectProjects() {
		selectedProject = null;
		int i = 0;
		while(i < list.size()) {
			list.get(i).setBackground(Color.orange);
			list.get(i).hideOptions();
			i++;
		}
		
		refresh();
	}
	
	// Move Project up in the list
		public void moveUp() {
			int i = 0;
			int newIndex = 0;
			if (selectedProject != null) {
				while(i < list.size()) {
					if (selectedProject == list.get(i)) {
						break;
					}
					i++;
				}
			}
			newIndex = i - 1;
			if(newIndex > -1){
				//scrollViewPort.remove(selectedProject); // remove from jpanel
				list.remove(i); // remove from linked list				
				list.add(newIndex, selectedProject);
			}
			updateCards();
			refresh();
		}
	
	// Move Project down in the list
	public void moveDown() {
		int i = 0;
		int newIndex = 0;
		if (selectedProject != null) {
			while(i < list.size()) {
				if (selectedProject == list.get(i)) {
					break;
				}
				i++;
			}
		}
		newIndex = i + 1;
		if(newIndex < list.size()) {
			//scrollViewPort.remove(selectedProject); // remove from jpanel
			list.remove(i); // remove from linked list
			list.add(newIndex, selectedProject);
			
		}
		//updateTimeTotal();
		//checkTimeAvailable();
		updateCards();
		refresh();
	}

	
	// Delete Project
	public void deleteProject() {
		if (selectedProject != null) {
			System.out.println(selectedProject);
			scrollViewPort.remove(selectedProject); // remove from jpanel
			list.remove(selectedProject); // remove from linked list
		}
	//	updateTimeAvailable();
		updateTimeTotal();
		//checkTimeAvailable();
		refresh();
	}
	
	// Hide the editPane
	public void hideEditPane() {
		scrollViewPort.remove(createProject);
		refresh();
	}	
	
	// Update on-schedule status
	public void updateCards() {
		totalProjectHours = 0;
		int i = 0;
		while(i < list.size()) {
			list.get(i).refresh();
			totalProjectHours = totalProjectHours + list.get(i).getDuration();
			i++;
		}
		totalProjectHoursLbl.setText(Integer.toString(totalProjectHours));
	}
}
