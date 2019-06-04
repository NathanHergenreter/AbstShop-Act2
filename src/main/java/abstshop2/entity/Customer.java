package abstshop2.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(nullable = false, unique = true)
    private String name;
    
    private int credits;
    
    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "buyer")
    private List<Item> purchases = new ArrayList<Item>();
 
    protected Customer() {}
    
    public Customer(String name)
    {
    	this.name = name;
    	this.credits = 0;
    }
    
    public Customer(String name, int credits)
    {
    	this.name = name;
    	this.credits = credits;
    }
    
    public String name() { return name; }
    public int credits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public void modCredits(int val) { credits += val; }
    public List<Item> purchases() { return purchases; }
    public Item purchase(int idx) { return purchases.get(idx); }
    public void addPurchase(Item purchase) { purchases.add(purchase); }
}
