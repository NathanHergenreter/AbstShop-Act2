package abstshop2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import abstshop2.entity.Customer;
import abstshop2.service.CustomerService;
import abstshop2.util.BotGenerator;

@Component
public class BotGenCLR implements CommandLineRunner{

	private int numBots = 50;
	
	@Autowired
	private CustomerService service;

	private static final Logger log = LoggerFactory.getLogger(AbstShopAct2Application.class);
	private BotGenerator botGen;
	
	@Override
	public void run(String... args) throws Exception {
		botGen = new BotGenerator();
		List<Customer> bots = botGen.generateCustomers(numBots);
		
		for(Customer bot : bots) { service.add(bot); }
	}

}
