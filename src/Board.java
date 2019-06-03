import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel{
	
	ListForm createList;
	JPanel scrollWindow;
	Board boardInstance;
	
	public Board() {
		setLayout(new MigLayout("", "[grow][grow]", "[::25,grow][grow]"));
		
		// Header
		JLabel lblStrive = new JLabel("STRiVE");
		lblStrive.setFont(new Font("Dialog", Font.BOLD, 22));
		add(lblStrive, "cell 0 0,aligny top");
		
		// Project list Scroll pane
		//JScrollPane scrollPane = new JScrollPane();
		//add(scrollPane, "cell 0 1 2 1,grow");
		scrollWindow = new JPanel();
		//scrollPane.setViewportView(scrollWindow);
		scrollWindow.setLayout(new MigLayout("", "[grow]", "[grow]"));
		add(scrollWindow, "cell 0 1 2 1,grow");

		
		// Create List Panel, will be added and removed to projectBoard
		createList = new ListForm();
	
		// Create new list
		JButton btnNewButton = new JButton("New List");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createList.setParentBoard(boardInstance); // Allow access to board from listForm
				scrollWindow.add(createList,"cell 0 0,grow");
				repaint();
				revalidate();
			}
		});
		add(btnNewButton, "cell 1 0,alignx right");		
	}
	
	// Hide Createlist Panel
	public void hideCreateList() {
		scrollWindow.remove(createList);
		revalidate();
		repaint();
	}
	
	// Allows access to an instance of the project board
	public void setInstance(Board newBoard) {
		boardInstance = newBoard;
	}
	
	// Add new project to scroll pane
	public void addList(ProjectList newList) {
		scrollWindow.add(newList, "cell 0 0,grow");
		revalidate();
		repaint();
	}

}
