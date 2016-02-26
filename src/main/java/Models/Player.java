package Models;

public class Player 
{
	private int userId;
	private int characterID;
	private String characterName;
	private int health;
	private int attack;
	private int healItems;
	private int money;
	
	public Player(int userID, int characterID, String characterName, int health, int attack, int healItems, int money)
	{
		this.setUserId(userID);
		this.setCharacterID(characterID);
		this.setCharacterName(characterName);
		this.setHealth(health);
		this.setAttack(attack);
		this.setHealItems(healItems);
		this.setMoney(money);
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

	public int getHealItems() {
		return healItems;
	}

	public void setHealItems(int healItems) {
		this.healItems = healItems;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
