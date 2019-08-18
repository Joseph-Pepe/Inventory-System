import java.io.*;
import java.util.Scanner;
public class ReadBinaryFile
{
	public static void main(String [] args)throws IOException
	{
		 boolean endOfFile = false;     // EOF flag
		 System.out.println("Enter in the file name: ");
		 Scanner keyboard = new Scanner(System.in);
		 String fileName = keyboard.nextLine().concat(".dat");

		 // Create the binary file input objects.
		 DataInputStream  inputFile = openFile(fileName);

		 while(inputFile == null)
	     {
			   System.out.println("Error: (The file: [" + fileName + "]) does not exist.\n" + "Enter another file name: ");
			   fileName = keyboard.nextLine();
			   inputFile = openFile(fileName);
	   	 }

		 System.out.println("Reading from the file:");
		 String contents = "";
		 // Read the contents of the file.
	     while (!endOfFile)
	     {
		    try
		    {
			   contents = inputFile.readUTF();
			   System.out.print(contents);
		    }
		    catch (EOFException e)
		    {
			   endOfFile = true;
		    }
        }

        System.out.println("\nDone.");

		// Close the file.
        inputFile.close();
	}

	private static DataInputStream openFile(String filename)
	{
		DataInputStream inputFile;
		try
		{
		 	FileInputStream fstream = new FileInputStream(filename);
		 	inputFile = new DataInputStream(fstream);
		}
		catch(FileNotFoundException e)
		{
			inputFile = null;
		}
		return inputFile;
	}
}