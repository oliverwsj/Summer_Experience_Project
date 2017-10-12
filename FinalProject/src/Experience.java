
/**
 * Experience class has all of the different experiences and their attributes 
 * it mostly consists from getters and setters
 *
 */
public class Experience 
{
	
	private String year;
	private String international;
	private String internship;
	private String organization;
	private String state;
	private String city;
	private String industry;
	private String hoursPerWeek;
	private String compensation;
	
	
	/**
	 * constructor. Assigns attributes from params
	 * @param year
	 * @param international
	 * @param internship
	 * @param organization
	 * @param state
	 * @param city
	 * @param natureOfWork
	 * @param hoursPerWeek
	 * @param compensation
	 */
	public Experience(String year, String international, String internship, String organization, String state,
			String city,  String natureOfWork, String hoursPerWeek,
			String compensation) 
	{
		this.year = year;
		this.international = international;
		this.internship = internship;
		this.organization = organization;
		this.state = state;
		this.city = city;
		this.industry = natureOfWork;
		this.hoursPerWeek = hoursPerWeek;
		if(compensation != null) {
			this.compensation = compensation.trim();	//to handle extra blank space at end
		} else {
			this.compensation = compensation;
		}
	}

	public String getYear() {
		return year;
	}

	public String getInternational() {
		return international;
	}

	public String getInternship() {
		return internship;
	}

	public String getOrganization() {
		return organization;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getIndustry() {
		return industry;
	}

	public String getHoursPerWeek() {
		return hoursPerWeek;
	}

	public String getCompensation() {
		return compensation;
	}

}