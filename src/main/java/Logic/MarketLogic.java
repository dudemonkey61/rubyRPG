package Logic;

import dto.MarketObject;

public class MarketLogic 
{
	public static MarketObject buyPotion(MarketObject market)
	{
		market.getPlayer().setHealItems(market.getPlayer().getHealItems() + 1);
		
		market.getPlayer().setMoney(market.getPlayer().getMoney() - 1);
		
		return market;
	}
	
	public static MarketObject buyAttack(MarketObject market)
	{
		market.getPlayer().setMaxHealth(market.getPlayer().getMaxHealth() + 1);
		
		market.getPlayer().setCurrentHealth(market.getPlayer().getMaxHealth());
		
		market.getPlayer().setMoney(market.getPlayer().getMoney() - 1);
		
		return market;
	}
	
	public static MarketObject buyHealth(MarketObject market)
	{
		market.getPlayer().setAttack(market.getPlayer().getAttack() + 1);
		
		market.getPlayer().setMoney(market.getPlayer().getMoney() - 1);
		
		return market;
	}
}
