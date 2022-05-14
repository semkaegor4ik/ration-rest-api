package com.university.fqw.rationrestapi.service;


import com.university.fqw.rationrestapi.entity.Recipe;
import com.university.fqw.rationrestapi.entity.RecipeType;

import java.util.List;

public interface RecipeService {
    List<Recipe> findByRecipeType(RecipeType recipeType);
}
