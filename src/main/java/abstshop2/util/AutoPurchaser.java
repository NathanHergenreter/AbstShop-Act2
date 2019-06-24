package abstshop2.util;

import java.util.ArrayList;

import abstshop2.entity.BotAI;
import abstshop2.entity.Customer;
import abstshop2.entity.Item;

public class AutoPurchaser {

	private int prefWt = 8;
	
	public AutoPurchaser() { }
	
	//Pre-condition: Items are sorted based on determined likely purchases
	public ArrayList<Item> choosePurchases(Customer buyer, ArrayList<Item> options)
	{
		ArrayList<Item> ret = new ArrayList<Item>();

		BotAI ai = buyer.getAI();
		int credits = buyer.getCredits();
		int threshold = creditThreshold(buyer.getBaseCredits(), ai.getFrugality());
		
		int patience = ai.getPatience(); int cur = 0; int start;
		while(patience >= 0 && credits > threshold)
		{
			start = cur;
			
			while(cur - start < 25 && credits > threshold)
			{
				Item item = options.get(cur);
				int boundFrugality = boundFrugality(buyer.getBaseCredits(), ai.getFrugality());
				int boundPref = boundPreference(buyer.getBaseCredits(), ai.getColorWeight(item.getColor()), 
								ai.getShapeWeight(item.getShape()));
				
				if(item.getCost() <= credits
				&& worthwhile(item.getCost(), boundFrugality, boundPref))
				{
					ret.add(item);
					credits -= item.getCost();
				}
				
				cur++;
			}
			
			patience--;
		}
		
		return ret;
	}
	
	private boolean worthwhile(int cost, int boundFrugality, int boundPref)
	{
		if(boundFrugality >= boundPref
		&& boundFrugality >= cost
		&& boundPref >= cost) 
		{
			return true;
		}
		else { return false; }
	}
	
	private int creditThreshold(int baseCredits, int frugality)
	{
		switch(frugality)
		{
			case 0:
				return (baseCredits*25)/100;
			case 1:
				return (baseCredits*33)/100;
			case 2:
				return (baseCredits*45)/100;
			case 3:
				return (baseCredits*60)/100;
			default:
				return (baseCredits*75)/100;
		}
	}
	
	private int boundFrugality(int baseCredits, int frugality)
	{
		return baseCredits - (baseCredits*10*frugality)/100;
	}
	
	private int boundPreference(int baseCredits, int colorWt, int shapeWt)
	{
		int ret = baseCredits/4;
		ret += (baseCredits*(colorWt*prefWt))/100;
		ret += (baseCredits*(shapeWt*prefWt))/100;
		
		return ret;
	}
}
