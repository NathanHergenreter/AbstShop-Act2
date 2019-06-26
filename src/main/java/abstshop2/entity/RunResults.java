package abstshop2.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class RunResults {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private int num;
	
	private Date date;
	
	private int totalSpent;
	
	private int totalPurchases;

    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "set")
	private List<Purchase> purchases = new ArrayList<Purchase>();

    private RunResults() {}
    
    public RunResults(int num) { this.num = num; this.totalSpent = 0; this.totalPurchases = 0; }
    
    public Long getId() { return id; }
    public int getNum() { return num; }
    public Date getDate() { return date; }
    public int getTotalSpent() { return totalSpent; }
    public int getTotalPurchases() { return totalPurchases; }
    public List<Purchase> getPurchases() { return purchases; }
    public void addPurchase(Purchase purchase) 
    { 
    	purchases.add(purchase); 
    	purchase.setSet(this); 
    	if(date == null) { date = purchase.getDate(); }
    	totalPurchases++;
    	totalSpent += purchase.getItem().getCost();
    }
    
}
