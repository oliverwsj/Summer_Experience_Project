import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 * 
 * @author Jacob and Nick 
 *Class with methods that return the two types of table: fitlered and full.
 */
public class TableBuilder {
	List<Experience> experiences = null;

	public TableBuilder() 
	{
		//constructor stub
	}
	
	/**
	 * returns a table with all data in database
	 * @return
	 */
	public JTable getFullTable()
	{
		JTable fullTable = new JTable(); 
		try 
		{
			experiences = (ArrayList<Experience>) DatabaseConverter.getAllExperiences();
			
			//Just add it or print it here wherever you want in your guy
			ExperienceTableModel model = new ExperienceTableModel(experiences);
			fullTable.setModel(model);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fullTable;
		
	}
	
	/**
	 * Returns a table with the experiences in filteredExperienceList.
	 * @param filteredExperienceList
	 * @return
	 */
	public JTable getFilteredTable(ArrayList<Experience> filteredExperienceList)
	{
		JTable filteredTable = new JTable();
		ExperienceTableModel model = new ExperienceTableModel(filteredExperienceList);
		filteredTable.setModel(model);
		return filteredTable;
	}

}

