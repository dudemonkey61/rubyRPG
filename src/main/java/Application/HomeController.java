package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heroku.sdk.jdbc.DatabaseUrl;

import dataTransfer.LoginData;
import dataTransfer.LoginValidation;
import dataTransfer.RegisterData;
//import Models.RegisterUserCredentials;
import dataTransfer.ValidationCodes;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/loginPage")
	public String loginPage() {
		return "users/login";
	}
	
	@RequestMapping("/registerPage")
	public String registerPage() {
		return "users/register";
	}
	
	@RequestMapping("/worldPage")
	public String worldPage() {
		return "worldPage";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginValidation loginGet(@RequestBody LoginData data) {
		LoginValidation code = new LoginValidation();
		try {
			Connection connection = DatabaseUrl.extract().getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet user = stmt.executeQuery("SELECT count(*) FROM Users WHERE username = '" + data.userName + "' AND password = '" + data.password + "'");
	        while (user.next()) {
	        	if(user.getInt(0) == 0) {
	        		code.IncorrectUsernameOrPassword = true;
	        	}
	        }
	        if(!code.IncorrectUsernameOrPassword) {
		        ResultSet userInfo = stmt.executeQuery("SELECT * FROM Users WHERE username = '" + data.userName + "' AND password = '" + data.password + "'");
		        while (userInfo.next()) {
		        	if(userInfo.getInt(0) == 0) {
		        		code.userId = userInfo.getInt(0);
		        		code.userName = userInfo.getString(1);
		        	}
		        }
	        }
		} catch (Exception e) {
			code.databaseError = true;
		}
        return code;
//		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ValidationCodes registerTransfer(@RequestBody RegisterData data) {
		ValidationCodes code = new ValidationCodes();
		try {
//	        URI dbUri = new URI("postgres://fghhopulwiaynq:OfvO_N_KLpwGqwbOZY7wEwKfL_@ec2-54-221-201-165.compute-1.amazonaws.com:5432/df02650vnkne80");
//			String dbusername = dbUri.getUserInfo().split(":")[0];
//			String dbpassword = dbUri.getUserInfo().split(":")[1];
//	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	        Connection connection = DatabaseUrl.extract().getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet userName = stmt.executeQuery("SELECT COUNT(*) FROM Users where username = '" + data.userName + "'");
	        ResultSet email = stmt.executeQuery("SELECT count(*) FROM Users where email = '" + data.email + "'");
//	        while (userNames.next()) {
//	            System.out.println("Number of Users: " + userNames.getString(0));
//	        }
	        while (userName.next()) {
	        	if(userName.getInt(0) != 0) {
	        		code.UsernameTaken = true;
	        	}
	        }
	        while (email.next()) {
	        	if(email.getInt(0) != 0) {
	        		code.EmailTaken = true;
	        	}
	        }
	        String newPassword = data.password;
	        String newConfirmPassword = data.confirmPassword;
	        if(!newPassword.equals(newConfirmPassword)) {
	        	code.PasswordMismatch = true;
	        }
	        if(!code.UsernameTaken && !code.EmailTaken && !code.PasswordMismatch) {
	        	stmt.execute("Insert into Users (username, email, password) values (" + data.userName + "," + data.email + "," + data.password + ")");
	        }
			//return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			//return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			code.databaseError = true;
		}
		return code;
	}
	
//	@RequestMapping(value = "/validate", method = RequestMethod.POST)
//	public String registerPost(@ModelAttribute("registerUserCredentials") RegisterUserCredentials userCredentials, BindingResult result) {
//		try {
//	        URI dbUri = new URI("postgres://fghhopulwiaynq:OfvO_N_KLpwGqwbOZY7wEwKfL_@ec2-54-221-201-165.compute-1.amazonaws.com:5432/df02650vnkne80");
//			String dbusername = dbUri.getUserInfo().split(":")[0];
//			String dbpassword = dbUri.getUserInfo().split(":")[1];
//	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//	        
//	        System.out.println(userCredentials.getUserName());
//	        System.out.println(userCredentials.getEmail());
//	        System.out.println(userCredentials.getPassword());
//	        System.out.println(userCredentials.getConfirmPassword());
//	        //"Select * from Users
//	        
//			return "redirect:login";
//		} catch (Exception e) {
//			return registerTransfer();
//		}
//	}
	
//	@ModelAttribute("registerUserCredentials")
//	public RegisterUserCredentials getRegisterUserCredentials() {
//		return new RegisterUserCredentials();
//	}
	
}
