package Application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dataTransfer.*;
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
	
	@RequestMapping("/townPage")
	public String townPage() {
		return "town";
	}
	
	@RequestMapping("/registerPage")
	public String registerPage() {
		return "users/register";
	}
	
	@RequestMapping("/worldPage")
	public String worldPage() {
		return "world";
	}
	
	@RequestMapping("/battlePage")
	public String battlePage() {
		return "battle";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginGet(@RequestBody LoginData data) {
		System.out.println(data.userName);
		System.out.println(data.password);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerTransfer(@RequestBody RegisterData data) {
		System.out.println(data.userName);
		System.out.println(data.password);
		System.out.println(data.email);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
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
