/**
 * Class for criteria objects
 * Used to encapsulate the menu and button choices in Gui. 
 * @author Oliver
 *
 */
public class Criteria {
	private String state;
	private String industry;
	private String year;
	private String hours;
	private String compensation;
	private String international;
	private String internship;
	
	/**
	 * constructor for Criteria object.
	 * @param state
	 * @param industry
	 * @param year
	 * @param hours
	 * @param compensation
	 * @param international
	 * @param internship
	 */
	public Criteria (String state, String industry, String year, String hours, String compensation, String international, String internship) {
		this.state = state;
		this.industry = industry;
		this.year = year;
		this.hours = hours;
		this.compensation = compensation;
		this.international = international;
		this.internship = internship;
	}

	public String getState() {
		return state;
	}

	public String getIndustry() {
		return industry;
	}

	public String getYear() {
		return year;
	}

	public String getHours() {
		return hours;
	}

	public String getCompensation() {
		return compensation;
	}

	public String getInternational() {
		return international;
	}
	
	public String getInternship() {
		return internship;
	}
}
