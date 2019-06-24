package abstshop2.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Purchase {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private Date date;
	
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Customer buyer;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    
	protected Purchase() {}
	
	public Purchase(Item item)
	{
		this.item = item;
		item.addPurchase(this);
		this.date = new Date(System.currentTimeMillis());
	}
	
	public long getId() { return id; }
	public Date getDate() { return date; }
	public Customer getBuyer() { return buyer; }
	public void setBuyer(Customer buyer) { this.buyer = buyer; }
	public Item getItem() { return item; }
}
