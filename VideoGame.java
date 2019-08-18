import java.util.Random;
import java.io.Serializable;

public class VideoGame implements Comparable<VideoGame>, Copyable,Serializable
{
	private static enum Genre { Action, Adventure, Sport,Shooter, Racing, RPG }

	private static String platforms = " PS3 , PS4 , Xbox One , PC , Nintendo Switch ";

	private int sku,yearReleased,quantity;

	private String title,genre,platform;

	private float price;

	private static int countNumTitles = 0;
	private final int MAX_SKU_NUMBER = 9999;
	private final int MIN_SKU_NUMBER = 1000;
	private static Random rand = new Random();

	public VideoGame(){
		yearReleased = 2018;
		price = 59.99f;
		genre = "N/A";
		title = "N/A";
		quantity = 1;
		sku = rand.nextInt(MAX_SKU_NUMBER) + MIN_SKU_NUMBER;
		platform = "N/A";
		countNumTitles++;
	}

	public VideoGame(int newYearReleased, float newPrice, String newGenre, String newTitle,int newQuantity,String newPlatform)throws VideoGameException{
		setYearReleased(newYearReleased);
		setPrice(newPrice);
		setGenre(newGenre);
		setTitle(newTitle);
		setQuantity(newQuantity);
		sku = rand.nextInt(MAX_SKU_NUMBER) +  MIN_SKU_NUMBER;
		setPlatform(newPlatform);
		countNumTitles++;
	}

	protected VideoGame(final VideoGame otherGame){
		yearReleased = otherGame.yearReleased;
		price = otherGame.price;
		genre = otherGame.genre;
		title = otherGame.title;
		quantity = otherGame.quantity;
		sku = otherGame.sku;
		platform = otherGame.platform;
		countNumTitles++;
	}

	public void setPlatform(String newPlatform)throws VideoGameException{

		String array[] = newPlatform.split(",");
		String result = "";

		int numberOfPlatforms = 0;

		for(int index = 0; index < array.length; index++){
			if(platforms.contains(array[index])){
				numberOfPlatforms += 1;
				if(numberOfPlatforms > 1)
					result += ", ";

				result += array[index];
			}
		}

		if(numberOfPlatforms <= 0 || result.isBlank() )
			throw new VideoGameException("Error:  Must Choose from: PS3 , PS4 , Xbox One , PC , Nintendo Switch ");

			platform = result;
	}

	public String getPlatform(){
		return platform;
	}

	public void setYearReleased(int releaseDate)throws VideoGameException{
		int minReleaseDate = 2000;

		if(releaseDate < minReleaseDate)
			throw new VideoGameException("Error: Release Date must be no earlier than " + minReleaseDate  +"! FOR - " + getTitle());

		 	yearReleased = releaseDate;
	}

	public int getYearReleased(){
		return yearReleased;
	}

	public void setPrice(float newPrice)throws VideoGameException{
		float minPrice = 4.99f; float maxPrice = 149.99f;

		if(newPrice < minPrice || newPrice > maxPrice)
			throw new VideoGameException("Error: Price can't be less " + minPrice +  " or higher than " + maxPrice + " in " + getTitle());

		 	price = newPrice;
	}

	public float getPrice(){
		return price;
	}

	public void setQuantity(int newQuantity)throws VideoGameException{
		if(newQuantity < -1)
		 	throw new VideoGameException("Error: Quantity must be greater than or equal to -1!");

		 	quantity = newQuantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setGenre(String newGenre)throws VideoGameException{
		boolean found = false;

		for(int index = 0; index < Genre.values().length; index++){
		 	if(newGenre.equals(Genre.values()[index].toString())){
		 	 	genre = newGenre;
		 	 	found = true;
			}
		}

	    if(found == false)
			throw new VideoGameException("Error:  Must Choose from: RPG, Shooter, Action, Adventure, Sport, Racing");
	}

	public String getGenre(){
		return genre;
	}

	public void setTitle(String newTitle)throws VideoGameException{
		if(newTitle.equals(""))
			throw new VideoGameException("Error: Title can't be blank." + getClass());

			title = newTitle;
	}

	public String getTitle(){
		return title;
	}

	public void setSku(int newSkuNumber)throws VideoGameException{
		if(newSkuNumber < MIN_SKU_NUMBER  && newSkuNumber > MAX_SKU_NUMBER)
			throw new VideoGameException("Error: ID number must be between " + MIN_SKU_NUMBER + " and " + MAX_SKU_NUMBER + " FOR - " + getTitle());

		 	sku = newSkuNumber;
	}

	public int getSkuNumber(){
		return sku;
	}

	public int getNumberOfTitles(){
		return countNumTitles;
	}

	public String toString(){
		return title + ":\n[Platform:" + platform + ", Genre:" + genre + ", Price:$" + price + ", SKU#:" + sku + ", In Stock:" + quantity + "]\n\n";
	}

	public boolean equals(Object obj)
	{
		if(obj == null || obj.getClass() != this.getClass())
			return false;

			VideoGame otherGame = (VideoGame)obj;

			return sku == otherGame.sku && price == otherGame.price && title.equals(otherGame.title)
							&& platform.equals(otherGame.platform) && yearReleased == otherGame.yearReleased
								 && genre.equals(otherGame.genre) && otherGame != null;
    }

	public int compareTo(VideoGame otherGame){

		if(this.equals(otherGame))
			return 0;
		else if(price < otherGame.price)
			return -1;
		else
			return 1;
	}

	public int hashCode(){
		final int number = 10;
		int result = 1;

		result *= number + ((title == null) ? 0 : title.hashCode());
		result *= number + sku;
		result *= number + quantity;
		return result;
	}

	public Object clone(){
		return new VideoGame(this);
	}

	protected void finalize(){
		countNumTitles--;
		System.out.println(title + ": Sold Out\n");
	}
}
