public class VideoGameException extends Exception{
	private String message;

	public VideoGameException(String newMessage){
		message = newMessage;
	}

	public String toString(){
		return message;
	}
}
