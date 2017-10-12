
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**Simple class containing method to create String[] from txt file
 * 
 * @author Nick
 *
 */
public class GuiFileIO
{
	/**
	 * gets a string[] from a txt file.
	 * @param fileName
	 * @return
	 */
	public String[] getFileLinesAsStringArray(String fileName)
	{
		 try
		    {
			
		      File file = new File(fileName);
		      long lineCount = Files.lines(Paths.get(fileName)).count();
		      String[] lines = new String[(int) lineCount];
		      Scanner s = new Scanner(file);
		      int i = 0;
		      while(s.hasNextLine())
		      {
		        String next = s.nextLine();
		        lines[i] = next;
		        i++;	       
		      }
		      return lines;
		    }
		    catch(IOException e)
		    {
		      System.out.println("There has been an ERROR: " + e);
		    }
		 //error case
		 return null;	
	}
}