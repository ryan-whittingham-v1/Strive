import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ProjectForm extends JPanel {
	
	private ProjectCard project;
	private JTextField nameTxtFld;
	private ProjectList list;
	private JTextField dueDateTxtFld;
	private JTextField durationTxtFld;
	private JTextPane detailsTxtPn;
	
	public ProjectForm() {
		
		setBackground(UIManager.getColor("Button.focus"));
		setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow]", "[grow][::25px,grow][::25px,grow][grow][grow][grow][grow][grow]"));
		
		JLabel nameLbl = new JLabel("Name:");
		add(nameLbl, "cell 1 1,alignx right");
		
		nameTxtFld = new JTextField();
		nameTxtFld.setText("My Custom Project");
		add(nameTxtFld, "cell 2 1 4 1,growx");
		nameTxtFld.setColumns(10);
		
		JLabel detailsLbl = new JLabel("Details:");
		add(detailsLbl, "cell 1 2,aligny bottom");
		
		detailsTxtPn = new JTextPane();
		detailsTxtPn.setText("New project details");
		add(detailsTxtPn, "cell 1 3 5 1,grow");
		
		JLabel durationLbl = new JLabel("Duration (hours):");
		add(durationLbl, "cell 2 4,alignx right");
		
		durationTxtFld = new JTextField();
		durationTxtFld.setText("4");
		add(durationTxtFld, "cell 3 4 3 1,growx");
		durationTxtFld.setColumns(10);
		
		JLabel dueDateLbl = new JLabel("Due Date (MM/dd/yyyy):");
		add(dueDateLbl, "cell 2 5,alignx right");
		
		JButton btnAddProject = new JButton("Submit");
		btnAddProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProjectCard project = new ProjectCard();
				project.setParentList(list);
				project.setName(nameTxtFld.getText());
				project.setDetails(detailsTxtPn.getText());
				project.setDuration(Integer.parseInt(durationTxtFld.getText()));
				project.setDueDate(dueDateTxtFld.getText());
				project.setTimer();
				list.hideEditPane();
				list.addProject(project);
				
			}
		});
		
		dueDateTxtFld = new JTextField();
		dueDateTxtFld.setText("06/05/2019");
		add(dueDateTxtFld, "cell 3 5 3 1,growx");
		dueDateTxtFld.setColumns(10);
		add(btnAddProject, "cell 4 7 3 1,alignx center");
				
	}
	
	// set the list to work with
	public void setList(ProjectList list) {
		this.list = list;
	}

}
