import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneLayout;

/**
 * New and updated GUI with GridBagLayOut and up to date (09/17/2017) Experience class usage.
 * @author Nick
 *
 */
public class SummerExperienceGUI
{
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
    //Components as global variables for easy identification.
    private JPanel mainPane;
	private JScrollPane scrollPane;  
	private JFrame masterFrame; 
	private JTextArea outputText;
	
	
	//Selection Tools: public so that command objects can get and update status. 
	public JComboBox stateQuery ;
	public JComboBox studentStandingQuery;
	public JComboBox compensationQuery;
	public JComboBox industryQuery;
	public JComboBox hoursQuery;
	public JCheckBox internationalBox; 
	public JCheckBox internshipBox ;
	public JButton searchButton;
	public JButton restorePreviousSearchButton;
	public JButton resetButton;
	public JButton colorSchemeButton;
	private JTable fullTable;
	private JTable currentDisplayedTable;
	
	//For color strategy pattern
	private ColorSchemeStrategy colorScheme; 
	//Stack for commands
	private Stack<guiCommand> commands = new Stack<guiCommand>();
	
	
	//public for use in command objects.
	public static String INTERNATIONAL_STRING = "Yes";
	public static String INTERNSHIP_STRING = "Yes";
	
	//Colors below. Assigned by constructor, then can be reassigned switchColorScheme()
	private Color colorOne;
	private Color colorTwo;
	private Color colorThree;
	private Color colorFour;
	private Color colorFive;
	private Color fontColor;
	private int colorInt = 0; // this int tells switchColorScheme which colorScheme to switch to next. 
	//Instance of utility classes
	private GuiFileIO guiIO = new GuiFileIO();
	private TableBuilder tableBuilder = new TableBuilder();
	//List of experiences that the GUI uses
	private ArrayList<Experience> experiences = new ArrayList<Experience>();
	
	
	/**
	 * Constructor for GUI
	 * Initialized Color Strategy as cc colors
	 * @param experiences
	 */
	public SummerExperienceGUI(ArrayList<Experience> experiences)
	{
		//Default color scheme is set
		this.colorScheme = new ColorSchemeColoradoCollege();
		colorOne = colorScheme.getColorOne();
		colorTwo = colorScheme.getColorTwo(); 
		colorThree = colorScheme.getColorThree();
		colorFour = colorScheme.getColorFour();
		colorFive = colorScheme.getColorFive();
		fontColor = colorScheme.getFontColor(); 
		this.experiences = experiences;
	}
	
	/**
	 * This method gets the selected options of the GUI and returns them as a string.
	 * @param state
	 * @param standing
	 * @param compensationQuery
	 * @param industryQuery
	 * @param hoursQuery
	 * @param internationalBox
	 * @param internshipBox
	 * @return
	 */
	
	/**
	 * This method switches the color scheme along a rotation between three schemes. 
	 */
	public void switchColorScheme()
	{
		System.out.println(colorInt);
		if(colorInt ==0)
		{
			colorScheme = new ColorSchemeDarkTheme();
			colorInt++;
		}
		else if(colorInt ==1)
		{
			colorScheme = new ColorSchemeLightTheme();
			colorInt++;
		}
		else
		{
			colorScheme = new ColorSchemeColoradoCollege();
			colorInt = 0;
		}
		
		//Update colors and refresh
		colorOne = colorScheme.getColorOne(); 
		colorTwo = colorScheme.getColorTwo(); 
		colorThree = colorScheme.getColorThree();
		colorFour = colorScheme.getColorFour();
		colorFive = colorScheme.getColorFive();
		fontColor = colorScheme.getFontColor();
		updateColors();
		refreshGui();
	}
	
	/** Updates the color properties of swing components to the current color strategy.
	 * 
	 */
	public void updateColors()
	{
		mainPane.setBorder(BorderFactory.createLineBorder(colorThree));
		mainPane.setBackground(colorTwo);
		scrollPane.setBorder(BorderFactory.createBevelBorder(3,colorThree, colorFour));
		outputText.setBackground(Color.WHITE);
		outputText.setForeground(fontColor);
		scrollPane.setBackground(colorTwo);
		fullTable.setBackground(colorFive);
		fullTable.setForeground(fontColor);
	}
	
