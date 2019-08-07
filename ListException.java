public class ListException extends Exception
{
	private String message;

	public ListException(String s)
	{
		message = s;
	}

	public String toString()
	{
		return message;
	}
}
