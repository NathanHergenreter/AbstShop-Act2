package abstshop2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import abstshop2.entity.Customer;

public class BotGenerator {
	
	private Random rng;
	
	public BotGenerator() 
	{
		rng = new Random();
	}
	
	public List<Customer> generateCustomers(int numCustomers)
	{
		List<Customer> ret = new ArrayList<Customer>();
		
		for(int i = 0; i < numCustomers; i++)
		{
			String name = "";
			for(int j = 0; j < 4; j++) { name += (char) (rng.nextInt(26) + 97); }
			name += formattedInt();
			ret.add(new Customer(name));
		}
		
		return ret;
	}
	
	private String formattedInt()
	{
		int num = rng.nextInt(10000);
		if(num > 999) { return new Integer(num).toString(); }
		else if(num > 99) { return "0" + new Integer(num).toString(); }
		else if(num > 9) { return "00" + new Integer(num).toString(); }
		else { return "000" + new Integer(num).toString(); }
	}
}
