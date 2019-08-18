import java.util.Scanner;
import java.io.*;
public class SaveInventoryItems
{
	public void SaveInventoryToTextFile(ListInterface<VideoGame> gameInventory)
	{
		try
		{
			String filename;

			Scanner keyboard = new Scanner(System.in);

			System.out.print("Save file as : ");
			filename = keyboard.nextLine();

			// Make sure the file does not exist.
			File file = new File(filename.concat(".txt"));
			if (file.exists())
			{
			   System.out.println("The file " + filename + " already exists.");
			   System.exit(0);
			}

			PrintWriter outputFile = new PrintWriter(file);

			outputFile.println(gameInventory);

			outputFile.close();
      		        System.out.println("Data written to the file.");
		}
		catch(IOException io)
		{
			System.out.println("Unable to open file");
		}
	}
}
