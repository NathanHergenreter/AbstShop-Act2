package abstshop2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ShapePreference {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ai_id")
    private BotAI ai;
    
    private String shape;
    private int weight;
    
    protected ShapePreference() {}
    
    public ShapePreference(String shape, int weight)
    {
    	this.shape = shape;
    	this.weight = weight;
    }
    
    public long getId() { return id; }
    public BotAI getAI() { return ai; }
    public void setAI(BotAI ai) { this.ai = ai; }
    public String getShape() { return shape; }
    public int getWeight() { return weight; }
}
