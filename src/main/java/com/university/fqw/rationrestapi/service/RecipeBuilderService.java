package com.university.fqw.rationrestapi.service;

import com.university.fqw.rationrestapi.entity.Gender;
import com.university.fqw.rationrestapi.entity.NormalRecipe;
import com.university.fqw.rationrestapi.entity.Recipe;
import com.university.fqw.rationrestapi.entity.RecipeType;
import com.university.fqw.rationrestapi.entity.UserData;
import com.university.fqw.rationrestapi.entity.Week;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Map<Week, Map<RecipeType, NormalRecipe>> getRecipes(UserData user) {

        Map<RecipeType, List<Recipe>> recipeMapSet = new HashMap<>();
        for (RecipeType recipeType : RecipeType.values()) {
            List<Recipe> recipeList = new ArrayList<>();
            List<Recipe> recipes = recipeService.findByRecipeType(recipeType);
            for (Week week : Week.values()) {
                int size = recipes.size();
                if (size != 0) {
                    int randInt = rand.nextInt(size);
                    Recipe recipe = recipes.get(randInt);
                    recipeList.add(recipe);
                    recipes.remove(randInt);
                } else {
                    recipes = recipeService.findByRecipeType(recipeType);
                }
            }
            recipeMapSet.put(recipeType, recipeList);
        }

        Map<Week, Map<RecipeType, NormalRecipe>> resultMap = new HashMap<>();

        Arrays.stream(Week.values()).forEach(week -> resultMap.put(week,
                recipeMapSet.entrySet().stream()
                        .map(entry ->
                                Map.entry(entry.getKey(), entry.getValue().stream()
                                .map(recipe ->
                                        getNormalRecipe(recipe, user))
                                .collect(Collectors.toList())))
                        .map(entry ->
                                Map.entry(entry.getKey(), entry.getValue().get(week.ordinal())))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));

        return resultMap;
    }

    private NormalRecipe getNormalRecipe(Recipe recipe, UserData user) {
        Gender gender = user.getGender();
        double calories = gender.getStartCoefficient() + (gender.getWeightCoefficient() * user.getWeight() + (gender.getHeightCoefficient() * user.getHeight()) - (gender.getAgeCoefficient() * user.getAge())) * user.getActivity().getCoefficient();

        return new NormalRecipe(recipe.getName(), recipe.getEncodedView(), recipe.getDescription(), recipe.getProductList().entrySet().stream()
                .map(entry -> Map.entry(entry.getKey().getName(), (int) (entry.getValue() * (calories * recipe.getRecipeType().getProcent() * 100) / entry.getKey().getCaloriesPerHundredGram())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
