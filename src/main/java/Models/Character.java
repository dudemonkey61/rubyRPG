package Models;

public class Character 
{
	private int userId;
	private int characterID;
	private String characterName;
	private int health;
	private int attack;
	
	public Character(int userID, int characterID, String characterName, int health, int attack)
	{
		this.setUserId(userID);
		this.setCharacterID(characterID);
		this.setCharacterName(characterName);
		this.setHealth(health);
		this.setAttack(attack);
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getCharacterID() 
	{
		return characterID;
	}

	public void setCharacterID(int characterID)
	{
		this.characterID = characterID;
	}

	public String getCharacterName() 
	{
		return characterName;
	}

	public void setCharacterName(String characterName)
	{
		this.characterName = characterName;
	}

	public int getHealth() 
	{
		return health;
	}

	public void setHealth(int health) 
	{
		this.health = health;
	}

	public int getAttack()
	{
		return attack;
	}

	public void setAttack(int attack) 
	{
		this.attack = attack;
	}
}
