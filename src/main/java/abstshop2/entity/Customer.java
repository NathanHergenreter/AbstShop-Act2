package abstshop2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(nullable = false, unique = true)
    private String name;
 
    protected Customer() {}
    
    public Customer(String name)
    {
    	this.name = name;
    }
    
    public String name() { return name; }
    
}
