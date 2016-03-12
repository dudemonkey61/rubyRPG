package Models;

public class Player 
{
	private int userId;
	private int characterID;
	private String characterName;
	private int currentHealth;
	private int maxHealth;
	private int attack;
	private int healItems;
	private int money;
	private String town;
	
	public Player(int userID, int characterID, String characterName, int currentHealth, int maxHealth, int attack, int healItems, int money)
	{
		this.setUserId(userID);
		this.setCharacterID(characterID);
		this.setCharacterName(characterName);
		this.setMaxHealth(maxHealth);
		this.setAttack(attack);
		this.setHealItems(healItems);
		this.setMoney(money);
		this.setTown(characterName);
	}
	
	public Player(){}

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

	public int getMaxHealth() 
	{
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) 
	{
		this.maxHealth = maxHealth;
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

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
}
