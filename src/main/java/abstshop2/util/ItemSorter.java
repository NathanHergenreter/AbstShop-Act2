package abstshop2.util;

import java.util.ArrayList;

import abstshop2.entity.BotAI;
import abstshop2.entity.ColorPreference;
import abstshop2.entity.Item;
import abstshop2.entity.ShapePreference;

public class ItemSorter {

	public ItemSorter() {}

	public ArrayList<Item> sortItems(ArrayList<Item> options, BotAI ai)
	{
		Item[] list = (Item[]) options.toArray();
		
		mergeSort(list, 0, options.size()-1, ai);
		ArrayList<Item> ret = new ArrayList<Item>();
		for(Item item : list) { ret.add(item); }
		
		return ret;
	}

	private void mergeSort(Item arr[], int l, int r, BotAI ai) 
	{ 
	    if (l < r) 
	    { 
	        // Same as (l+r)/2, but avoids overflow for 
	        // large l and h 
	        int m = l+(r-l)/2; 
	  
	        // Sort first and second halves 
	        mergeSort(arr, l, m, ai); 
	        mergeSort(arr, m+1, r, ai); 
	  
	        merge(arr, l, m, r, ai); 
	    } 
	} 
	
	private void merge(Item arr[], int l, int m, int r, BotAI ai) 
	{ 
	    int i, j, k; 
	    int n1 = m - l + 1; 
	    int n2 =  r - m; 
	  
	    Item[] L = new Item[n1];
	    for (i = 0; i < n1; i++) { L[i] = arr[l + i]; }
	    
	    Item[] R = new Item[n2]; 
	    for (j = 0; j < n2; j++) { R[j] = arr[m + 1+ j]; }
	  
	    i = 0; j = 0; k = l;
	    
	    while (i < n1 && j < n2) 
	    { 
	        if (compare(L[i], R[j], ai) >= 0) 
	        { 
	            arr[k] = L[i]; 
	            i++; 
	        } 
	        else
	        { 
	            arr[k] = R[j]; 
	            j++; 
	        } 
	        k++; 
	    } 
	  
	    while (i < n1) 
	    { 
	        arr[k] = L[i]; 
	        i++; 
	        k++; 
	    } 
	  
	    while (j < n2) 
	    { 
	        arr[k] = R[j]; 
	        j++; 
	        k++; 
	    } 
	} 
	
	private int compare(Item arg0, Item arg1, BotAI ai) {
		ShapePreference shape0 = ai.getShapePreference(arg0.getShape());
		ColorPreference color0 = ai.getColorPreference(arg0.getColor());
		ShapePreference shape1 = ai.getShapePreference(arg1.getShape());
		ColorPreference color1 = ai.getColorPreference(arg1.getColor());
		
		int val0 = shape0 != null ? shape0.getWeight() : 0;
		val0 += color0 != null ? color0.getWeight() : 0;
		int val1 = shape1 != null ? shape1.getWeight() : 0;
		val1 += color1 != null ? color1.getWeight() : 0;
		
		return val0 - val1;
	}
}
