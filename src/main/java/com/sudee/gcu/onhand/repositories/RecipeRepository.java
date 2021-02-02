/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/24/2021
 */

package com.sudee.gcu.onhand.repositories;

import com.sudee.gcu.onhand.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    Recipe findRecipeByRecipeId(int id);
}