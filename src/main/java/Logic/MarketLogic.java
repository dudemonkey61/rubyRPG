package Logic;

import dto.MarketObject;

public class MarketLogic 
{
	public MarketObject buyPotion(MarketObject market)
	{
		market.getPlayer().setHealItems(market.getPlayer().getHealItems() + 1);
		
		market.getPlayer().setMoney(market.getPlayer().getMoney() - 1);
		
		return market;
	}
	
	public MarketObject buyAttack(MarketObject market)
	{
		market.getPlayer().setHealth(market.getPlayer().getHealth() + 1);
		
		market.getPlayer().setMoney(market.getPlayer().getMoney() - 1);
		
		return market;
	}
	
	public MarketObject buyHealth(MarketObject market)
	{
		market.getPlayer().setAttack(market.getPlayer().getAttack() + 1);
		
		market.getPlayer().setMoney(market.getPlayer().getMoney() - 1);
		
		return market;
	}
}
