package Application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
	private int saltLen = 33;

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
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			String newSalt = new String(salt);
			
			String pass = hash256(data.password, newSalt);
			code.userName = pass;
			
			Connection connection = DatabaseUrl.extract().getConnection();
	        Statement stmtCount = connection.createStatement();
	        Statement stmt = connection.createStatement();
	        ResultSet user = stmtCount.executeQuery("SELECT count(*) FROM Users WHERE username = '" + data.userName + "' AND password = '" + data.password + "'");
	        while (user.next()) {
	        	if(user.getInt(1) == 0) {
	        		code.IncorrectUsernameOrPassword = true;
	        	}
	        }
	        if(!code.IncorrectUsernameOrPassword) {
		        ResultSet userInfo = stmt.executeQuery("SELECT * FROM Users WHERE username = '" + data.userName + "' AND password = '" + data.password + "'");
		        while (userInfo.next()) {
		        	if(userInfo.getInt(1) != 0) {
		        		code.userId = userInfo.getInt(1);
		        		code.userName = userInfo.getString(2);
		        	}
		        }
	        }
		} catch (Exception e) {
			code.databaseError = true;
		}
        return code;
	}
	
	 private static String hash256(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();

            for (int i=0; i<bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ValidationCodes registerTransfer(@RequestBody RegisterData data) {
		ValidationCodes code = new ValidationCodes();
		try {
			

			Connection connection = DatabaseUrl.extract().getConnection();
	        Statement stmtUser = connection.createStatement();
	        Statement stmtEmail = connection.createStatement();
	        Statement stmtInsert = connection.createStatement();
	        ResultSet userName = stmtUser.executeQuery("SELECT count(*) FROM Users WHERE username = '" + data.userName + "'");
	        ResultSet email = stmtEmail.executeQuery("SELECT count(*) FROM Users WHERE email = '" + data.email + "'");


	        while (userName.next()) {
	        	if(userName.getInt(1) != 0) {
	        		code.UsernameTaken = true;
	        	}
	        }
	        while (email.next()) {
	        	if(email.getInt(1) != 0) {
	        		code.EmailTaken = true;
	        	}
	        }
	        String newPassword = data.password;
	        String newConfirmPassword = data.confirmPassword;
	        if(!newPassword.equals(newConfirmPassword)) {
	        	code.PasswordMismatch = true;
	        }
	        if(!code.UsernameTaken && !code.EmailTaken && !code.PasswordMismatch) {
	        	//insert into characters (charatername, health, attack) values ("", 10, 10)
	        	//select characterid from characters where charactername = '" + charctername + "'
	        	//insert into usercharacters (userid, characterid) values (userid, characterid)
	        	stmtInsert.execute("Insert into Users (username, email, password) values ('" + data.userName + "','" + data.email + "','" + data.password + "')");
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
