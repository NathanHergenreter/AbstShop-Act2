package abstshop2.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    
    @Cascade(CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private BotAI ai;
    
//    @Cascade({CascadeType.ALL})
//    @OneToMany(mappedBy = "buyer")
//    private List<Item> items = new ArrayList<Item>();

    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "buyer")
    private List<Purchase> purchases = new ArrayList<Purchase>();
    
    protected Customer() {}
    
    public Customer(String name) {
    	this.name = name;
    	this.credits = 0;
    }
    
    public Customer(String name, int credits) {
    	this.name = name;
    	this.credits = credits;
    }
    
    public Customer(String name, int credits, BotAI ai) {
    	this(name, credits);
    	this.ai = ai;
    }
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public void modCredits(int val) { credits += val; }
//    public List<Item> getItem() { return items; }
//    public Item getItem(int idx) { return items.get(idx); }
//    public void addItem(Item item) { items.add(item); }
    public List<Purchase> getPurchases() { return purchases; }
    public Purchase getPurchase(int idx) { return purchases.get(idx); }
    public void addPurchase(Purchase purchase) { purchases.add(purchase); purchase.setBuyer(this); }
    public BotAI getAI() { return ai; }
    public void setAI(BotAI ai) { this.ai = ai; }
    
}
