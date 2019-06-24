package abstshop2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import abstshop2.entity.BotAI;
import abstshop2.entity.ColorPreference;
import abstshop2.entity.Customer;
import abstshop2.entity.Item;
import abstshop2.entity.ShapePreference;

public class Generator {
	
	private Random rng;
	private List<String> shapes;
	private List<String> colors;
	private int maxPatience = 5;
	private int maxFrugality = 5;
	private int minWeight = -2;
	private int maxWeight = 2;
	
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
			Customer next = new Customer(name, credits, genBotAI());
			
			ret.add(next);
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
	
	private BotAI genBotAI() 
	{
		BotAI ret = new BotAI(rng.nextInt(maxPatience), rng.nextInt(maxFrugality));

		List<ShapePreference> shapePreferences = genShapePreferences();
		for(ShapePreference pref : shapePreferences) { if(pref.getWeight()!=0 ) ret.addShapePreference(pref); }
		
		List<ColorPreference> colorPreferences = genColorPreferences();
		for(ColorPreference pref : colorPreferences) { if(pref.getWeight()!=0 ) ret.addColorPreference(pref); }
		
		return ret;
	}
	
	private List<ShapePreference> genShapePreferences() 
	{
		List<ShapePreference> ret = new ArrayList<ShapePreference>();
		
		List<Integer> remShapes = new ArrayList<Integer>();
		for(int i = 0; i < shapes.size(); i++) { remShapes.add(new Integer(i)); }
		
		int num = rng.nextInt(shapes.size());
		while(num > 0) {
			int idx = rng.nextInt(remShapes.size());
			ret.add(new ShapePreference(shapes.get(remShapes.get(idx)), rng.nextInt(maxWeight-minWeight+1)+minWeight));
			remShapes.remove(idx);
			num--;
		}
		
		return ret.size() > 1 ? orderedShapePreferences(ret) : ret;
	}

	private List<ColorPreference> genColorPreferences() 
	{
		List<ColorPreference> ret = new ArrayList<ColorPreference>();
		
		List<Integer> remColors = new ArrayList<Integer>();
		for(int i = 0; i < colors.size(); i++) { remColors.add(new Integer(i)); }
		
		int num = rng.nextInt(colors.size());
		while(num > 0) {
			int idx = rng.nextInt(remColors.size());
			ret.add(new ColorPreference(colors.get(remColors.get(idx)), rng.nextInt(maxWeight-minWeight+1)+minWeight));
			remColors.remove(idx);
			num--;
		}
		
		return ret.size() > 1 ? orderedColorPreferences(ret) : ret;
	}
	
	private List<ShapePreference> orderedShapePreferences(List<ShapePreference> unordered)
	{
		List<ShapePreference> ret = new ArrayList<ShapePreference>();
		
		int cur = 0; int max = 0;
		while(unordered.size() > 0)
		{
			if(cur == unordered.size())
			{
				ret.add(unordered.get(max));
				unordered.remove(max);
				cur = 0;
				max = 0;
			}
			else if(unordered.get(cur).getWeight() > unordered.get(max).getWeight())
			{ max = cur; }
			else { cur++; }
		}
		
		return ret;
	}

	//Bad code :(
	//Should've made a higher level Preference class
	//Like, REALLY should have :thinking:
	private List<ColorPreference> orderedColorPreferences(List<ColorPreference> unordered)
	{
		List<ColorPreference> ret = new ArrayList<ColorPreference>();
		
		int cur = 0; int max = 0;
		while(unordered.size() > 0)
		{
			if(cur == unordered.size())
			{
				ret.add(unordered.get(max));
				unordered.remove(max);
				cur = 0;
				max = 0;
			}
			else if(unordered.get(cur).getWeight() > unordered.get(max).getWeight())
			{ max = cur; }
			else { cur++; }
		}
		
		return ret;
	}
}
