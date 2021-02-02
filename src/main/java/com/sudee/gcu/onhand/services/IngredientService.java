/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/28/2021
 */

package com.sudee.gcu.onhand.services;

import com.sudee.gcu.onhand.models.Ingredient;
import com.sudee.gcu.onhand.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    public final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient getByName(String name) {
        return ingredientRepository.findIngredientByName(name);
    }

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

}
