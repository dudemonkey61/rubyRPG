package Models;

public class RegisterUserCredentials {
	private String userName;
	private String email;
	private String password;
	private String confirmPassword;
	
	public RegisterUserCredentials() {
		setUserName("");
		setEmail("");
		setPassword("");
		setConfirmPassword("");
	}
	public RegisterUserCredentials(String userName, String email, String password, String checkPassword) {
		setUserName(userName);
		setEmail(email);
		setPassword(password);
		setConfirmPassword(checkPassword);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}	
}
