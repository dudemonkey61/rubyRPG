package dataTransfer;

public class ValidationCodes {
	public boolean UsernameTaken;
	public boolean EmailTaken;
	public boolean PasswordMismatch;
	public boolean databaseError;
	public int counter;
	public boolean UsernameEmpty;
	public boolean EmailEmpty;
	
	public ValidationCodes() {
		UsernameTaken = false;
		EmailTaken = false;
		PasswordMismatch = false;
		databaseError = false;
		counter = 0;
		UsernameEmpty = false;
		EmailEmpty = false;
	}
}
