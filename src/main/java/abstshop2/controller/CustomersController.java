package abstshop2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import abstshop2.entity.Customer;
import abstshop2.service.CustomerService;

@Controller
@RequestMapping(value="/customers")
public class CustomersController {

	@Autowired
	CustomerService service;
	
    @GetMapping("/all")
    @ResponseBody
    public List<Customer> all() 
    {
    	return service.findAll();
    }
}
