/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/23/2021
 */

package com.sudee.gcu.onhand.services;

import com.sudee.gcu.onhand.models.Recipe;
import com.sudee.gcu.onhand.repositories.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecipeService {
    Logger recipeServiceLogger = LoggerFactory.getLogger(RecipeService.class);

    private final RecipeRepository recipeRepository;
    private List<Recipe> recipes = new ArrayList<>();

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes() {
        recipeServiceLogger.info("RecipeService --- getRecipes --- " + new Date().toString());
        recipes.clear();
        recipes = (List<Recipe>)recipeRepository.findAll();
        return recipes;
    }

    public Recipe getById(int id) {
        recipeServiceLogger.info("RecipeService --- getById --- " + new Date().toString());
        return recipeRepository.findRecipeByRecipeId(id);
    }

    public void save(Recipe recipe) {
        recipeServiceLogger.info("RecipeService --- save --- " + new Date().toString());
        recipeRepository.save(recipe);
    }

    public void delete(Recipe recipe) {
        recipeServiceLogger.info("RecipeService --- delete --- " + new Date().toString());
        recipeRepository.delete(recipe);
    }
}
