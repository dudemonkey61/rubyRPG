package dataTransfer;

public class LoginValidation {
	public boolean IncorrectUsernameOrPassword;
	public boolean databaseError;
	public int userId;
	public String userName;
	
	public LoginValidation() {
		IncorrectUsernameOrPassword = false;
		databaseError = false;
		userId = -1;
		userName = "";
	}
}
	