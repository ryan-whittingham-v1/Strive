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

public class ProjectList extends JPanel implements ActionListener{
	
	private int capacity;
	private LinkedList<ProjectCard> list;
	private JPanel scrollViewPort;
	private JLabel totalTimeLbl;
	//private JLabel lblListname;
	private String name = "List Name";
	private int maxTimeAvailable = 0;
	private JLabel lblListName;
	
	
	// Constructor to create new linked list
	public ProjectList(String text) {
		
		
		
		list = new LinkedList<ProjectCard>(); 
		this.setLayout(new MigLayout("", "[grow,center][grow]", "[25px:25px:25px][25px:25px:25px,grow][grow]"));
		this.setBackground(Color.green);
		this.name = text;
		lblListName = new JLabel(name);
		lblListName.setFont(new Font("Ubuntu", Font.BOLD, 20));
		add(lblListName, "cell 0 0,alignx left");
		
		JLabel lblTotalHours = new JLabel("Total Hours:");
		add(lblTotalHours, "cell 0 1,alignx right,aligny center");
		
		totalTimeLbl = new JLabel("0");
		add(totalTimeLbl, "cell 1 1,aligny center");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(scrollPane, "cell 0 2 2 1,grow");
		scrollViewPort = new JPanel();
		scrollPane.setViewportView(scrollViewPort);
		scrollViewPort.setLayout(new MigLayout("", "[grow,center]", "[grow]"));
				
	}
	
	// Add new project
	public void addProject(ProjectCard newProject) {
		list.add(newProject); // add to linked list
		scrollViewPort.add(newProject, "aligny top, wrap"); // add to jpanel 
		updateTimeTotal();
		checkTimeAvailable();
		this.revalidate();
		this.repaint();
	}
	
	// Get all projects time total
	public int updateTimeTotal() {
		int total = 0;
		int i = 0;
		while(i < list.size()) {
			total = total + list.get(i).getDuration();
			i++;
		}
		totalTimeLbl.setText(Integer.toString(total));
		return total;
	}
	
	// Set max time available for the list
	public void setMaxTimeAvailable(int time) {
		maxTimeAvailable = time;
	}
	
	// Check if time available in the list
	public void checkTimeAvailable() {
		if (updateTimeTotal() > maxTimeAvailable) {
			this.setBackground(Color.red);
		}
		else {
			this.setBackground(Color.green);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Delete this project" + e.getSource());
		
	}
	
	private ProjectCard selectedProject;
	
	// Select project
	public void selectProject(ProjectCard selectedProject) {
		this.selectedProject = selectedProject;
		int i = 0;
		while(i < list.size()) {
			list.get(i).setBackground(Color.orange);
			i++;
		}
		selectedProject.setBackground(Color.blue);
	}
	
	// Delete Project
	public void deleteProject() {
		if (selectedProject != null) {
			System.out.println(selectedProject);
			scrollViewPort.remove(selectedProject); // remove from jpanel
			System.out.println(list.remove(selectedProject)); // remove from linked list
		}
	
		updateTimeTotal();
		checkTimeAvailable();
		this.revalidate();
		this.repaint();
	}

}
