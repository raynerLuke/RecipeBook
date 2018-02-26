package model;

import java.util.ArrayList;

public class RecipesList {

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
