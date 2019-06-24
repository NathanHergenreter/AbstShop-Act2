package abstshop2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abstshop2.entity.Customer;
import abstshop2.entity.Item;
import abstshop2.entity.Purchase;
import abstshop2.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;
    
    public void add(Customer customer)
    {
    	if(!exists(customer.getName())) {
    		repo.save(customer);
    	}
    }
    
    public Customer get(String name) {
    	return repo.findByName(name);
    }
    
    public Customer get(long id) {
    	return repo.findById(id).get();
    }
    
    public void update(Customer customer) {
    	repo.save(customer);
    }
    
    public Purchase makePurchase(Customer customer, Item item) {
    	Purchase purchase = new Purchase(item);
		customer.addPurchase(purchase);
		customer.modCredits(-(item.getCost()));
		update(customer);
		
		return purchase;
    }
    
    public int pay(Customer customer)
    {
    	customer.modCredits(customer.getBaseCredits());
    	update(customer);
    	
    	return customer.getBaseCredits();
    }
    
    public boolean exists(String name) { return get(name) != null; }
    
    public boolean exists(Customer customer) { return exists(customer.getName()); }
    
    public boolean hasCustomers() { return repo.count() > 0; }
    
    public List<Customer> findAll() { return repo.findAll(); }
    
}
