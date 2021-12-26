package be.sorondare.quarkus.test.commons;

public class AlreadyExistsException extends Exception {

	private final String id;

	public AlreadyExistsException(String id) {
		this.id = id;
	}

	public AlreadyExistsException(String message, String id) {
		super(message);
		this.id = id;
	}

	public AlreadyExistsException(String message, Throwable cause, String id) {
		super(message, cause);
		this.id = id;
	}

	public AlreadyExistsException(Throwable cause, String id) {
		super(cause);
		this.id = id;
	}

	public AlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String id) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.id = id;
	}
}
