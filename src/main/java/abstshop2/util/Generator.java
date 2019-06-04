package abstshop2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import abstshop2.entity.Customer;
import abstshop2.entity.Item;

public class Generator {
	
	private Random rng;
	private List<String> shapes;
	private List<String> colors;
	
	public Generator() 
	{
		rng = new Random();
		shapes = setShapes();
		colors = setColors();
	}
	
	public List<Customer> generateCustomers(int numCustomers, int minCredits, int maxCredits)
	{
		List<Customer> ret = new ArrayList<Customer>();
		
		for(int i = 0; i < numCustomers; i++)
		{
			String name = "";
			for(int j = 0; j < 4; j++) { name += (char) (rng.nextInt(26) + 97); }
			name += formattedInt();
			int credits = rng.nextInt(maxCredits-minCredits) + minCredits;
			ret.add(new Customer(name, credits));
		}
		
		return ret;
	}
	
	public List<Item> generateItems(int numItems, int minCost, int maxCost)
	{
		List<Item> ret = new ArrayList<Item>();
		
		for(int i = 0; i < numItems; i++)
		{
			int cost = rng.nextInt(maxCost-minCost) + minCost;
			String shape = shapes.get(rng.nextInt(shapes.size()));
			String color = colors.get(rng.nextInt(colors.size()));
			ret.add(new Item(cost, shape, color));
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
	
	private List<String> setShapes()
	{
		List<String> ret = new ArrayList<String>();
		
		ret.add("Sphere"); ret.add("Box"); ret.add("Pyramid"); ret.add("Cylinder"); ret.add("Cone");
		
		return ret;
	}
	
	private List<String> setColors() 
	{
		List<String> ret = new ArrayList<String>();
		
		ret.add("Blue"); ret.add("Red"); ret.add("Yellow"); ret.add("Green"); ret.add("Orange");
		ret.add("Purple"); ret.add("White"); ret.add("Grey"); ret.add("Black");
		
		return ret;
	}
}
