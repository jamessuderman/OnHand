/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 01/23/2021
 */

package com.sudee.gcu.onhand.controllers;

import com.sudee.gcu.onhand.models.User;
import com.sudee.gcu.onhand.services.LoginService;
import com.sudee.gcu.onhand.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

// So that testing can happen
// CI/CD pipeline

@Controller
@SessionAttributes("name")
public class LoginController {
    Logger loginLogger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;
    private final RecipeService recipeService;

    public LoginController(LoginService loginService, RecipeService recipeService) {
        this.recipeService = recipeService;
        this.loginService = loginService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelMap model){
        loginLogger.info("Login Controller --- showLoginPage --- " + new Date().toString());
        ModelAndView loginMav = new ModelAndView();
        loginMav.setViewName("login");
        return loginMav;
    }

    @RequestMapping(value="/app", method = RequestMethod.POST)
    public ModelAndView navigateToApp(ModelMap model, @ModelAttribute User user){
        ModelAndView appMav = new ModelAndView();

        if(loginService.validate(user.getUsername(), user.getPassword())) {
            loginLogger.info("Login Controller --- navigateToApp --- " + new Date().toString());
            appMav.addObject("name", user.getUsername());
            appMav.addObject("user", user);
            appMav.addObject("recipes", recipeService.getRecipes());
            appMav.setViewName("app");
        } else {
            loginLogger.error("Login Controller --- navigateToApp --- " + new Date().toString());
            appMav.addObject("invalidCreds", true);
            appMav.setViewName("login");
        }

        return appMav;
    }
}
