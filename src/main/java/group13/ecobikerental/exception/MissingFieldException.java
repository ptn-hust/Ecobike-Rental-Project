package group13.ecobikerental.exception;

public class MissingFieldException extends EcobikeSystemException {
	public MissingFieldException() {
		super("ERROR: All field must be required");
	}
	
	public MissingFieldException(String message) {
		super(message);
	}

}
