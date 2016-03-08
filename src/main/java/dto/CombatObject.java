package dto;

import Models.Enemy;
import Models.Player;

public class CombatObject 
{
	private Player thePlayer;
	private Enemy theEnemy;
	
	public CombatObject(Player thePlayer, Enemy theEnemy)
	{
		this.setThePlayer(thePlayer);
		this.setTheEnemy(theEnemy);
	}
	
	public CombatObject(){};

	public Player getThePlayer() {
		return thePlayer;
	}

	public void setThePlayer(Player thePlayer) {
		this.thePlayer = thePlayer;
	}

	public Enemy getTheEnemy() {
		return theEnemy;
	}

	public void setTheEnemy(Enemy theEnemy) {
		this.theEnemy = theEnemy;
	}
}
