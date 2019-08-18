import java.util.Scanner;
import java.io.*;
public class WriteBinaryFile
{
	public void SaveInventoryAsBinaryFile(ListInterface<VideoGame> gameInventory)throws ListException
	{
		try
		{
			String filename;
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Save Binary file as : ");
			filename = keyboard.nextLine();

			FileOutputStream fstream = new FileOutputStream(filename.concat(".dat"));
			DataOutputStream outputFile = new DataOutputStream(fstream);

			System.out.println("Writing to the file...");
			for(int index = 1; index <= gameInventory.getCurrentSize();index++)
				outputFile.writeUTF(gameInventory.get(index).toString());

			System.out.println("Done.");
			// Close the file.
			outputFile.close();
		}
		catch(IOException e)
		{
			System.out.println("Unable to open file");
		}
	}
}