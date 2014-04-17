package com.namoo.cookbook.domain;

public class Recipe {
	//
	private String name;
	private String ingredients;
	private String procedure;
	
	private ImageFile recipeImage;
	
	//--------------------------------------------------------------------------
	// constructor
	
	public Recipe() {
		//
	}
	
	public Recipe(String name, String ingredients, String procedure) {
		//
		this.name = name;
		this.ingredients = ingredients;
		this.procedure = procedure;
	}

	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public ImageFile getRecipeImage() {
		return recipeImage;
	}

	public void setRecipeImage(ImageFile recipeImage) {
		this.recipeImage = recipeImage;
	}
	
	

}
