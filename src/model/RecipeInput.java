package model;

import java.util.ArrayList;

public class RecipeInput extends Recipe {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private String method;
	private ArrayList<String> ingredents = new ArrayList<String>();
	
	public RecipeInput(String recipeName, Type type, String description, String method, ArrayList<String> ingredents){
		super(recipeName, type);
		this.description = description;
		this.method = method;
		this.ingredents = ingredents;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public ArrayList<String> getIngredents() {
		return ingredents;
	}
	
	public void setIngredents(ArrayList<String> ingredents) {
		this.ingredents = ingredents;
	}
    
}
