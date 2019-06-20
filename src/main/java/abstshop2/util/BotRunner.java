package abstshop2.util;

import java.util.ArrayList;
import java.util.Random;

import abstshop2.entity.Customer;
import abstshop2.entity.Item;

public class BotRunner {

	Random rng;
	AutoPurchaser purchaser;
	
	public BotRunner() {
		purchaser = new AutoPurchaser();
		rng = new Random();
	}
	
	public ArrayList<Item> purchaseRandom(Customer bot, ArrayList<Item> options)
	{
		ArrayList<Integer> idcs = new ArrayList<Integer>();
		for(int i = 0; i < options.size(); i++) { idcs.add(new Integer(i)); }
		
		ArrayList<Item> rand = new ArrayList<Item>();
		while(idcs.size() > 0) 
		{ 
			int idx = rng.nextInt(idcs.size());
			rand.add(options.get(idx));
			idcs.remove(idx);
		}
		return purchaser.choosePurchases(bot, rand);
	}
}
