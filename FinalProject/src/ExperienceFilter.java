import java.util.ArrayList;

/**
 * 
 * @author Oliver
 * The ExperienceFilter can be used to filter an ArrayList of Experience data, takes in said ArrayList
 */
public class ExperienceFilter {
	private static final String STATE_NOT_SELECTED = "Select State"; 
	private static final String INDUSTRY_NOT_SELECTED = "Select Industry"; 
	private static final String YEAR_NOT_SELECTED = "Select Year"; 
	private static final String HOURS_NOT_SELECTED = "Select Hours/Week"; 
	private static final String COMPENSATION_NOT_SELECTED = "Select Financial Compensation"; 
	private static final String INTERNATIONAL_NOT_SELECTED = "Not Selected"; 
	private static final String INTERNSHIP_NOT_SELECTED = "Not Selected";
	private String state;
	private String industry;
	private String year;
	private String hours;
	private String compensation;
	private String international;
	private String internship;
	
	ArrayList<Experience> allExperiences;
	
	/**
	 * 
	 * @param allExperiences, ArrayList of all experiences in database
	 */
	public ExperienceFilter(ArrayList<Experience> allExperiences) {
		this.allExperiences = allExperiences;
	}
	
	/**
	 * 
	 * @param criteria, contains users filter criteria
	 * @return ArrayList of matching Experiences
	 */
	public ArrayList<Experience> filterDB(Criteria criteria) {
		ArrayList<Experience> matchingExperiences = new ArrayList<Experience>();
		matchingExperiences.addAll(allExperiences);
		ArrayList<Experience> experiencesToRemove = new ArrayList<Experience>();
		state = criteria.getState();
		industry = criteria.getIndustry();
		year = criteria.getYear();
		hours = criteria.getHours();
		compensation = criteria.getCompensation();
		international = criteria.getInternational();
		internship = criteria.getInternship();
		for(Experience e : matchingExperiences) {
			if( !state.equals(STATE_NOT_SELECTED) && !state.equals(e.getState())) {		//state filter
				experiencesToRemove.add(e);
			} 
			else if( !industry.equals(INDUSTRY_NOT_SELECTED) && !industry.equals(e.getIndustry())) {		//industry filter
				experiencesToRemove.add(e);
			} 
			else if( !year.equals(YEAR_NOT_SELECTED) && !year.equals(e.getYear())) {		//year (class standing) filter
				experiencesToRemove.add(e);
			} 
			else if( !hours.equals(HOURS_NOT_SELECTED) && !hours.equals(e.getHoursPerWeek())) {		//hours filter
				experiencesToRemove.add(e);
			} 
			else if( !compensation.equals(COMPENSATION_NOT_SELECTED) && !compensation.equals(e.getCompensation())) {		//compensation filter
				experiencesToRemove.add(e);
			} 
			else if( !international.equals(INTERNATIONAL_NOT_SELECTED) && !international.equals(e.getInternational())) {		//international filter
				experiencesToRemove.add(e);
			} 
			else if( !internship.equals(INTERNSHIP_NOT_SELECTED) && !internship.equals(e.getInternship())) {		//internship filter
				experiencesToRemove.add(e);
			}
		}
		matchingExperiences.removeAll(experiencesToRemove);		//removes non-matching Experiences
		return matchingExperiences;
	}
	
}


