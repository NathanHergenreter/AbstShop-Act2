package abstshop2.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class BotAI {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(mappedBy = "ai")
	private Customer customer;

    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "ai")
    private List<ShapePreference> shapePreferences = new ArrayList<ShapePreference>();

    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "ai")
    private List<ColorPreference> colorPreferences = new ArrayList<ColorPreference>();

    //Positive - represents how many sets of items can be shown before bot "loses interest"
    private int patience;
    //Negative - represents weight in determining when bot no longer feels like spending
    private int frugality;
    
    protected BotAI() {}
    
    public BotAI(int patience, int frugality)
    {
    	this.patience = patience;
    	this.frugality = frugality;
    }

    public ShapePreference getShapePreference(String shape) 
    { 
    	for(ShapePreference pref : shapePreferences)
    	{
    		if(pref.getShape().compareTo(shape) == 0)
    			return pref;
    	}
    	
    	return null;
    }

    public ColorPreference getColorPreference(String color) 
    { 
    	for(ColorPreference pref : colorPreferences)
    	{
    		if(pref.getColor().compareTo(color) == 0)
    			return pref;
    	}
    	
    	return null;
    }
    
    public int getShapeWeight(String shape)
    {
    	ShapePreference pref = getShapePreference(shape);
    	return pref != null ? pref.getWeight() : 0;
    }
    
    public int getColorWeight(String color)
    {
    	ColorPreference pref = getColorPreference(color);
    	return pref != null ? pref.getWeight() : 0;
    }
    
    public ShapePreference getShapePreference(int idx) { return shapePreferences.get(idx); }
    public List<ShapePreference> getShapePreferences() { return shapePreferences; }
    public void addShapePreference(ShapePreference pref) { shapePreferences.add(pref); pref.setAI(this); }
    public ColorPreference getColorPreference(int idx) { return colorPreferences.get(idx); }
    public List<ColorPreference> getColorPreferences() { return colorPreferences; }
    public void addColorPreference(ColorPreference pref) { colorPreferences.add(pref); pref.setAI(this); }
    public int getPatience() { return patience; }
    public int getFrugality()  { return frugality; }
}
