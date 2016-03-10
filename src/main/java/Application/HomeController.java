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

import Logic.CombatLogic;
import Logic.MarketLogic;
import Models.Player;
import dataTransfer.LoginData;
import dataTransfer.LoginValidation;
import dataTransfer.RegisterData;
//import Models.RegisterUserCredentials;
import dataTransfer.ValidationCodes;
import dto.CombatObject;
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
	public @ResponseBody LoginValidation loginGet(@RequestBody LoginData data) {
		LoginValidation code = new LoginValidation();
		try {
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
//		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ValidationCodes registerTransfer(@RequestBody RegisterData data) {
		ValidationCodes code = new ValidationCodes();
		try {
	        Connection connection = DatabaseUrl.extract().getConnection();
	        Statement stmtUser = connection.createStatement();
	        Statement stmtEmail = connection.createStatement();
	        Statement stmtInsert = connection.createStatement();
	        Statement stmtCharacterCreate = connection.createStatement();
	        Statement stmtUserData = connection.createStatement();
	        Statement stmtCharacterData = connection.createStatement();
	        Statement stmtCharacterRelate = connection.createStatement();
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
	        	stmtInsert.execute("Insert into Users (username, email, password) values ('" + data.userName + "','" + data.email + "','" + data.password + "')");
	        	stmtCharacterCreate.execute("Insert into Characters (charactername, attack, maxhealth, currenthealth) values ('" + data.userName + "', 10, 10, 10)");
	        	ResultSet userData = stmtUserData.executeQuery("SELECT userID FROM users WHERE username = '" + data.userName + "'");
	        	ResultSet characterData = stmtCharacterData.executeQuery("SELECT characterID FROM Characters WHERE charactername = '" + data.userName + "'");
	        	int userID = -1;
	        	int characterID = -1;
	        	while (userData.next()) {
	        		userID = userData.getInt(1);
	        	}
	        	while (characterData.next()) {
	        		characterID = characterData.getInt(1);
	        	}
	        	stmtCharacterRelate.execute("Insert into userCharacters (userid, characterid) values (" + userID + ", " + characterID + ")");
	        }
		} catch (Exception e) {
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
	
	@RequestMapping(value = "/buy/Attack", method = RequestMethod.POST)
	public @ResponseBody MarketObject increaseAttack(@RequestBody MarketObject data) 
	{
		data = MarketLogic.buyAttack(data);
		
		try 
		{
			Connection connection = DatabaseUrl.extract().getConnection();
			Statement stmtUser = connection.createStatement();
			stmtUser.execute("UPDATE Characters SET zeni = '" + data.getPlayer().getMoney() + "'  WHERE characterid = '" + data.getPlayer().getCharacterID() + "'");
			stmtUser.execute("UPDATE Characters SET attack = '" + data.getPlayer().getAttack() + "'  WHERE characterid = '" + data.getPlayer().getCharacterID() + "'");
		} 
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return data;
	}
	
	@RequestMapping(value = "/buy/Health", method = RequestMethod.POST)
	public @ResponseBody MarketObject increaseHealth(@RequestBody MarketObject data) 
	{
		data = MarketLogic.buyHealth(data);
		
		try 
		{
			Connection connection = DatabaseUrl.extract().getConnection();
			Statement stmtUser = connection.createStatement();
			stmtUser.execute("UPDATE Characters SET zeni = '" + data.getPlayer().getMoney() + "'  WHERE characterid = '" + data.getPlayer().getCharacterID() + "'");
			stmtUser.execute("UPDATE Characters SET currenthealth = '" + data.getPlayer().getCurrentHealth() + "'  WHERE characterid = '" + data.getPlayer().getCharacterID() + "'");
			stmtUser.execute("UPDATE Characters SET maxhealth = '" + data.getPlayer().getMaxHealth() + "'  WHERE characterid = '" + data.getPlayer().getCharacterID() + "'");
		} 
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return data;
	}
	
	@RequestMapping(value = "/buy/Potion", method = RequestMethod.POST)
	public @ResponseBody MarketObject addPotions(@RequestBody MarketObject data) 
	{
		data = MarketLogic.buyPotion(data);
		
		try 
		{
			Connection connection = DatabaseUrl.extract().getConnection();
			Statement stmtUser = connection.createStatement();
			stmtUser.execute("UPDATE Characters SET zeni = '" + data.getPlayer().getMoney() + "'  WHERE characterid = '" + data.getPlayer().getCharacterID() + "'");
			stmtUser.execute("UPDATE Characters SET healItems = '" + data.getPlayer().getHealItems() + "'  WHERE characterid = '" + data.getPlayer().getCharacterID() + "'");
		} 
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return data;
	}
	
	@RequestMapping(value = "/combat/pve/Attack", method = RequestMethod.POST)
	public @ResponseBody CombatObject increaseAttack(@RequestBody CombatObject data) 
	{
		data = CombatLogic.playerAttack(data);
		
		if(data.getThePlayer().getCurrentHealth() > 0 && data.getTheEnemy().getHealth() <= 0)
		{
			data = CombatLogic.survivingPlayer(data);
		}
		
		else
		{
			data = CombatLogic.enemyAttack(data);
		}
		
		if(data.getThePlayer().getCurrentHealth() <= 0)
		{
			data = CombatLogic.dieingPlayer(data);
		}
		
		try 
		{
			Connection connection = DatabaseUrl.extract().getConnection();
			Statement stmtUser = connection.createStatement();
			stmtUser.execute("UPDATE Characters SET currenthealth = '" + data.getThePlayer().getCurrentHealth() + "'  WHERE characterid = '" + data.getThePlayer().getCharacterID() + "'");
			stmtUser.execute("UPDATE Characters SET zeni = '" + data.getThePlayer().getMoney() + "'  WHERE characterid = '" + data.getThePlayer().getCharacterID() + "'");
		} 
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return data;
	}
	
	@RequestMapping(value = "/combat/pve/Heal", method = RequestMethod.POST)
	public @ResponseBody CombatObject increaseHealth(@RequestBody CombatObject data) 
	{		
		data = CombatLogic.healPlayer(data);
		
		if(data.getThePlayer().getCurrentHealth() > 0 && data.getTheEnemy().getHealth() <= 0)
		{
			data = CombatLogic.survivingPlayer(data);
		}
		
		else
		{
			data = CombatLogic.enemyAttack(data);
		}
		
		if(data.getThePlayer().getCurrentHealth() <= 0)
		{
			data = CombatLogic.dieingPlayer(data);
		}
		
		try 
		{
			Connection connection = DatabaseUrl.extract().getConnection();
			Statement stmtUser = connection.createStatement();
			//Table that relates characters to users:		usercharacters
			//Columns:		characterid		chactername		health		attack		healingitems	zeni
			//DataTypes:	int				string			int			int			int				int
			//ResultSet character = stmtUser.executeQuery("SELECT health FROM Characters WHERE characterid = '" + data.getThePlayer().getCharacterID() + "'");
			stmtUser.execute("UPDATE Characters SET currenthealth = '" + data.getThePlayer().getCurrentHealth() + "'  WHERE characterid = '" + data.getThePlayer().getCharacterID() + "'");
			stmtUser.execute("UPDATE Characters SET healthitems = '" + data.getThePlayer().getHealItems() + "'  WHERE characterid = '" + data.getThePlayer().getCharacterID() + "'");
			stmtUser.execute("UPDATE Characters SET zeni = '" + data.getThePlayer().getMoney() + "'  WHERE characterid = '" + data.getThePlayer().getCharacterID() + "'");
			//while(character.next())
			//{
				//int health = character.getInt(1);
			//}
		} 
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
		return data;
	}
	
	@RequestMapping(value = "/combat/pve", method = RequestMethod.POST)
	public @ResponseBody CombatObject startCombat(@RequestBody Player data) 
	{
		CombatObject combat = new CombatObject(data, CombatLogic.createEnemy(data));
		return combat;
	}
}
