package oop.gourmetquest.Model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id", "userId", "image", "rating", "reviewCount"})
public class Recipes {

    public String name;
    public List<String> ingredients;
    public List<String> instructions;
    public int prepTimeMinutes;
    public int cookTimeMinutes;
    public int servings;
    public String difficulty;
    public String cuisine;
    public int caloriesPerServing;
    public List<String> tags;
    public List<String> mealType;

    Recipes(){}

    Recipes(String name, List<String> ingredients, List<String> instructions, int prepTimeMinutes, int cookTimeMinutes,
        int servings, String difficulty, String cuisine, int caloriesPerServing, List<String> tags, List<String> mealTypes) {

            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
            this.prepTimeMinutes = prepTimeMinutes;
            this.cookTimeMinutes = cookTimeMinutes;
            this.servings = servings;
            this.difficulty = difficulty;
            this.cuisine = cuisine;
            this.caloriesPerServing = caloriesPerServing;
            this.tags = tags;
            this.mealType = mealTypes;
     }

    // Getters
    public String getName () {
        return name;
    }
    public List<String> getIngredients () {
        return ingredients;
    }
    public List<String> getInstructions () {
        return instructions;
    }
    public int getPrepTimeMinutes (){
        return prepTimeMinutes;
    }
    public int getcookTimeMinutes (){
        return cookTimeMinutes;
    }
    public int getservings (){
        return servings;
    }
    public String getDifficulty (){
        return difficulty;
    }
    public String getCuisine (){
        return cuisine;
    }
    public int getCaloriesPerServing(){
        return caloriesPerServing;
    }
    public List<String> getTags (){
        return tags;
    }
    public List<String> getMealType (){
        return mealType;
    }
    
    public void DisplayFood(){
        int i = 1;
        int j = 1;
        
        System.out.println("Name: " + name);
        System.out.println("Origin: " + cuisine);
        System.out.println("Difficulty: " + difficulty);
        
        System.out.print("Tags: ");
        for (String tag : tags) {System.out.print("[" + tag + "] ");}

        System.out.print("\nMeal Type: ");
        for (String type : mealType){System.out.print("[" + type + "] ");}

        System.out.println("\nPreparation Time: " + prepTimeMinutes + " minutes");
        System.out.println("Cooking Time: " + cookTimeMinutes + " minutes");
        System.out.println("Per Serving:" + servings);
        System.out.println("Calorie Per Serving: " + caloriesPerServing);
        System.out.println("-".repeat(30));
        System.out.println("Ingredients: ");
        for(String ingredient : ingredients){
            System.out.println(i +" - " + ingredient);
            i++;
        }
        System.out.println("-".repeat(30));
        System.out.println("Method: " );
        for(String steps : instructions){
            System.out.println("Step " + j + ":\n" +" \t- " + steps);
            j++;
        }
    }


    
}

