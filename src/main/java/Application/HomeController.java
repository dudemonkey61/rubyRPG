package Application;

import java.net.URI;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Models.RegisterUserCredentials;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "users/login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		return "users/login";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet() {
		return "users/register";
//		return new ModelAndView("users/register", "registerUserCredentials", new RegisterUserCredentials());
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerTransfer() {
		return "users/register";
	}
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("registerUserCredentials") RegisterUserCredentials userCredentials, BindingResult result) {
		try {
	        URI dbUri = new URI("postgres://fghhopulwiaynq:OfvO_N_KLpwGqwbOZY7wEwKfL_@ec2-54-221-201-165.compute-1.amazonaws.com:5432/df02650vnkne80");
			String dbusername = dbUri.getUserInfo().split(":")[0];
			String dbpassword = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	        
	        System.out.println(userCredentials.getUserName());
	        System.out.println(userCredentials.getEmail());
	        System.out.println(userCredentials.getPassword());
	        System.out.println(userCredentials.getConfirmPassword());
	        //"Select * from Users
	        
			return "redirect:login";
		} catch (Exception e) {
			return registerTransfer();
		}
	}
	@ModelAttribute("registerUserCredentials")
	public RegisterUserCredentials getRegisterUserCredentials() {
		return new RegisterUserCredentials();
	}
	
	@RequestMapping("/worldPage")
	public String worldPage() {
		return "worldPage";
	}
}