	/**
	 * refreshes the Gui to show any changes
	 */
	public void refreshGui()
	{
		mainPane.revalidate();
		mainPane.setVisible(true);
		masterFrame.validate();
		
	}
	
	/**
	 * returns a string of the attributes of the experience object. 
	 * @param experiences
	 * Not needed for Iteration 3, but possibly useful for (theoretical) future iterations.
	 * @return formatted string
	 */
	public String experienceObjectListToString(ArrayList<Experience> experiences)
	{
		String experiencesString = "";
		for(Experience temp: experiences)
		{
			experiencesString = experiencesString + temp.toString() +"\n";
		}
		
		
		return experiencesString;
	}
	
	/**
	 * updates the Table displayed in the GUI to the param JTable updatedTable
	 * @param updatedTable
	 */
	public void updateTable(JTable updatedTable) 
	{
		currentDisplayedTable = updatedTable;
		scrollPane.setViewportView(currentDisplayedTable);
		refreshGui();	
	}
	
	/**
	 * This method creates and formats the GUI.
	 */
	public void createGUI() 
	{
		
		masterFrame = new JFrame("Database Search");
		masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		masterFrame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		masterFrame.setBounds(0, 0, 1200, 900);
		//Sets the inital size of the window.
		mainPane = new JPanel();
		mainPane.setBorder(BorderFactory.createLineBorder(colorThree));
		mainPane.setLayout(new GridBagLayout());
		masterFrame.setContentPane(mainPane);
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) 
		{
		c.fill = GridBagConstraints.HORIZONTAL;
		}
		c.insets = new Insets(2,2,2,2);
		//Main background color
		mainPane.setBackground(colorTwo);
		
		//Below are all of the filters, checkBoxes, and Buttons.
		//__________________________________________________________________________________________________________
		String[] StateFilter =  guiIO.getFileLinesAsStringArray("States.txt");
		stateQuery = new JComboBox(StateFilter);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		mainPane.add(stateQuery, c);
		
		String[] studentStandingFilter = { "Select Year" ,"Rising Sophomore", "Rising Junior", "Rising Senior"};
		studentStandingQuery = new JComboBox(studentStandingFilter);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		mainPane.add(studentStandingQuery, c);
		
		String[] compensationFilter = { "Select Financial Compensation", "Unpaid", "Paid hourly wage", "Paid a stipend"};
		compensationQuery = new JComboBox(compensationFilter);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		mainPane.add(compensationQuery, c);
		
		String[] industryFilter = { "Select Industry", "Arts, Media and Communications","Community Organizations/Non-Profits"
				,"Consulting, Management and Human Resources","Education", "Engineering and Technology","Environment and Sustainability"
				, "Finance, Real Estate and Insurance", "Healthcare","Public Policy, Government and Law","Scientific Research"
				,"Sports and Outdoor Recreation", "Other"};
		industryQuery = new JComboBox(industryFilter);
		c.weightx = 0.5; 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		mainPane.add(industryQuery, c);
		
		String[] hoursFilter = {"Select Hours/Week",  "Under 15", "15-29", "30+"};
		hoursQuery = new JComboBox(hoursFilter);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		mainPane.add(hoursQuery, c);
		
		internationalBox = new JCheckBox("International Students");
		internationalBox.setMnemonic(KeyEvent.VK_C); 
		internationalBox.setSelected(false);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		mainPane.add(internationalBox, c);
		
		internshipBox = new JCheckBox("Internships only");
		internshipBox.setMnemonic(KeyEvent.VK_C); 
		internshipBox.setSelected(false);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		mainPane.add(internshipBox, c);
	
		searchButton = new JButton("Search");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		mainPane.add(searchButton, c);
		
