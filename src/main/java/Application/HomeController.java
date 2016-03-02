package Application;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heroku.sdk.jdbc.DatabaseUrl;

import dataTransfer.LoginData;
import dataTransfer.RegisterData;
//import Models.RegisterUserCredentials;

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
	public ResponseEntity<String> loginGet(@RequestBody LoginData data) {
		System.out.println(data.userName);
		System.out.println(data.password);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerTransfer(@RequestBody RegisterData data) {
		try {
//	        URI dbUri = new URI("postgres://fghhopulwiaynq:OfvO_N_KLpwGqwbOZY7wEwKfL_@ec2-54-221-201-165.compute-1.amazonaws.com:5432/df02650vnkne80");
//			String dbusername = dbUri.getUserInfo().split(":")[0];
//			String dbpassword = dbUri.getUserInfo().split(":")[1];
//	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	        try {
		        Connection connection = DatabaseUrl.extract().getConnection();
		        Statement stmt = connection.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Users");
		        while (rs.next()) {
		            System.out.println("Number of Users: " + rs.getString(0));
		        }
			} catch (SQLException e) {
				System.out.println("Finding Out What Broke");
			}

			System.out.println(data.userName);
			System.out.println(data.password);
			System.out.println(data.confirmPassword);
			System.out.println(data.email);
			
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
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
