package Models;

public class Enemy 
{
	private String name;
	private int health;
	private int attack;
	
	public Enemy(String name, int health, int attack)
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
	}
	
	public Enemy(){}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getHealth() {
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
