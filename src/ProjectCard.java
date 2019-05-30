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

public class ProjectCard extends JPanel {
	private String name = "";
    Date dueDate = new Date();
    private int duration = 5;
	private String description = "This project is a test project and im curious if the text will wrap or not and it looks like it will. yeah!";
	//private Dimension maxDims = new Dimension(500, 250);
	ProjectList parent;
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date newDate) {
		dueDate = newDate;
	}
	

	public ProjectCard(String name, ProjectList parent) {
		
		// When clicked
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.selectProject((ProjectCard) e.getSource());
			}
		});
		
		//Card Details
		//this.setMaximumSize(maxDims);
		this.parent = parent;
		this.setBackground(Color.orange);
		this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow]"));
		
		//Name
		this.name = name;
		JLabel projectNameLbl = new JLabel(this.name);
		projectNameLbl.setFont(new Font("Ubuntu", Font.BOLD, 25));
		this.add(projectNameLbl, "cell 0 0,alignx left,aligny top");
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.deleteProject();
			}
		});
		add(panel, "cell 3 0,grow");
		
		JLabel lblX = new JLabel("x");
		panel.add(lblX);
		
		
		//Description
		JTextPane descriptionTxtPne = new JTextPane();
		descriptionTxtPne.setText(description);
		descriptionTxtPne.setEditable(false);
		descriptionTxtPne.setOpaque(false);
		this.add(descriptionTxtPne, "cell 0 1 4 1,grow");
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDuration, "cell 1 4,alignx right,aligny bottom");
		JLabel lblNewLabel = new JLabel(Integer.toString(duration));
		add(lblNewLabel, "cell 2 4,alignx left,aligny bottom");
		
		
		//Due date
		JLabel lblDue = new JLabel("Due:");
		this.add(lblDue, "cell 1 5,alignx right,aligny bottom");
		JLabel dueDateLbl = new JLabel(dueDate.toString());
		this.add(dueDateLbl, "cell 2 5,alignx left,aligny bottom");		
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}	
}
