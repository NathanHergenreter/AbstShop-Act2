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
    
    private int patience;
    
    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "buyer")
    private List<Item> purchases = new ArrayList<Item>();

    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "customer")
    private List<ShapePreference> shapePreferences = new ArrayList<ShapePreference>();

    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "customer")
    private List<ColorPreference> colorPreferences = new ArrayList<ColorPreference>();
    
    protected Customer() {}
    
    public Customer(String name)
    {
    	this.name = name;
    	this.credits = 0;
    	this.patience = 0;
    }
    
    public Customer(String name, int credits)
    {
    	this.name = name;
    	this.credits = credits;
    	this.patience = 0;
    }
    
    public Customer(String name, int credits, int patience)
    {
    	this(name, credits);
    	this.patience = patience;
    }
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public void modCredits(int val) { credits += val; }
    public List<Item> getPurchases() { return purchases; }
    public Item getPurchase(int idx) { return purchases.get(idx); }
    public void addPurchase(Item purchase) { purchases.add(purchase); }
    public ShapePreference getShapePreference(int idx) { return shapePreferences.get(idx); }
    public List<ShapePreference> getShapePreferences() { return shapePreferences; }
    public void addShapePreference(ShapePreference pref) { shapePreferences.add(pref); pref.setCustomer(this); }
    public ColorPreference getColorPreference(int idx) { return colorPreferences.get(idx); }
    public List<ColorPreference> getColorPreferences() { return colorPreferences; }
    public void addColorPreference(ColorPreference pref) { colorPreferences.add(pref); pref.setCustomer(this); }
    
    @Override
    public String toString()
    {
    	return "id: "+id+" name: "+name+" credits: "+credits;
    }
}
