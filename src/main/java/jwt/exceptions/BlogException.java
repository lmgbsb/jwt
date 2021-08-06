package jwt.exceptions;

public class BlogException   extends RuntimeException {

	public BlogException(String exMessage, Exception exception){
        super(exMessage, exception);
    }

    public BlogException(String exMessage) {
        super(exMessage);
    }
}
