import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseMotionAdapter;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtProjectName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setTitle("STRiVE");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setExtendedState(MAXIMIZED_BOTH); // Launch to fullscreen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 339);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.shadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[::400px,grow]10[200px:n,grow]", "[:50px:50px,grow][300px,grow]"));
		
        Calendar c = Calendar.getInstance();
        
        // Current time
		JLabel lblNewLabel = new JLabel(c.getTime().toString());
		contentPane.add(lblNewLabel, "cell 0 0 2 1");
		
		// Create project list
		ProjectList list = new ProjectList("My Projects");
		contentPane.add(list, "flowx,cell 1 1,grow");
		
		// Set max time available for projects in the list
		list.setMaxTimeAvailable(8);
		
		Dimension maxDims = new Dimension(500, 250);
		
		// Add New Project Panel
		JPanel panel = new JPanel();		
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[][][grow][][][][grow][]"));
		
		JLabel lblCreateNewProject = new JLabel("Create New Project");
		lblCreateNewProject.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblCreateNewProject, "cell 0 0 5 1");
		
		JLabel lblName = new JLabel("Name:");
		panel.add(lblName, "cell 0 1,alignx right");
		
		txtProjectName = new JTextField();
		panel.add(txtProjectName, "cell 1 1 4 1,growx");
		txtProjectName.setColumns(10);
		
		contentPane.add(panel, "cell 0 1,grow");
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(list);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProjectCard newProject = new ProjectCard(txtProjectName.getText(), list);
				list.addProject(newProject);
			}
		});
		panel.add(btnNewButton, "cell 4 7,alignx left");
		
		/*
		// Move projectcard down 
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProjectCard temp = projectList.removeFirst();
				projectList.addLast(temp);
				int i = 0;
				while(i < projectList.size()) {
					panel_2.add(projectList.get(i), "wrap");
					
					panel_2.revalidate();
					
					i++;
				}
			}
		});
		contentPane.add(btnNewButton_1, "cell 0 2");
		*/
	}

}
