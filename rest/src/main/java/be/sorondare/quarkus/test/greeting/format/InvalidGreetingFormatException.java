package be.sorondare.quarkus.test.greeting.format;

public class InvalidGreetingFormatException extends Exception {
	public InvalidGreetingFormatException() {
	}

	public InvalidGreetingFormatException(String message) {
		super(message);
	}

	public InvalidGreetingFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidGreetingFormatException(Throwable cause) {
		super(cause);
	}

	public InvalidGreetingFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
