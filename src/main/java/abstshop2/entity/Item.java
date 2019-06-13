package abstshop2.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Item {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private int cost;
	private String shape;
	private String color;

    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "item")
    private List<Purchase> purchases = new ArrayList<Purchase>();
	
	protected Item() {}
	
	public Item(int cost, String shape, String color) {
		this.cost = cost;
		this.shape = shape;
		this.color = color;
//		this.buyer = null;
	}
	
	public Long getId() { return id; }
	public int getCost() { return cost; }
	public String getShape() { return shape; }
	public String getColor() { return color; }
    public List<Purchase> getPurchases() { return purchases; }
    public Purchase getPurchase(int idx) { return purchases.get(idx); }
    public void addPurchase(Purchase purchase) { purchases.add(purchase); }

//  @ManyToOne
//  @JoinColumn(name = "buyer_id")
//  private Customer buyer;

//	public Customer getBuyer() { return buyer; }
//	public void setBuyer(Customer customer) { this.buyer = buyer; }
}
