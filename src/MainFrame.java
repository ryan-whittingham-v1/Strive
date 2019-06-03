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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtProjectName;
	private ListForm createList;
	private Board projectBoard;
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
		//contentPane.setBackground(Color.LIGHT_GRAY);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		// Create Project Board
		projectBoard = new Board();
		contentPane.add(projectBoard, "cell 0 0,grow");
		// Pass instance of Project Board to itself for access from interior objects
		projectBoard.setInstance(projectBoard);
	}
	
	

}
