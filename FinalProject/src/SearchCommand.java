import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;


/**
 * Search functionality Encapsulated into a command object
 * @author Nick
 *
 */
public class SearchCommand implements guiCommand
{
	private TableBuilder myTableBuilder = new TableBuilder();
	private String state;
	private String year;
	private String compensation;
	private String industry;
	private String hours;
	private String international;
	private String internship;
	private boolean isInternational;
	private boolean isInternship;
	private ArrayList<Experience> allExperiences;
	private JTable filteredTable;
	//search command could have methods that return arrayLists, strings. 
	//Assign stuff in constructor? yes
	
	/**
	 * Constructor initialized command properties using the JComboBox objects from the GUI as parameters. 
	 * @param stateQuery
	 * @param yearQuery
	 * @param compensationQuery
	 * @param industryQuery
	 * @param hoursQuery
	 * @param internationalBox
	 * @param internshipBox
	 * @param allExperiences
	 */
	public SearchCommand(JComboBox stateQuery, JComboBox yearQuery, JComboBox compensationQuery, JComboBox industryQuery, JComboBox hoursQuery, JCheckBox internationalBox, JCheckBox internshipBox, ArrayList<Experience> allExperiences)
	{ 
		this.state = (String) stateQuery.getSelectedItem();
		this.year = (String) yearQuery.getSelectedItem();
		this.compensation = (String) compensationQuery.getSelectedItem();
		this.industry = (String) industryQuery.getSelectedItem();
		this.hours = (String) hoursQuery.getSelectedItem();
		this.international = getInternationalStatus(internationalBox);
		this.internship = getInternshipStatus(internshipBox);
		this.isInternational = internationalBox.isSelected();
		this.isInternship = internshipBox.isSelected();
		this.allExperiences = allExperiences;
		
		
	}
	
	//Search might activate the filters. 
	public void doCommand() 
	{
		ExperienceFilter filter = new ExperienceFilter(allExperiences);	//make new filter object
		ArrayList<Experience> filteredExperiences = filter.filterDB(getCriteria());	//filter list
		JTable filteredTable = myTableBuilder.getFilteredTable(filteredExperiences);
		this.filteredTable = filteredTable;
		DbQuery.gui.updateTable(filteredTable);
		//display filtered list
	}
	
	//undo will restore the previous search and activate the filters. 
	public void undoCommand()
	{
		DbQuery.gui.stateQuery.setSelectedItem(state);
		DbQuery.gui.studentStandingQuery.setSelectedItem(year);
		DbQuery.gui.compensationQuery.setSelectedItem(compensation);
		DbQuery.gui.industryQuery.setSelectedItem(industry);
		DbQuery.gui.hoursQuery.setSelectedItem(hours);
		DbQuery.gui.internationalBox.setSelected(isInternational);
		DbQuery.gui.internshipBox.setSelected(isInternship);
		DbQuery.gui.updateTable(filteredTable);
		DbQuery.gui.refreshGui();
		
	}
	
	/**
	 * gets a string representing the status of the international box.
	 * @param internationalBox
	 * @return
	 */
	public String getInternationalStatus(JCheckBox internationalBox)
	{
		String status;
		if(internationalBox.isSelected())
		{
			status = "Yes";
		}
		else
		{
			status = "Not Selected";
		}
		return status;
		
	}
	
	/**
	 * gets a string representing the status of the internship box.
	 * @param internshipBox
	 * @return
	 */
	public String getInternshipStatus(JCheckBox internshipBox)
	{
		String status;
		if(internshipBox.isSelected())
		{
			status = "Yes";
		}
		else
		{
			status = "Not Selected";
		}
		return status;
	}
	
	/**
	 * Creates a criteria object with the SearchCommand's attributes.
	 * @return
	 */
	public Criteria getCriteria()

	{
		return new Criteria(state, industry, year, hours, compensation, international, internship);

	}
	
	/**
	 * @return a String with the search criteria.
	 */
	public String getSearchCriteria()
	{
		String criteriaToReturn = international + ", " + internship + ", " + state + ", " + year + ", " + compensation + 
				", " + industry + ", " + hours;
				
		return criteriaToReturn;
	}
	

}