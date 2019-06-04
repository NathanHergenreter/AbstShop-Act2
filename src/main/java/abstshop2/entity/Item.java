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
	
	public Long getId() { return id; }
	public int getCost() { return cost; }
	public String getShape() { return shape; }
	public String getColor() { return color; }
	public Customer getBuyer() { return buyer; }
	public void setBuyer(Customer customer) { this.buyer = buyer; }
	
	@Override
	public String toString()
	{
		String buyerStr = buyer == null ? "None" : buyer.getName();
		return "id: "+id+" cost: "+cost+" shape: "+shape+" color: "+color+" buyer: "+buyerStr;
	}
}
