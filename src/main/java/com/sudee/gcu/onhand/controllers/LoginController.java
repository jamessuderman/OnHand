/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/23/2021
 */

package com.sudee.gcu.onhand.controllers;

import com.sudee.gcu.onhand.models.User;
import com.sudee.gcu.onhand.services.LoginService;
import com.sudee.gcu.onhand.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("name")
public class LoginController {
    private final LoginService loginService;
    private final RecipeService recipeService;

    public LoginController(LoginService loginService, RecipeService recipeService) {
        this.recipeService = recipeService;
        this.loginService = loginService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelMap model){
        ModelAndView loginMav = new ModelAndView();
        loginMav.setViewName("login");
        return loginMav;
    }

    @RequestMapping(value="/app", method = RequestMethod.POST)
    public ModelAndView navigateToApp(ModelMap model, @ModelAttribute User user){
        ModelAndView appMav = new ModelAndView();

        if(loginService.validate(user.getUsername(), user.getPassword())) {
            appMav.addObject("name", user.getUsername());
            appMav.addObject("user", user);
            appMav.addObject("recipes", recipeService.getRecipes());
            appMav.setViewName("app");
        } else {
            appMav.addObject("invalidCreds", true);
            appMav.setViewName("login");
        }

        return appMav;
    }
}
