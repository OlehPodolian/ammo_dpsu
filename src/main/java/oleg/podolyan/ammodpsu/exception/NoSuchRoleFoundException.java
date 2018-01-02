package oleg.podolyan.ammodpsu.exception;

public class NoSuchRoleFoundException extends Throwable {

	private String message;

	public NoSuchRoleFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
