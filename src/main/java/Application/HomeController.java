package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody LoginValidation loginGet(@RequestBody LoginData data) {
		LoginValidation code = new LoginValidation();
		try {
			Connection connection = DatabaseUrl.extract().getConnection();
	        Statement stmtCount = connection.createStatement();
	        Statement stmt = connection.createStatement();
	        ResultSet user = stmtCount.executeQuery("SELECT count(*) FROM Users WHERE username = '" + data.userName + "' AND password = '" + data.password + "'");
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
	public @ResponseBody ValidationCodes registerTransfer(@RequestBody RegisterData data) {
		ValidationCodes code = new ValidationCodes();
		try {
//	        URI dbUri = new URI("postgres://fghhopulwiaynq:OfvO_N_KLpwGqwbOZY7wEwKfL_@ec2-54-221-201-165.compute-1.amazonaws.com:5432/df02650vnkne80");
//			String dbusername = dbUri.getUserInfo().split(":")[0];
//			String dbpassword = dbUri.getUserInfo().split(":")[1];
//	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			++code.counter;
	        Connection connection = DatabaseUrl.extract().getConnection();
			++code.counter;
	        Statement stmtUser = connection.createStatement();
			++code.counter;
	        Statement stmtEmail = connection.createStatement();
			++code.counter;
	        Statement stmtInsert = connection.createStatement();
			++code.counter;
	        ResultSet userName = stmtUser.executeQuery("SELECT COUNT(*) FROM Users where username = '" + data.userName + "'");
			++code.counter;
	        ResultSet email = stmtEmail.executeQuery("SELECT count(*) FROM Users where email = '" + data.email + "'");
			++code.counter;
//	        while (userNames.next()) {
//	            System.out.println("Number of Users: " + userNames.getString(0));
//	        }
			code.counter = 20;
	        while (userName.next()) {
				code.counter = 50;
	        	if(userName.getInt(0) != 0) {
	    			code.counter = 100;
	        		code.UsernameTaken = true;
	        	}
	        }
			++code.counter;
	        while (email.next()) {
	        	if(email.getInt(0) != 0) {
	        		code.EmailTaken = true;
	        	}
	        }
			++code.counter;
	        String newPassword = data.password;
			++code.counter;
	        String newConfirmPassword = data.confirmPassword;
			++code.counter;
	        if(!newPassword.equals(newConfirmPassword)) {
	        	code.PasswordMismatch = true;
	        }
			++code.counter;
	        if(!code.UsernameTaken && !code.EmailTaken && !code.PasswordMismatch) {
	        	stmtInsert.execute("Insert into Users (username, email, password) values (" + data.userName + "," + data.email + "," + data.password + ")");
	        }
	        code.counter = -1;
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
