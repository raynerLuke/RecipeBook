package model;

import java.util.ArrayList;

public class Recipe {
    
	private String name;
	private String description;
	private String method;
	private ArrayList<String> ingredents = new ArrayList<String>();
	
	public Recipe(String name, String description, String method, ArrayList<String> ingredents){
		this.name = name;
		this.description = description;
		this.method = method;
		this.ingredents = ingredents;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
