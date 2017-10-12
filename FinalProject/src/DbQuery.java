import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

/**
 * Displays the online database table in our GUI
 * fetches the information from a myPHPAdmin database
 * @author Jacob and Pietro
 *
 */


public class DbQuery extends JFrame {

	private JPanel contentPane;
	private JTextField databaseTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;    
	private JTable table;
	private JComboBox filterList;
	private JLabel experienceLabel;
	private JTextArea output;
	
	//Public for implementation of reset criteria and restore previous search buttons.
	public static SummerExperienceGUI gui;

	
	private ArrayList<Experience> experiences = null;
	private DatabaseConverter dbFilter = new DatabaseConverter();
	

	public static void main(String[] args) throws SQLException {
		
		createSingletonDatabase.getInstance();
		DbQuery runQuery = new DbQuery();

	}

/**
 * Constructor.
 * gets the ArrayList of all experiences and assigns it to class attribute. 
 */
	public DbQuery() {
		
		experiences = null;
		try
		{
			experiences = (ArrayList<Experience>) dbFilter.getAllExperiences();					
		}
		catch (Exception exc) {
			System.out.println(exc); 
		}
		gui = new SummerExperienceGUI(experiences);
		gui.createGUI();
	}
}
