package controller;

import java.util.Comparator;

import model.Recipe;

public class Sort {
    
	private static int value = 0;
	
	public static final Comparator<Recipe> compareName = new Comparator<Recipe>(){
		public int compare(Recipe p1, Recipe p2){
			value = p1.getRecipeName().compareTo(p2.getRecipeName());
			return  value;
		}
	};
}
