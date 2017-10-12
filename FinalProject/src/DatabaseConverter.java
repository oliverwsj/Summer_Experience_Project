import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Converts experience from row in database to type Experience in List
 * @author jacobnehama
 *
 */
public class DatabaseConverter {

public static final String PORT_NUMBER = "8889";

/**
 * Gets all of the experiences from the database so we can display them in our GUI
 *
 */
public static List<Experience> getAllExperiences() throws Exception {
	
	List<Experience> list = new ArrayList<>();
	
	Statement stmt = null;
	ResultSet myResults = null;
	
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} 
	catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} 
	try {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:" + PORT_NUMBER + "/Experiences?user=root&password=root"); // MySQL

		// Step 2: Allocate a "Statement" object in the Connection
		stmt = conn.createStatement();
		myResults = stmt.executeQuery("select * from T1");
		
		while (myResults.next()) {
			Experience tempExperience = convertRowToExperience(myResults);
			list.add(tempExperience);  
		}
		

		return list;		
	}
	catch(SQLException ex) {
		ex.printStackTrace();
	}
	finally {
		 stmt.close();
	}
	return list;
}

/**
 * Gets all of the experiences from our database and allows us to search them 
 * 
 *
 */
public static List<Experience> searchExperiences() throws Exception {
	List<Experience> list = new ArrayList<>();

	PreparedStatement myStmt = null;
	ResultSet myResults = null;

	try {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:" + PORT_NUMBER + "/Experiences?user=root&password=root"); // MySQL

		//lastName += "%";
		myStmt = conn.prepareStatement("select * from T1");
		
		myResults = myStmt.executeQuery();
		
		while (myResults.next()) {
			Experience tempExperience = convertRowToExperience(myResults);
			list.add(tempExperience);
		}
		
		return list;
	}
	finally {
		myStmt.close();
		myResults.close();
	}
}
/**
 * Converts experiences so that they are read as a row that will can contain the different attributes of experiences 
 *
 */
public static Experience convertRowToExperience(ResultSet myResults) throws SQLException {
	
	String year = myResults.getString("Question1"); 
	String international = myResults.getString("Question2"); 
	String internship = myResults.getString("Question3");   
	String org = myResults.getString("Question4"); 
	String state = myResults.getString("Question5"); 
	String city = myResults.getString("Question6");
	String industry = myResults.getString("Question7"); 
	String hoursPerWeek = myResults.getString("Question8"); 
	String compensation = myResults.getString("Question9"); 
	
	Experience tempExperience = new Experience(year, international, internship, org, state, city, industry, hoursPerWeek, compensation );	
	return tempExperience;
}

}