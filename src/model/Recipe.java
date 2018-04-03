package model;

import java.io.Serializable;

public class Recipe implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recipeName;
	private Type type;
	
	public Recipe(String recipeName, Type type){
		this.recipeName = recipeName;
		this.type = type;
	}
	
	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	
	public Type getType(){
		return this.type;
	}
	
	public void setType(Type type){
		this.type = type;
	}
}