		restorePreviousSearchButton = new JButton("Restore Previous Search");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		mainPane.add(restorePreviousSearchButton, c);
		
		resetButton = new JButton("Reset Criteria");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		mainPane.add(resetButton, c);
		
		
		colorSchemeButton = new JButton("Change Color Scheme");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		mainPane.add(colorSchemeButton, c);
		
		//__________________________________________________________________________________________________________

		//Sets up the Text are where output will go.
		outputText = new JTextArea("");
		outputText.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		//color of text box
		outputText.setBackground(Color.WHITE);
		//color of writing
		outputText.setForeground(fontColor);
		outputText.setBounds(0,0,800,1000);
		
		//Setting up the scroll pane 
		scrollPane = new JScrollPane(outputText);
		scrollPane.setBorder(BorderFactory.createBevelBorder(3,colorThree, colorFour));
		//ScrollPane color
		scrollPane.setBackground(colorTwo);
		scrollPane.setVisible(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 100;    
		c.ipadx = 100;
		c.weightx = 0.0;
		c.gridwidth = 18;
		c.gridheight = 18;
		c.gridx = 0;
		c.gridy = 3 ;
		mainPane.add(scrollPane, c);
		mainPane.revalidate();
		mainPane.setVisible(true);
		masterFrame.validate();
		
		//Initilize table as full table
		fullTable = tableBuilder.getFullTable();
		fullTable.setBackground(colorFive);
		fullTable.setForeground(fontColor);
		scrollPane.setViewportView(fullTable);
		
		//Action listener for restorePreviousSearchButton
		restorePreviousSearchButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(!commands.isEmpty())
				{
					SearchCommand previousSearch = (SearchCommand) commands.pop();
					String search = previousSearch.getSearchCriteria();
					outputText.setText(search);
					previousSearch.undoCommand();
					refreshGui();				
				}
				else
				{
					//Not printing temporary error string into text area for whatever reason. Never figured it out. 
					String current = outputText.getText();
					outputText.setText("No last search to restore.");
					scrollPane.setViewportView(outputText);
					refreshGui();
					try
					{
						refreshGui();
						Thread.sleep(1500);
					}
					catch(Exception ex)
					{
						System.out.print("Error in pause: " + ex +"\n" + "Moving on.");
					}
					scrollPane.setViewportView(fullTable);
					refreshGui();
				}
				
			}
		} );
	
		//Action listener for resetButton
		resetButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				stateQuery.setSelectedIndex(0);
				studentStandingQuery.setSelectedIndex(0);
				compensationQuery.setSelectedIndex(0);
				industryQuery.setSelectedIndex(0);
				hoursQuery.setSelectedIndex(0);
				internationalBox.setSelected(false);
				internshipBox.setSelected(false);
				scrollPane.setViewportView(fullTable);
				refreshGui();
				
			}
		} );
		
		//Action listener for searchButton
		searchButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				scrollPane.setViewportView(outputText);
				SearchCommand searchCommand = new SearchCommand(stateQuery, studentStandingQuery, compensationQuery, industryQuery, hoursQuery, internationalBox, internshipBox,experiences);
				commands.push(searchCommand);
				searchCommand.doCommand();
				refreshGui();
			}
		} );
		
		//Action listener for colorSchemeButton
		colorSchemeButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				switchColorScheme();	
			}
		} );
		refreshGui();
		masterFrame.setVisible(true);		
	}

	/**
	 * converts javafx colors to colors usable by swing.
	 * Not needed in this class for Iteration 3, but possibly useful for (theoretical) future iterations. 
	 * @param fxColor
	 * @return
	 */
	public Color convertFXColorToSwingColor(javafx.scene.paint.Color fxColor)
	{
		java.awt.Color awtColor = new java.awt.Color((float) fxColor.getRed(),
		                                             (float) fxColor.getGreen(),
		                                             (float) fxColor.getBlue(),
		                                             (float) fxColor.getOpacity());
		return awtColor;
	}

}
	