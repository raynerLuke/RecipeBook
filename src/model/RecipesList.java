package model;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipesList implements Serializable {

	ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	public void add(Recipe recipe) {
		this.recipes.add(recipe);
	}
	
	public void remove(Recipe recipe) {
		this.recipes.remove(recipe);
	}
	
	public ArrayList<Recipe> getAllRecipes(){
	    return this.recipes;
	}
	
	public void update(Recipe recipeOld, Recipe recipeNew) {
		recipes.remove(recipeOld);
		recipes.add(recipeNew);
	}
	
}

