package abstshop2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import abstshop2.entity.Customer;
import abstshop2.entity.Item;
import abstshop2.service.CustomerService;
import abstshop2.service.ItemService;
import abstshop2.util.Generator;

@Component
public class StartupGenCLR implements CommandLineRunner{

	private int numBots = 50;
	private int numItems = 500;
	private int minCost = 50;
	private int maxCost = 500;
	private int creditMult = 10;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ItemService itemService;

	private static final Logger log = LoggerFactory.getLogger(AbstShopAct2Application.class);
	private Generator gen;
	
	@Override
	public void run(String... args) throws Exception {
		gen = new Generator();
		
		if(!customerService.hasCustomers()) { generateBots(); }
		if(!itemService.hasItems()) { generateItems(); }
	}
	
	private void generateBots() {
		List<Customer> bots = gen.generateCustomers(numBots, minCost * creditMult, maxCost * creditMult);
		
		for(Customer bot : bots) { customerService.add(bot); }
	}
	
	private void generateItems() {
		List<Item> items = gen.generateItems(numItems, minCost, maxCost);
		
		for(Item item : items) { itemService.add(item); }
	}

}
