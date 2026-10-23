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
    private static final File FilePath = new File("C:\\Users\\ASUS\\Documents\\2nd year advance study\\GourmetQuest\\gourmetquest\\src\\main\\Data\\Breakfast.json");

    private static final ObjectMapper mapper = new ObjectMapper();

    //jackson objectmapper
    public static List<Recipes> deserialize(){
        try {
            return mapper.readValue(FilePath, new TypeReference<List<Recipes>>() {});
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    //find Food name
    public static List<Recipes> SearchingName(String Search){

        List<Recipes> recipes = deserialize();
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


    // filtering every items
    public static  List<Recipes> filteringEveryItems (List<String> AllIngredients) {
        List<Recipes> recipes = deserialize();
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
        List<Recipes> recipes = deserialize();
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

}
