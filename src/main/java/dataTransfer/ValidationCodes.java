package dataTransfer;

public class ValidationCodes {
	public boolean UsernameTaken;
	public boolean EmailTaken;
	public boolean PasswordMismatch;
	public boolean databaseError;
	
	public ValidationCodes() {
		UsernameTaken = false;
		EmailTaken = false;
		PasswordMismatch = false;
		databaseError = false;
	}
}
