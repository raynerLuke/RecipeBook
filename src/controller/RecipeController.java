package controller;

import java.util.ArrayList;
import model.Recipe;
import model.RecipesList;

public class RecipeController {
    
	private static RecipeController instance;
	private RecipesList recipesList;
	
	private RecipeController(){
		this.recipesList = new RecipesList();
	}
	
	public static RecipeController getInstance(){
		if(instance == null){
			instance = new RecipeController();
		}
		return instance;
	}
	
	public void addRecipe(Recipe recipe){
		recipesList.addRecipe(recipe);
	}
	
	public void removeRecipe(Recipe recipe){
		recipesList.deleteRecipe(recipe);
	}
	
	public ArrayList<Recipe> getAllRecipes(){
		return recipesList.getAllRecipes();
	}
}