package be.sorondare.quarkus.test.greeting.format;

public class EmptyFormatException extends Exception {
	public EmptyFormatException() {
	}

	public EmptyFormatException(String message) {
		super(message);
	}

	public EmptyFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyFormatException(Throwable cause) {
		super(cause);
	}

	public EmptyFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
