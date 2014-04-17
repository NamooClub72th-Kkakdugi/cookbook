package com.namoo.cookbook.dao;

import java.util.List;

import com.namoo.cookbook.domain.Recipe;

/**
 * RecipeDao 인터페이스
 * @author kosta-18
 *
 */
public interface RecipeDao {
	//
	List<Recipe> readAllRecipes();
	Recipe readRecipe(String recipeName);
	void insertRecipe(Recipe recipe);
	void updateRecipe(Recipe recipe);
	void deleteRecipe(String recipeName);
}
