package abstshop2.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import abstshop2.entity.Customer;
import abstshop2.entity.Item;
import abstshop2.entity.Purchase;
import abstshop2.service.CustomerService;
import abstshop2.service.ItemService;
import abstshop2.util.BotRunner;

@Controller
@RequestMapping(value="/bots")
public class BotController {

	@Autowired
	CustomerService botService;
	@Autowired
	ItemService itemService;
	BotRunner runner = new BotRunner();
	
	@GetMapping("all/run/random")
	public String allRunRandomTest(Model model)
	{
		model.addAttribute("type", "Random");
		model.addAttribute("results", allRunRandom());
		
		return "bot-run";
	}
	
    @PostMapping("/all/run/random")
    @ResponseStatus(value = HttpStatus.OK)
    public runResults allRunRandom() 
    {
    	int total = 0;
    	List<Customer> bots = botService.findAll();
    	List<Purchase> purchases = new ArrayList<Purchase>();
    	
    	for(Customer bot : bots)
    	{
    		ArrayList<Item> options = new ArrayList<Item>(itemService.findAvailable());
    		ArrayList<Item> choices = runner.purchaseRandom(bot, options);
        	for(Item item : choices) { purchases.add(botService.makePurchase(bot, item)); total += item.getCost(); }
    	}
    	
    	return new runResults(total, purchases);
    }

	@GetMapping("all/pay")
	public String allPayTest(Model model)
	{
		allPay();
		return "home";
	}
	
    @PostMapping("all/pay")
    @ResponseStatus(value = HttpStatus.OK)
    public void allPay()
    {
    	List<Customer> bots = botService.findAll();
    	for(Customer bot : bots) { botService.pay(bot); }
    }
    
    private class runResults
    {
    	private int totalSpent;
    	private int totalPurchases;
    	private List<Purchase> purchases;
    	
    	private runResults(int totalSpent, List<Purchase> purchases)
    	{
    		this.totalSpent = totalSpent;
    		this.totalPurchases = purchases.size();
    		this.purchases = purchases;
    	}
    	
    	public int getTotalSpent() { return totalSpent; }
    	public int getTotalPurchases() { return totalPurchases; }
    	public List<Purchase> getPurchases() { return purchases; }
    }
}
