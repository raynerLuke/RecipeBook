package model;

import java.util.ArrayList;

public class RecipeInput extends Recipe {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private String method;
	private String ingredents;
	
	public RecipeInput(String recipeName, Type type, String description, String method, String ingredents){
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
	
	public String getIngredents() {
		return ingredents;
	}
	
	public void setIngredents(String ingredents) {
		this.ingredents = ingredents;
	}
    
}
