import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ExperienceFilterTest {

	@Before
	public void setUpDB() throws SQLException {
		createSingletonDatabase.getInstance();
	}
	
	@Test
	public void DBisCreated() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		assertFalse(experiences.isEmpty());
	}
	
	@Test
	public void testSingleton() throws Exception {		//ensures prevention of more than one database creation, despite several instances
		createSingletonDatabase.getInstance();
		createSingletonDatabase.getInstance();
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		assertEquals(450, experiences.size());
	}
	
	@Test
	public void filteredListIsNotEmpty() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		ExperienceFilter filter = new ExperienceFilter(experiences);
		Criteria criteria = new Criteria("Select State", "Select Industry", "Select Year", "Select Hours/Week", "Select Financial Compensation", "Not Selected", "Not Selected");
		ArrayList<Experience> filteredExperiences = filter.filterDB(criteria);
		assertFalse(filteredExperiences.isEmpty());
	}
	
	@Test
	public void filtersToColoradoOnly() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		ExperienceFilter filter = new ExperienceFilter(experiences);
		Criteria criteria = new Criteria("Colorado", "Select Industry", "Select Year", "Select Hours/Week", "Select Financial Compensation", "Not Selected", "Not Selected");		
		ArrayList<Experience> filteredExperiences = filter.filterDB(criteria);
		boolean isOnlyColorado = true;
		for(Experience e : filteredExperiences) {
			if (!e.getState().equals("Colorado")) {
				isOnlyColorado = false;
			}
		}
		assertTrue(isOnlyColorado && !filteredExperiences.isEmpty());
	}
	
	@Test
	public void filtersToEducationOnly() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		ExperienceFilter filter = new ExperienceFilter(experiences);
		Criteria criteria = new Criteria("Select State", "Education", "Select Year", "Select Hours/Week", "Select Financial Compensation", "Not Selected", "Not Selected");		
		ArrayList<Experience> filteredExperiences = filter.filterDB(criteria);
		boolean isOnlyEducation = true;
		for(Experience e : filteredExperiences) {
			if (!e.getIndustry().equals("Education")) {
				isOnlyEducation = false;
			}
		}
		assertTrue(isOnlyEducation && !filteredExperiences.isEmpty());
	}
	
	@Test
	public void filtersToRisingJuniorOnly() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		ExperienceFilter filter = new ExperienceFilter(experiences);
		Criteria criteria = new Criteria("Select State", "Select Industry", "Rising Junior", "Select Hours/Week", "Select Financial Compensation", "Not Selected", "Not Selected");		
		ArrayList<Experience> filteredExperiences = filter.filterDB(criteria);
		boolean isOnlyRisingJuniors = true;
		for(Experience e : filteredExperiences) {
			if (!e.getYear().equals("Rising Junior")) {
				isOnlyRisingJuniors = false;
			}
		}
		assertTrue(isOnlyRisingJuniors && !filteredExperiences.isEmpty());
	}
	
	@Test
	public void filtersTo30PlusHoursOnly() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		ExperienceFilter filter = new ExperienceFilter(experiences);
		Criteria criteria = new Criteria("Select State", "Select Industry", "Select Year", "30+", "Select Financial Compensation", "Not Selected", "Not Selected");		
		ArrayList<Experience> filteredExperiences = filter.filterDB(criteria);
		boolean isOnly30Plus = true;
		for(Experience e : filteredExperiences) {
			if (!e.getHoursPerWeek().equals("30+")) {
				isOnly30Plus = false;
			}
		}
		assertTrue(isOnly30Plus && !filteredExperiences.isEmpty());
	}
	
	@Test
	public void filtersToPaidOnly() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		ExperienceFilter filter = new ExperienceFilter(experiences);
		Criteria criteria = new Criteria("Select State", "Select Industry", "Select Year", "Select Hours/Week", "Paid hourly wage", "Not Selected", "Not Selected");		
		ArrayList<Experience> filteredExperiences = filter.filterDB(criteria);
		boolean isPaidHourlyWage = true;
		for(Experience e : filteredExperiences) {
			if (!e.getCompensation().equals("Paid hourly wage")) {
				isPaidHourlyWage = false;
			}
		}
		assertTrue(isPaidHourlyWage && !filteredExperiences.isEmpty());
	}
	
	@Test
	public void filtersToInternationalOnly() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		ExperienceFilter filter = new ExperienceFilter(experiences);
		Criteria criteria = new Criteria("Select State", "Select Industry", "Select Year", "Select Hours/Week", "Select Financial Compensation", "Yes", "Not Selected");		
		ArrayList<Experience> filteredExperiences = filter.filterDB(criteria);
		boolean isOnlyInternational = true;
		for(Experience e : filteredExperiences) {
			if (!e.getInternational().equals("Yes")) {
				isOnlyInternational = false;
			}
		}
		assertTrue(isOnlyInternational && !filteredExperiences.isEmpty());
	}
	
	@Test
	public void filtersToInternshipOnly() throws Exception {
		DatabaseConverter converter = new DatabaseConverter();
		ArrayList<Experience> experiences = (ArrayList<Experience>) converter.getAllExperiences();
		ExperienceFilter filter = new ExperienceFilter(experiences);
		Criteria criteria = new Criteria("Select State", "Select Industry", "Select Year", "Select Hours/Week", "Select Financial Compensation", "Not Selected", "Yes");		
		ArrayList<Experience> filteredExperiences = filter.filterDB(criteria);
		boolean isOnlyInternships = true;
		for(Experience e : filteredExperiences) {
			if (!e.getInternship().equals("Yes")) {
				isOnlyInternships = false;
			}
		}
		assertTrue(isOnlyInternships && !filteredExperiences.isEmpty());
	}
	
	//Did not perform tests on user stories involving I/O (GUI or user input)
	
}
