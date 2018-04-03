package model;

public class RecipeURL extends Recipe {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
 
	public RecipeURL(String recipeName, Type type, String url){
		super(recipeName, type);
		this.url = url;
	}

	public String getFileLocation() {
		return this.url;
	}

	public void setURL(String url) {
		this.url = url;
	}
	

}
