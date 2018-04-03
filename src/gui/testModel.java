package gui;

import java.util.ArrayList;

import controller.RecipeController;
import model.Recipe;
import model.RecipeInput;
import model.RecipeURL;
import model.Type;

public class testModel {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
        
		//ArrayList<String> r1Ingredents = new ArrayList<String>();
		//r1Ingredents.add("chicken");
		//r1Ingredents.add("cajen spice");
		//r1Ingredents.add("pepper");
		
		//Recipe r1 = new RecipeInput("cajen chicken", Type.INPUT, "sweet and spicy", "cook for 30 mins in oven", r1Ingredents);
		//Recipe r2 = new RecipeURL("steak pie", Type.URL, "http//:www.food.com");
		
		//RecipeController.getInstance().addRecipe(r1);
		//RecipeController.getInstance().addRecipe(r2);
		RecipeController.getInstance().LoadFromFile();
		//RecipeController.getInstance().LoadFromFile();
		
	    ArrayList<Recipe> allRecipes = RecipeController.getInstance().getAllRecipes();
		
		for(Recipe p : allRecipes){
			if(p.getType() == Type.INPUT){
				RecipeInput r = (RecipeInput) p;
				System.out.println(r.getDescription());
			}
			else{
				RecipeURL r = (RecipeURL) p;
				System.out.println(r.getRecipeName());
			}	
		}	
	}
}
