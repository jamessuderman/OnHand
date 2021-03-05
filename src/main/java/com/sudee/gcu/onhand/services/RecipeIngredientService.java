/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/28/2021
 */

package com.sudee.gcu.onhand.services;

import com.sudee.gcu.onhand.models.RecipeIngredient;
import com.sudee.gcu.onhand.repositories.RecipeIngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RecipeIngredientService {
    Logger recipeIngredientLogger = LoggerFactory.getLogger(RecipeIngredientService.class);

    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public RecipeIngredient getByIngredientId(int id) {
        recipeIngredientLogger.info("RecipeIngredientService --- getByIngredientId --- " + new Date().toString());
        return recipeIngredientRepository.getRecipeIngredientByIngredientIngredientId(id);
    }

    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        recipeIngredientLogger.info("RecipeIngredientService --- save --- " + new Date().toString());
        return recipeIngredientRepository.save(recipeIngredient);
    }
}
