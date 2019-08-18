import java.io.*;
import java.util.Scanner;
public class SerializeObjects
{
	public static void SerializeTheObject(ListInterface<VideoGame> gameInventory)throws ListException,IOException
	{
		FileOutputStream outStream = new FileOutputStream("objects.dat");
		ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

		//Write the Serialized objects to the file.
		for(int index = 0; index < gameInventory.getCurrentSize(); index++)
			objectOutputFile.writeObject(gameInventory.get(index));

		objectOutputFile.close();

		System.out.println("Objects written to objects.dat file.");
	}

	public static void DeserializeTheObject(ListInterface<VideoGame> gameInventory)throws ListException,IOException,ClassNotFoundException
	{
			FileInputStream inStream = new FileInputStream("objects.dat");
			ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

			VideoGame [] listOfGames = new VideoGame[gameInventory.getCurrentSize()];

			//Read the Serialized objects from the file.
			for(int index = 0; index < gameInventory.getCurrentSize(); index++)
				listOfGames[index] = (VideoGame) objectInputFile.readObject();


			objectInputFile.close();

			for(VideoGame currentGame: listOfGames)
				System.out.println(currentGame + "\n");
	}
}