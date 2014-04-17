package com.namoo.cookbook.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoo.cookbook.dao.RecipeDao;
import com.namoo.cookbook.domain.Cookbook;
import com.namoo.cookbook.domain.Recipe;
import com.namoo.cookbook.service.facade.CookbookService;

@Service
public class CookbookServiceLogic implements CookbookService {
	//
	@Autowired
	private RecipeDao recipeDao;
	
	@Override
	public Cookbook getCookbook() {
		// 
//		Cookbook cookbook = new Cookbook("Namoosori cookbook", "Namoosori");
//		cookbook.addRecipe(new Recipe("김치찌개", "김치, 돼지고기", "1. 김치 넣고 끓인다."));
//		cookbook.addRecipe(new Recipe("된장찌개", "된장, 돼지고기", "1. 된장 넣고 끓인다."));
		Cookbook cookbook = new Cookbook("Namoosori cookbook", "Namoosori");
		cookbook.setRecipes(recipeDao.readAllRecipes());
		
		return cookbook;
	}

	@Override
	public void registerRecipe(Recipe recipe) {
		//
		recipeDao.insertRecipe(recipe);

	}

	@Override
	public void modifyRecipe(Recipe recipe) {
		//
		recipeDao.updateRecipe(recipe);
	}

	@Override
	public void removeRecipe(String recipeName) {
		//
		recipeDao.deleteRecipe(recipeName);
	}

	@Override
	public Recipe getRecipe(String recipeName) {
		//
		return recipeDao.readRecipe(recipeName);
	}

}
