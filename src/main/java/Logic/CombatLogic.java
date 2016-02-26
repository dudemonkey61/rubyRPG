package Logic;

import dto.CombatObject;

public class CombatLogic 
{
	public CombatObject playerAttack(CombatObject combat)
	{
		int attack = combat.getThePlayer().getAttack();
		
		int health = combat.getTheEnemy().getHealth();
		
		int damage = health - attack;
		
		combat.getTheEnemy().setHealth(damage);
		
		return combat;
	}
	
	public CombatObject enemyAttack(CombatObject combat)
	{
		int attack = combat.getTheEnemy().getAttack();
		
		int health = combat.getThePlayer().getHealth();
		
		int damage = health - attack;
		
		combat.getThePlayer().setHealth(damage);
		
		return combat;
	}
	
	public CombatObject healPlayer(CombatObject combat)
	{
		if(combat.getThePlayer().getHealItems() > 0)
		{
			combat.getThePlayer().setHealItems(combat.getThePlayer().getHealItems() - 1);
			
			combat.getThePlayer().setHealth(combat.getThePlayer().getHealth() + 1);
		}
		
		return combat;
	}
}
