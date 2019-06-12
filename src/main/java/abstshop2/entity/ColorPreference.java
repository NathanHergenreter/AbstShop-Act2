package abstshop2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ColorPreference {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    private String color;
    private int weight;
    
    protected ColorPreference() {}
    
    public ColorPreference(String color, int weight)
    {
    	this.color = color;
    	this.weight = weight;
    }
    
    public long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public String getColor() { return color; }
    public int getWeight() { return weight; }
}
