package abstshop2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private int cost;
	private String shape;
	private String color;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Customer buyer;
	
	protected Item() {}
	
	public Item(int cost, String shape, String color) {
		this.cost = cost;
		this.shape = shape;
		this.color = color;
		this.buyer = null;
	}
	
	public Long id() { return id; }
	public int cost() { return cost; }
	public String shape() { return shape; }
	public String color() { return color; }
	public Customer buyer() { return buyer; }
	public void setBuyer(Customer customer) { this.buyer = buyer; }
}
