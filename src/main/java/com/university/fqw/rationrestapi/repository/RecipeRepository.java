package com.university.fqw.rationrestapi.repository;

import com.university.fqw.rationrestapi.entity.Recipe;
import com.university.fqw.rationrestapi.entity.RecipeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByRecipeType(RecipeType recipeType);

}
