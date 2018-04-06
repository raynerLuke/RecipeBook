package controller;


import java.util.ArrayList;
import Perssistor.FilePersistor;
import model.Recipe;
import model.RecipesList;

public class RecipeController {
    
    private RecipesList recipesList;
	private static RecipeController instance;
	private FilePersistor fp = new FilePersistor();
	
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
		this.recipesList.add(recipe);
	}
	
	public void removeRecipe(Recipe recipe){
		this.recipesList.remove(recipe);
	}
	
	public ArrayList<Recipe> getAllRecipes(){
		return recipesList.getAllRecipes();
	}
	
	public void updateRecipe(Recipe recipeOld, Recipe recipeNew) {
		this.recipesList.update(recipeOld, recipeNew);	
	}
	
	public void SaveToFile(){
		fp.save(this.recipesList);
	}
	
	public void LoadFromFile() throws ClassNotFoundException{
		this.recipesList = fp.load();
	}
}
