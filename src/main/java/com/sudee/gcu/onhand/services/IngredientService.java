/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/28/2021
 */

package com.sudee.gcu.onhand.services;

import com.sudee.gcu.onhand.models.Ingredient;
import com.sudee.gcu.onhand.repositories.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IngredientService {
    Logger ingredientServiceLogger = LoggerFactory.getLogger(IngredientService.class);

    public final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient getByName(String name) {
        ingredientServiceLogger.info("IngredientService --- getByName --- " + new Date().toString());
        return ingredientRepository.findIngredientByName(name);
    }

    public Ingredient save(Ingredient ingredient) {
        ingredientServiceLogger.info("IngredientService --- save --- " + new Date().toString());
        return ingredientRepository.save(ingredient);
    }

}
