package com.university.fqw.rationrestapi.service;


import com.university.fqw.rationrestapi.entity.Recipe;
import com.university.fqw.rationrestapi.entity.RecipeType;
import com.university.fqw.rationrestapi.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> findByRecipeType(RecipeType recipeType) {
        return recipeRepository.findByRecipeType(recipeType);
    }
}
