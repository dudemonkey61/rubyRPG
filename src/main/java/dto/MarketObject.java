package dto;

import Models.Player;

public class MarketObject 
{
	private Player player;
	
	public MarketObject(){}
	
	public MarketObject(Player player)
	{
		setPlayer(player);
	}
	
	public Player getPlayer() 
	{
		return player;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
}
