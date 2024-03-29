import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Font;

public class ListForm extends JPanel {
	
	private ProjectList list;
	private JTextField nameTxtFld;
	private JTextField hoursPerDayTxtFld;
	private JTextField daysPerWeekTxtFld;
	private Board parentBoard;
	
	public ListForm() {
		
		setBackground(UIManager.getColor("Button.focus"));
		setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow]", "[grow][::25px,grow][::25px,grow][grow][grow][grow][grow][grow]"));
		
		JLabel nameLbl = new JLabel("List Name:");
		nameLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		add(nameLbl, "cell 1 1,alignx right");
		
		nameTxtFld = new JTextField();
		nameTxtFld.setText("My Custom List");
		add(nameTxtFld, "cell 2 1 4 1,growx");
		nameTxtFld.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Available Work Rate");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblNewLabel, "cell 2 3 4 1,aligny bottom");
		
		JLabel daysPerWeekLbl = new JLabel("# Days Per Week:");
		add(daysPerWeekLbl, "cell 2 4,alignx right");
		
		daysPerWeekTxtFld = new JTextField();
		daysPerWeekTxtFld.setText("5");
		add(daysPerWeekTxtFld, "cell 3 4 3 1,growx");
		daysPerWeekTxtFld.setColumns(10);
		
		JLabel hoursPerDayLbl = new JLabel("# Hours Per Day:");
		add(hoursPerDayLbl, "cell 2 5,alignx right");
		
		hoursPerDayTxtFld = new JTextField();
		hoursPerDayTxtFld.setText("8");
		add(hoursPerDayTxtFld, "cell 3 5 3 1,growx");
		hoursPerDayTxtFld.setColumns(10);
		
		JButton btnAddProject = new JButton("Submit");
		btnAddProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProjectList list = new ProjectList();
				list.setInstanceId(list);
				list.setName(nameTxtFld.getText());
				list.setDaysPerWeek(daysPerWeekTxtFld.getText());
				list.setHoursPerDay(hoursPerDayTxtFld.getText());
				parentBoard.hideCreateList();
				parentBoard.addList(list);
			}
		});
		add(btnAddProject, "cell 4 7 3 1,alignx center");
				
	}
	
	// Allow access to parentBoard methods
		public void setParentBoard(Board parent) {
			parentBoard = parent;
		}

}
