package Logic;

import java.util.Random;
import Models.Enemy;
import Models.Player;
import dto.CombatObject;

public class CombatLogic 
{
	private static Random rand = new Random();
	public static CombatObject playerAttack(CombatObject combat)
	{
		int attack = combat.getThePlayer().getAttack();
		
		int health = combat.getTheEnemy().getHealth();
		
		int damage = health - attack;
		
		combat.getTheEnemy().setHealth(damage);
		
		return combat;
	}
	
	public static CombatObject enemyAttack(CombatObject combat)
	{
		int attack = combat.getTheEnemy().getAttack();
		
		int health = combat.getThePlayer().getHealth();
		
		int damage = health - attack;
		
		combat.getThePlayer().setHealth(damage);
		
		return combat;
	}
	
	public static CombatObject healPlayer(CombatObject combat)
	{
		if(combat.getThePlayer().getHealItems() > 0)
		{
			combat.getThePlayer().setHealItems(combat.getThePlayer().getHealItems() - 1);
			
			combat.getThePlayer().setHealth(combat.getThePlayer().getHealth() + 1);
		}
		
		return combat;
	}
	
	public static Enemy createEnemy(Player player)
	{
		int attack = player.getAttack() + rand.nextInt(3) - 1;
		int health = player.getHealth() + rand.nextInt(3) - 1;
		
		if(attack <= 0)
		{
			attack = 1;
		}
		
		if(health <= 0)
		{
			health = 1;
		}
		
		Enemy newEnemy = new Enemy("Random Name", attack, health);
		
		return newEnemy;
	}
}
