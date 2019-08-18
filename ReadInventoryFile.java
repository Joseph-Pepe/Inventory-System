import java.util.Scanner;
import java.io.*;
public class ReadInventoryFile
{
	public static void main(String [] args)
	{
	   String fileName = "";
	   System.out.println("Enter the name of the file you want to read from: ");
	   Scanner keyboard = new Scanner(System.in);
	   fileName = keyboard.nextLine().concat(".txt");

	   Scanner inputFile = openFile(fileName);

	   while(inputFile == null)
	   {
		   System.out.println("Error: (The file: [" + fileName + "]) does not exist.\n" + "Enter another file name: ");
		   fileName = keyboard.nextLine().concat(".txt");
		   inputFile = openFile(fileName);
	   }

	   while(inputFile.hasNext())
	   {
		  String contents = inputFile.nextLine();
		  System.out.println(contents);
	   }
	   inputFile.close();
	}

	private static Scanner openFile(String filename)
	{
		Scanner scan;
		try
		{
			File file = new File(filename);
			scan = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			scan = null;
		}
		return scan;
	}
}
