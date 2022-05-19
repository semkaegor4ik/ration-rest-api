package com.university.fqw.rationrestapi.controller;

import com.university.fqw.rationrestapi.entity.NormalRecipe;
import com.university.fqw.rationrestapi.entity.RecipeType;
import com.university.fqw.rationrestapi.entity.UserData;
import com.university.fqw.rationrestapi.entity.Week;
import com.university.fqw.rationrestapi.service.RecipeBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ration")
@RequiredArgsConstructor
public class BasicController {
    private final RecipeBuilderService recipeBuilderService;

    @GetMapping()
    public @ResponseBody Map<Week, Map<RecipeType, NormalRecipe>> getRecipes(@RequestBody UserData userData) {
        return recipeBuilderService.getRecipes(userData);
    }
}
