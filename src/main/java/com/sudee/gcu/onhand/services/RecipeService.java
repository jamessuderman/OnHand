/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/23/2021
 */

package com.sudee.gcu.onhand.services;

import com.sudee.gcu.onhand.models.Recipe;
import com.sudee.gcu.onhand.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private List<Recipe> recipes = new ArrayList<>();

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes() {
        recipes.clear();
        recipes = (List<Recipe>)recipeRepository.findAll();
        return recipes;
    }

    public Recipe getById(int id) {
        return recipeRepository.findRecipeByRecipeId(id);
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }
}
