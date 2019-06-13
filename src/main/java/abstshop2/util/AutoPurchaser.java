package abstshop2.util;

import java.util.ArrayList;

import abstshop2.entity.BotAI;
import abstshop2.entity.Customer;
import abstshop2.entity.Item;

public class AutoPurchaser {

	public AutoPurchaser() 
	{ }
	
	public ArrayList<Item> choosePurchases(Customer buyer, ArrayList<Item> options)
	{
		ArrayList<Item> ret = new ArrayList<Item>();
		BotAI ai = buyer.getAI();
		int baseCredits = buyer.getBaseCredits();
		int credits = buyer.getCredits();
		
		return ret;
	}
}
