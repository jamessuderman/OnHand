/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/23/2021
 */

package com.sudee.gcu.onhand.controllers;

import com.sudee.gcu.onhand.models.Ingredient;
import com.sudee.gcu.onhand.models.Recipe;
import com.sudee.gcu.onhand.models.RecipeIngredient;
import com.sudee.gcu.onhand.services.IngredientService;
import com.sudee.gcu.onhand.services.RecipeIngredientService;
import com.sudee.gcu.onhand.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("name")
public class RecipeController {
    Logger recipeLogger = LoggerFactory.getLogger(RecipeController.class);

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final RecipeIngredientService recipeIngredientService;

    public RecipeController(RecipeService recipeService,
                            IngredientService ingredientService,
                            RecipeIngredientService recipeIngredientService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.recipeIngredientService = recipeIngredientService;
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView showAddPage(ModelMap model){
        recipeLogger.info("RecipeController --- showAddPage --- " + new Date().toString());
        ModelAndView addMav = new ModelAndView();
        addMav.setViewName("add");
        return addMav;
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditPage(ModelMap model, @PathVariable int id){
        recipeLogger.info("RecipeController --- showEditPage --- " + new Date().toString());
        ModelAndView editMav = new ModelAndView();
        Recipe recipe = recipeService.getById(id);
        editMav.addObject("recipe", recipe);
        editMav.setViewName("edit");
        return editMav;
    }

    @RequestMapping(value="/app", method = RequestMethod.GET)
    public ModelAndView showApp(ModelMap model){
        recipeLogger.info("RecipeController --- showApp --- " + new Date().toString());
        ModelAndView appMav = new ModelAndView();
        appMav.addObject("recipes", recipeService.getRecipes());
        appMav.setViewName("app");
        return appMav;
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam(name = "recipeId") int recipeId,
                             @RequestParam(name = "ingredients") List<String> ingredients,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "category") String category,
                             @RequestParam(name = "instructions") String instructions) {

        Recipe recipe = recipeService.getById(recipeId);
        recipe.setName(name);
        recipe.setCategory(category);
        recipe.setInstructions(instructions);

        recipe.getIngredients().clear();
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        for(String i: ingredients) {
            String[] parts = i.split(" ");

            StringBuilder ingredientName = new StringBuilder();
            for(int j = 2; j < parts.length; j++) {
                ingredientName.append(parts[j]);
                if(j < parts.length - 1) {
                    ingredientName.append(" ");
                }
            }

            Ingredient ingredient = ingredientService.getByName(ingredientName.toString());
            RecipeIngredient recipeIngredient = recipeIngredientService.getByIngredientId(ingredient.getIngredientId());
            recipeIngredient.setAmount(Integer.parseInt(parts[0]));
            recipeIngredient.setIngredient(ingredient);
            recipeIngredients.add(recipeIngredient);
        }

        recipe.setIngredients(recipeIngredients);
        recipeService.save(recipe);

        recipeLogger.info("RecipeController --- save --- " + new Date().toString());

        ModelAndView appMav = new ModelAndView();
        appMav.addObject("recipes", recipeService.getRecipes());
        appMav.setViewName("app");
        return appMav;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam(name = "ingredients", required = false) List<String> ingredients,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "category") String category,
                               @RequestParam(name = "instructions") String instructions) {

        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setCategory(category);
        recipe.setInstructions(instructions);
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();

        if(ingredients != null) {
            for(String i: ingredients) {
                String[] parts = i.split(" ");

                StringBuilder ingredientName = new StringBuilder();
                for(int j = 2; j < parts.length; j++) {
                    ingredientName.append(parts[j]);
                    if(j < parts.length - 1) {
                        ingredientName.append(" ");
                    }
                }
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientName.toString());
                ingredient.setUnit(parts[1]);
                RecipeIngredient recipeIngredient = new RecipeIngredient();
                recipeIngredient.setAmount(Integer.parseInt(parts[0]));
                recipeIngredient.setIngredient(ingredientService.save(ingredient));
                recipeIngredients.add(recipeIngredientService.save(recipeIngredient));
            }
        }

        recipe.setIngredients(recipeIngredients);
        recipeService.save(recipe);

        recipeLogger.info("RecipeController --- create --- " + new Date().toString());

        ModelAndView appMav = new ModelAndView();
        appMav.addObject("recipes", recipeService.getRecipes());
        appMav.setViewName("app");
        return appMav;
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("recipeId") int recipeId) {
        Recipe recipe = recipeService.getById(recipeId);

        recipeLogger.info("RecipeController --- delete --- " + new Date().toString());

        recipeService.delete(recipe);

        ModelAndView appMav = new ModelAndView();
        appMav.addObject("recipes", recipeService.getRecipes());
        appMav.setViewName("app");
        return appMav;
    }
}
