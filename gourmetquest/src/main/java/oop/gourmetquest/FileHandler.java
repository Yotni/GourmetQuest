package oop.gourmetquest;
import  oop.gourmetquest.Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class FileHandler {

    public static final Scanner scanner = new Scanner(System.in);

    public static final File FilePathRecipes = new File("gourmetquest\\src\\main\\Data\\Breakfast.json");
    public static final File FilePathSaving = new File("gourmetquest\\src\\main\\Data\\SavedRecipes.json");
    public static final File FilePathToDoList = new File("gourmetquest\\src\\main\\Data\\RecipeToDoList.json");

    private static final ObjectMapper mapper = new ObjectMapper();

    private Produced produced;
    private MeatProduct meatProduct;
    private HerbSpices herbSpices;
    private Dairy dairy;
    private OilCondiments oilCondiments;
    private Pantry pantry;


    public FileHandler (Produced produced, MeatProduct meatProduct, HerbSpices herbSpices, Dairy dairy, OilCondiments oilCondiments, Pantry pantry) {
        this.produced = produced;
        this.meatProduct = meatProduct;
        this.herbSpices = herbSpices;
        this.dairy = dairy;
        this.oilCondiments = oilCondiments;
        this.pantry = pantry;
    }

    //jackson objectmapper
    public static List<Recipes> deserialize(File Filepath) throws IOException{
        if (Filepath == null || !Filepath.exists()) {
            throw new IOException();
        }

        return mapper.readValue(Filepath, new TypeReference<List<Recipes>>() {});
    }

    public static void serialize(Recipes pickRecipe, File FilePath) throws IOException {
        if (!FilePath.exists()) {
             Files.writeString(FilePath.toPath(), "[]");
        }
 
        List<Recipes> Recipes = deserialize(FilePath);
    
        boolean isSaved = false;
        for (Recipes Recipe : Recipes) {
            if(Recipe.getName().equalsIgnoreCase(pickRecipe.getName())) {
                isSaved = true;
                break;
            }
        }
        if (!isSaved) {
            Recipes.add(pickRecipe);
            App.clearScreen();
            System.out.println("Successfully added: " + pickRecipe.getName());
            System.out.println("-".repeat(150));
        }
        else {
            if(FilePath.equals(FilePathSaving)) {
                App.clearScreen();
                System.out.println( "The recipe " + pickRecipe.getName() + " was already saved");
            }
            else if (FilePath.equals(FilePathToDoList)) {
                App.clearScreen();
                System.out.println( "The recipe " + pickRecipe.getName() + " is already on your to do list.");
            }

            System.out.println("-".repeat(150));

            
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(FilePath, Recipes);
        
    }

    public static void removedARecipe(Recipes selectedItem, List<Recipes> listOfResults, String CallerType) throws IOException{
        if (CallerType.equalsIgnoreCase("SavedRecipes")) {

            listOfResults.remove(selectedItem);

            mapper.writerWithDefaultPrettyPrinter().writeValue(FilePathSaving, listOfResults);

            App.clearScreen();
            System.out.println(selectedItem.getName() + " was removed from saved recipes");

        }
        else if (CallerType.equalsIgnoreCase("toDoList")) {
            listOfResults.remove(selectedItem);

            mapper.writerWithDefaultPrettyPrinter().writeValue(FilePathToDoList, listOfResults);

            App.clearScreen();
            System.out.println(selectedItem.getName() + " was removed from To do list");
        }
        
        
        
        System.out.println("-".repeat(150));

        
    }

    //find Food name
    public static List<Recipes> SearchingName(String Search) throws IOException{

        List<Recipes> recipes = deserialize(FilePathRecipes);  
        
        List<Recipes> listOfResults = new ArrayList<>();

        if(Search.isEmpty()) {
            return listOfResults;   
        }
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
    public static  List<Recipes> filteringEveryItems (List<String> AllIngredients) throws IOException {
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
    public static  List<Recipes> filteringAtLeast1 (List<String> AllIngredients) throws IOException {
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
    // This will removed ingredients automcatically.

    public List<String> ouputIngredientsToRemoved(String ingredientsPick) {
        List<String> totalBasketsByCategory = null;
        if (ingredientsPick.equalsIgnoreCase("1")) {
            List<String> basketVegetables = produced.getVegetables();
            List<String> basketFruits = produced.getFruits();
            totalBasketsByCategory = combineBaskets(basketVegetables, basketFruits);
            
        }
        else if (ingredientsPick.equalsIgnoreCase("2")) {
            List<String> basketLandMeats = meatProduct.getLandMeats();
            List<String> basketFishes = meatProduct.getFishes();
            List<String> basketProcessedMeats = meatProduct.getProcessedMeats();
            totalBasketsByCategory = combineBaskets(basketLandMeats, basketFishes, basketProcessedMeats);
        }
        else if (ingredientsPick.equalsIgnoreCase("3")) {
            List<String> basketSpices = herbSpices.getSpices();
            List<String> basketHerbs = herbSpices.getHerbs();
            totalBasketsByCategory = combineBaskets(basketSpices, basketHerbs);
        }
        else if (ingredientsPick.equalsIgnoreCase("4")) {
            List<String> basketCondiments = oilCondiments.getCondiments();
            List<String> basketSauces = oilCondiments.getSauces();
            List<String> basketOils = oilCondiments.getOils();
            totalBasketsByCategory = combineBaskets(basketCondiments, basketSauces, basketOils);
        }
        else if (ingredientsPick.equalsIgnoreCase("5")) { 
           List<String> basketMilks = dairy.getMilks();
            List<String> basketCheeses= dairy.getCheeses();
            List<String> baskettCreams = dairy.getCreams();
            List<String> basketButters = dairy.getButters();
            totalBasketsByCategory = combineBaskets(basketMilks, basketCheeses, baskettCreams, basketButters);
        }
        else if (ingredientsPick.equalsIgnoreCase("6")) { 
            List<String> basketGrains = pantry.getGrains();
            List<String> basketCannedGoods = pantry.getCannedGoods();
            List<String> basketBakingStaples = pantry.getBakingStaples();
            totalBasketsByCategory = combineBaskets(basketGrains, basketCannedGoods, basketBakingStaples);
        }
        return totalBasketsByCategory;
    }

    @SafeVarargs
    public final List<String> combineBaskets(List<String>... baskets) {

    List<String> totalBasketsByCategory  = new ArrayList<>();

    for (List<String>  basket : baskets) {
        for (String player : basket) {
            totalBasketsByCategory.add(player);
        }
    }

    return totalBasketsByCategory;
    }

    public void executeRemovalOfIngredients (String ingredientsPick, String removedIngredient) {
        if(ingredientsPick.equals("1")) {
                
            if (produced.getVegetables().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from vegetables.");
            }
            else if (produced.getFruits().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from fruits.");
            }
        }
        else if(ingredientsPick.equals("2")) {
            
            if (meatProduct.getLandMeats().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from land meats.");
            }
            else if (meatProduct.getFishes().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from fishes.");
            }
            else if (meatProduct.getProcessedMeats().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from fishes.");
            }
        }
        else if(ingredientsPick.equals("3")) {
            
            if (herbSpices.getHerbs().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from herbs.");
            }
            else if (herbSpices.getSpices().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from spices.");
            }
        }
        else if(ingredientsPick.equals("4")) {
            
            if (oilCondiments.getCondiments().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from condiments.");
            }
            else if (oilCondiments.getOils().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from oils.");
            }
            else if (oilCondiments.getSauces().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from sauces.");
            }
        }
        else if(ingredientsPick.equals("5")) {
            
            if (dairy.getMilks().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from milks.");
            }
            else if (dairy.getCheeses().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from cheeses.");
            }
            else if (dairy.getButters().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from butters.");
            }
            else if (dairy.getCreams().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from creams.");
            }
        }
        else if(ingredientsPick.equals("6")) {
            
            if (pantry.getGrains().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from Grains.");
            }
            else if (pantry.getCannedGoods().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from Canned Goods.");
            }
            else if (pantry.getBakingStaples().remove(removedIngredient)){
                System.out.println(removedIngredient + " was removed from Baking Staples.");
            }
        }
    }

    
    public static Recipes generateRandomRecipe(String randomCategory) {

        Random random = new Random();
        
        List<Recipes> recipes = null;
        try {
            recipes = deserialize(FilePathRecipes);
        } catch (FileNotFoundException e) {
            App.handleException(e);
            return null; 
        } catch (IOException e) {
            App.handleException(e);
            return null; 
        }
        List<Recipes> listOfResults = new ArrayList<>();

        for (Recipes recipe : recipes) {
            if(recipe.getMealType().contains(randomCategory)) {
                listOfResults.add(recipe);
            }
        }
        int randomInt = random.nextInt(listOfResults.size()); 

        Recipes randomRecipe = listOfResults.get(randomInt);

        randomRecipe.DisplayFood();
        return randomRecipe;


    }

    public List<String> getTotalIngredients() {
        List<String> basketVegetables = produced.getVegetables();
        List<String> basketFruits = produced.getFruits();
        List<String> basketLandMeats = meatProduct.getLandMeats();
        List<String> basketFishes = meatProduct.getFishes();
        List<String> basketProcessedMeats = meatProduct.getProcessedMeats();
        List<String> basketSpices = herbSpices.getSpices();
        List<String> basketHerbs = herbSpices.getHerbs();
        List<String> basketCondiments = oilCondiments.getCondiments();
        List<String> basketSauces = oilCondiments.getSauces();
        List<String> basketOils = oilCondiments.getOils();
        List<String> basketMilks = dairy.getMilks();
        List<String> basketCheeses= dairy.getCheeses();
        List<String> baskettCreams = dairy.getCreams();
        List<String> basketButters = dairy.getButters();
        List<String> basketGrains = pantry.getGrains();
        List<String> basketCannedGoods = pantry.getCannedGoods();
        List<String> basketBakingStaples = pantry.getBakingStaples();

        
        List<List<String>> allIngredients = List.of(basketVegetables, basketFruits, basketLandMeats, basketFishes, 
                                            basketProcessedMeats, basketSpices, basketHerbs, basketCondiments, basketSauces, 
                                            basketOils, basketMilks, basketCheeses, baskettCreams, basketButters, basketGrains,
                                            basketCannedGoods, basketBakingStaples
                                        );

        List<String> TotalBasket = new ArrayList<>();

        for (List<String> ingredient : allIngredients) {
            TotalBasket.addAll(ingredient);
        }

        return TotalBasket;
    

    }
}
