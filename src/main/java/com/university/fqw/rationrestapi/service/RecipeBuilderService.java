package com.university.fqw.rationrestapi.service;

import com.university.fqw.rationrestapi.entity.Gender;
import com.university.fqw.rationrestapi.entity.NormalRecipe;
import com.university.fqw.rationrestapi.entity.Recipe;
import com.university.fqw.rationrestapi.entity.RecipeType;
import com.university.fqw.rationrestapi.entity.User;
import com.university.fqw.rationrestapi.entity.Week;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeBuilderService {
    private final RecipeService recipeService;
    private final Random rand = new Random();

    public Map<Week, Map<RecipeType, NormalRecipe>> getRecipes(User user) {
        Map<Week, Map<RecipeType, NormalRecipe>> weekMapHashMap = new HashMap<>();

        for (Week week : Week.values()) {
            Map<RecipeType, NormalRecipe> recipeMap = new HashMap<>();

            for (RecipeType recipeType : RecipeType.values()) {
                recipeMap.put(recipeType, getRecipe(recipeType, user));
            }
            weekMapHashMap.put(week, recipeMap);
        }
        return weekMapHashMap;
    }

    public NormalRecipe getRecipe(RecipeType recipeType, User user) {
        List<Recipe> recipes = recipeService.findByRecipeType(recipeType);
        Recipe recipe = recipes.get(rand.nextInt(recipes.size()));

        Gender gender = user.getGender();
        double calories = gender.getStartCoefficient() + (gender.getWeightCoefficient() * user.getWeight() + (gender.getHeightCoefficient() * user.getHeight()) - (gender.getAgeCoefficient() * user.getAge())) * user.getActivity().getCoefficient();

        return new NormalRecipe(recipe.getName(), recipe.getDescription(), recipe.getProductList().entrySet().stream()
                .map(entry -> Map.entry(entry.getKey().getName(), (int) (entry.getValue() * (calories * recipe.getRecipeType().getProcent() * 100) / entry.getKey().getCaloriesPerHundredGram())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
