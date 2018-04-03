package model;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipesList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	public ArrayList<Recipe> getAllRecipes() {
		return recipes;
	}

	public void addRecipe(Recipe recipe) {
		this.recipes.add(recipe);
	}
	
	public void deleteRecipe(Recipe recipe){
		this.recipes.remove(recipe);
	}
}
