/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/28/2021
 */

package com.sudee.gcu.onhand.repositories;

import com.sudee.gcu.onhand.models.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {
    RecipeIngredient getRecipeIngredientByIngredientIngredientId(int id);
}
