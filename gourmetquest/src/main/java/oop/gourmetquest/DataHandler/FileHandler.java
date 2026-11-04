package oop.gourmetquest.DataHandler;
import  oop.gourmetquest.Model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class FileHandler {

    // https://github.com/Ovi/DummyJSON/blob/master/database/recipes.json
    // private static final File FilePath = new File("C:\\Users\\ASUS\\Documents\\2nd year advance study\\GourmetQuest\\gourmetquest\\src\\main\\Data\\recipes.json");
    private static final File FilePathRecipes = new File("C:\\Users\\ASUS\\Documents\\2nd year advance study\\GourmetQuest\\gourmetquest\\src\\main\\Data\\Breakfast.json");
    private static final File FilePathSaving = new File("C:gourmetquest\\src\\main\\Data\\SavedRecipes.json");

    private static final ObjectMapper mapper = new ObjectMapper();

    //jackson objectmapper
    public static List<Recipes> deserialize(File Filepath){
        try {
            return mapper.readValue(Filepath, new TypeReference<List<Recipes>>() {});
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static void serialize(Recipes recipe) throws Exception {
        List<Recipes> savedRecipes = new ArrayList<>();

        if (FilePathSaving.exists()) {
            savedRecipes = deserialize(FilePathSaving);
        }

        boolean isSaved = false;
        for (Recipes savedRecipe : savedRecipes) {
            if(savedRecipe.getName().equalsIgnoreCase(recipe.getName())) {
                isSaved = true;
                break;
            }
        }
        if (!isSaved) {
            savedRecipes.add(recipe);
            System.out.println("-".repeat(150));
            System.out.println("Successfully Added " + recipe.getName());
            System.out.println("-".repeat(150));
        }
        else {
            System.out.println("-".repeat(150));
            System.out.println( "The recipe " + recipe.getName() + " was already saved");
            System.out.println("-".repeat(150));
        }
        
        mapper.writerWithDefaultPrettyPrinter().writeValue(FilePathSaving, savedRecipes);
    }

    //find Food name
    public static List<Recipes> SearchingName(String Search){

        List<Recipes> recipes = deserialize(FilePathRecipes);
        List<Recipes> listOfResults = new ArrayList<>();
        if (recipes != null) {
            for (Recipes recipe : recipes){
                if (recipe.getName().toLowerCase().contains(Search.toLowerCase())){
                    listOfResults.add(recipe);
                }
            }
        }
        return listOfResults;
    }

    //Output saved or data recipes
    public static void outputRecipes (List<Recipes> listOfResults) {
        int count = 1;
            for(Recipes recipe : listOfResults){
                System.out.println("-".repeat(150));
                System.out.println(count + " -- " + recipe.getName());
                count++;
            } 
    }


    // filtering every items
    public static  List<Recipes> filteringEveryItems (List<String> AllIngredients) {
        List<Recipes> recipes = deserialize(FilePathRecipes);
        List<Recipes> listOfResults = new ArrayList<>();
       
        for (Recipes recipe : recipes) {
            boolean allRecipeIngredientsFound = true;
            for (String recipeIngredient : recipe.getIngredients()) {
                boolean found = false;
                for (String ingredient : AllIngredients) {
                    if (recipeIngredient.toLowerCase().contains(ingredient.toLowerCase())) {
                        found = true;      
                        break;             
                    }
                }
                if (!found) {
                    allRecipeIngredientsFound = false;   
                    break;                              
                }
            }

            if (allRecipeIngredientsFound) {
                listOfResults.add(recipe);
            }
        }
        return listOfResults;
    }
    
    //filtering thorgh ingredients At least one
    public static  List<Recipes> filteringAtLeast1 (List<String> AllIngredients) {
        List<Recipes> recipes = deserialize(FilePathRecipes);
        List<Recipes> listOfResults = new ArrayList<>();
       
        for (Recipes recipe : recipes ) {
            for (String RecipeIngredient : recipe.getIngredients()) {
                for (String ingredient : AllIngredients) {
                    if(RecipeIngredient.toLowerCase().contains(ingredient.toLowerCase())) {
                    listOfResults.add(recipe);
                    }   
                } 
            }
        }
        return listOfResults;
    }

    public static void findIngredientsToDelete(String ingredientCategory, String ingredienttoRemoved) {
        if (ingredientCategory.equals("1")) {

        }
    }
    

}
