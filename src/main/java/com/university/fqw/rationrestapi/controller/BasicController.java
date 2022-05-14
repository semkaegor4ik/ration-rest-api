package com.university.fqw.rationrestapi.controller;

import com.university.fqw.rationrestapi.entity.NormalRecipe;
import com.university.fqw.rationrestapi.entity.RecipeType;
import com.university.fqw.rationrestapi.entity.User;
import com.university.fqw.rationrestapi.entity.Week;
import com.university.fqw.rationrestapi.service.RecipeBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/ration")
@RequiredArgsConstructor
public class BasicController {
    private final RecipeBuilderService recipeBuilderService;

    @GetMapping()
    public Map<Week, Map<RecipeType, NormalRecipe>> getRecipes(User user) {
        return recipeBuilderService.getRecipes(user);
    }
}
