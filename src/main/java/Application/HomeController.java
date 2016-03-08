package Application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Logic.CombatLogic;
import Logic.MarketLogic;
import Models.Enemy;
import Models.Player;
import dataTransfer.*;
import dto.CombatObject;
//import Models.RegisterUserCredentials;
import dto.MarketObject;

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
	
	@RequestMapping("/town")
	public String town() {
		return "townPage";
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
	
	@RequestMapping(value = "/buy/Attack", method = RequestMethod.POST)
	public @ResponseBody MarketObject increaseAttack(@RequestBody MarketObject data) 
	{
		data = MarketLogic.buyAttack(data);
		return data;
	}
	
	@RequestMapping(value = "/buy/Health", method = RequestMethod.POST)
	public @ResponseBody MarketObject increaseHealth(@RequestBody MarketObject data) 
	{
		data = MarketLogic.buyHealth(data);
		return data;
	}
	
	@RequestMapping(value = "/buy/Attack", method = RequestMethod.POST)
	public @ResponseBody MarketObject addPotions(@RequestBody MarketObject data) 
	{
		data = MarketLogic.buyPotion(data);
		return data;
	}
	
	@RequestMapping(value = "/combat/pve/Attack", method = RequestMethod.POST)
	public @ResponseBody CombatObject increaseAttack(@RequestBody CombatObject data) 
	{
		data = CombatLogic.playerAttack(data);
		data = CombatLogic.enemyAttack(data);
		return data;
	}
	
	@RequestMapping(value = "/combat/pve/Heal", method = RequestMethod.POST)
	public @ResponseBody CombatObject increaseHealth(@RequestBody CombatObject data) 
	{
		data = CombatLogic.healPlayer(data);
		data = CombatLogic.enemyAttack(data);
		return data;
	}
	
	@RequestMapping(value = "/combat/pve", method = RequestMethod.POST)
	public @ResponseBody CombatObject startCombat(@RequestBody Player data) 
	{
		CombatObject combat = new CombatObject(data, CombatLogic.createEnemy(data));
		return combat;
	}
}
