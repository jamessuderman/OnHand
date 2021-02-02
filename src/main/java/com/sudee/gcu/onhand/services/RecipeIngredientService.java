/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/28/2021
 */

package com.sudee.gcu.onhand.services;

import com.sudee.gcu.onhand.models.RecipeIngredient;
import com.sudee.gcu.onhand.repositories.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public RecipeIngredient getByIngredientId(int id) {
        return recipeIngredientRepository.getRecipeIngredientByIngredientIngredientId(id);
    }

    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }
}
