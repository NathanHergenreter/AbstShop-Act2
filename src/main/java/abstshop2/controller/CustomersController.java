package abstshop2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import abstshop2.entity.Customer;
import abstshop2.service.CustomerService;

@Controller
@RequestMapping(value="/customers")
public class CustomersController {

	@Autowired
	CustomerService service;

	@GetMapping("/display")
	public String displayAll(Model model) {
		model.addAttribute("customers", all());
		return "customers-display";
	}
	
    @GetMapping("/all")
    @ResponseBody
    public List<Customer> all() 
    {
    	return service.findAll();
    }
    
    @GetMapping("/customer/{id}")
    public String displayCustomer( @PathVariable("id") long id, Model model) {
    	Customer customer = service.get(id);
    	model.addAttribute("customer", customer);
    	model.addAttribute("purchases", customer.getPurchases());
    	return "customer-page";
    }
}
