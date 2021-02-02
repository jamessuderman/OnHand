/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/28/2021
 */

package com.sudee.gcu.onhand.repositories;

import com.sudee.gcu.onhand.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    Ingredient findIngredientByName(String name);
}
