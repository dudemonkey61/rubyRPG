package dataTransfer;

import Models.Player;

public class LoginValidation {
	public boolean IncorrectUsernameOrPassword;
	public boolean databaseError;
	public Player playerData;
	
	public LoginValidation() {
		IncorrectUsernameOrPassword = false;
		databaseError = false;
		playerData = new Player();
	}
}
	