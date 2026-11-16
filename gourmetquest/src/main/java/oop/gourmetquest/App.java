package oop.gourmetquest;

import java.io.File;
import java.io.IOException;
import java.util.*;

import oop.gourmetquest.Model.*;


public class App {
    public static final Scanner scanner = new Scanner(System.in);

    private static final File FilePathSaving = new File("C:gourmetquest\\\\src\\\\main\\\\Data\\SavedRecipes.json");
    private static final File FilePathToDoList = new File("C:gourmetquest\\src\\main\\Data\\RecipeToDoList.json");

    public static final Produced produced = new Produced();
    public static final MeatProduct meatProduct = new MeatProduct();
    public static final HerbSpices herbSpices = new HerbSpices();
    public static final Dairy dairy = new Dairy();
    public static final OilCondiments oilCondiments = new OilCondiments();
    public static final Pantry pantry = new Pantry();

    public static FileHandler fileHandler = new FileHandler(produced, meatProduct, herbSpices, dairy, oilCondiments, pantry);
    public static GourmetQuest gourmetQuest = new GourmetQuest(produced, meatProduct, herbSpices, dairy, oilCondiments, pantry, fileHandler);
    

    public static void main( String[] args ){

        while(true){
            clearScreen();
            List<Recipes> listOfResults = FileHandler.deserialize(FilePathToDoList);

            System.out.println("Gourmet Quest: Search A food that will change your Life!");
            System.out.println("[1] Search recipes");
            System.out.println("[2] Saved Recipes");
            System.out.println("[3] To do List [" + listOfResults.size() +"]");
            System.out.println("[4] What can I cook? [Random Recipes]");
            System.out.println("[5] Exit");
            System.out.print("Choose Your Path: ");
            
            String FoodPath = scanner.nextLine();

            switch (FoodPath.toLowerCase()) {
                case "1":
                    searchRecipes();
                    break;
                case "2":
                    SavedRecipes();
                    break;
                case "3":
                    toDoList();
                    break;
                case "4":
                    RandomRecipes();
                    break;
                case "5":
                    System.out.println("\nUntil we meet again traveler\n");
                    programmedClosed();
                default:
                    invalidInput();
                    continue;

            }
        }
    }

    private static void searchRecipes() {
        clearScreen();
        searchRecipes : while (true) {
            System.out.println("How do you want to search[X to exit]:");
            System.out.println("[1] Search through name");
            System.out.println("[2] Search through filtering");
            System.out.print("Choose Your Path: ");

            String SearchPath = scanner.nextLine();

            switch (SearchPath.toLowerCase()) {
                case "1":
                    gourmetQuest.SearchName();
                    clearScreen();
                    break;
                case "2":
                    gourmetQuest.filterIngredients();
                    clearScreen();
                    break;
                case "x":
                    break searchRecipes;
                default:
                    invalidInput();        
                    continue;  
            }
        }

    }
    // savedrecipes and todlosit method is literally same might put it into one mamaya mweeehheehehehehe or dont 
    private static void SavedRecipes() {
        clearScreen();
        SavedRecipes : while (true) {
            System.out.println("Recipes You have saved [[TD] Add to-do list | [D] Delete saved recipe]: ");

            if(!FilePathSaving.exists()) {
                System.out.println("Could not find file path for Saving!");
                break;
            }

            List<Recipes> listOfResults = FileHandler.deserialize(FilePathSaving);

            if (listOfResults.isEmpty()) {
                System.out.println("Your saved recipes is empty!");
            }
            else {
                FileHandler.outputRecipes(listOfResults);

            }
            System.out.print("Choose a recipe to view[X to Exit]: ");
            String PickARecipe = scanner.nextLine();

            if (listOfResults.isEmpty()) {
                clearScreen();
                System.out.println("You cannot choose if you dont have a choice at all!");
                System.out.println("-".repeat(150));
                continue;
                
            }

            if (PickARecipe.equalsIgnoreCase("X")) {
                break SavedRecipes;
            }
            else if (PickARecipe.equalsIgnoreCase("D")) {
                GourmetQuest.deleteList(PickARecipe, listOfResults, "SavedRecipes");
                clearScreen();
            }
            else if (PickARecipe.equalsIgnoreCase("TD")) {
                GourmetQuest.AddToDoList(listOfResults, FilePathToDoList);
                clearScreen();
            }
            else {
                try {
                    Recipes selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                    System.out.println("-".repeat(150));
                    selectedItem.DisplayFood();
                    clearScreen();       
                }catch (IndexOutOfBoundsException e) {
                    handleException(e);
                }catch (NumberFormatException e) {
                    handleException(e);
                }
            }
            

        }

    }
    private static void toDoList() {
        clearScreen();
        todoList : while(true) {
            System.out.println("Your to do List: ");

            if(!FilePathToDoList.exists()) {
                System.out.println("Could not find file path for To do list!");
                break;
            }

            List<Recipes> listOfResults = FileHandler.deserialize(FilePathToDoList);

            if (listOfResults.isEmpty()) {
                System.out.println("-".repeat(150));
                System.out.println("Your to do list recipes is empty!");
            }
            else {
                FileHandler.outputRecipes(listOfResults);

            }
            System.out.println("-".repeat(150));
            System.out.print("Choose a recipe to Do [X to Exit]: ");
            String PickARecipe = scanner.nextLine();
            if (PickARecipe.equalsIgnoreCase("X")) {
                break todoList;
            }
            if (listOfResults.isEmpty()) {
                clearScreen();
                System.out.println("You cannot choose if you dont have a choice at all!");
                System.out.println("-".repeat(150));
                continue;
                
            }
            else {
                try {
                    GourmetQuest.deleteList(PickARecipe, listOfResults, "toDoList");
                } catch (NumberFormatException e ) {
                    handleException(e);
                } catch (Exception e ) {
                    handleException(e);
                } 
            }
                

        }
            
    }

    private static void RandomRecipes() {
        clearScreen();
        String randomCategory = null;
        RandomRecipes :while (true) {
            System.out.println("Choose a meal type: ");
            System.out.println("[1] Breakfast");
            System.out.println("[2] Lunch");
            System.out.println("[3] Dinner");
            System.out.println("[4] Dessert ");
            System.out.println("[5] Appetizer");
            System.out.print("Choose Your Path: ");

            String pickrandomRecipe = scanner.nextLine();

            switch (pickrandomRecipe.toLowerCase()) {
                case "1":
                    randomCategory = "Breakfast";
                    break;
                case "2":
                    randomCategory = "Lunch";
                    break;
                case "3":
                    randomCategory = "Dinner";
                    break;
                case "4":
                    randomCategory = "Dessert";
                    break;
                case "5":
                    randomCategory = "Appetizer";
                    break;
                case "x" :
                    break RandomRecipes;
                default:
                    App.invalidInput();
                    continue;
            }  
            while (true) {
                try{
                    Recipes randomRecipe = FileHandler.generateRandomRecipe(randomCategory);

                    System.out.println("-".repeat(150));
                    System.out.print("Do you want to saved this recipe [Y/X]: ");
                    String toSaved = scanner.nextLine();

                    if(toSaved.equalsIgnoreCase("Y")) {
                        FileHandler.serialize(randomRecipe, FilePathSaving);
                        break;
                    }
                    else if(toSaved.equalsIgnoreCase("X")) {
                        clearScreen();
                        break;
                    }
                    else {
                        continue;
                    }
                    
                } catch (Exception e) {
                    App.handleException(e);
                }    
            } 
            
            
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J\033[3J");
        System.out.flush();
    }
    //this is just for invalid input wihtout try catch.
    public static void invalidInput() {
        clearScreen();
        System.out.println("Invalid input. Please enter a valid input!");
        System.out.println("-".repeat(150));

    }
    public static void programmedClosed(){
        System.exit(0);
    }

    public static void handleException(Exception e) {

        if (e instanceof NumberFormatException) {
            clearScreen();
            System.out.println("Error: Please enter integers only!");
            System.out.println("-".repeat(150));
        }
        else if (e instanceof IndexOutOfBoundsException) {
            clearScreen();
            System.out.println("Error: Invalid recipe number!");
            System.out.println("-".repeat(150));
        }
        else if (e instanceof NullPointerException) {
            clearScreen();
            System.out.println("Error: Something is null!");
            System.out.println("-".repeat(150));
        }
        else if (e instanceof InputMismatchException) {
            clearScreen();
            System.out.println("Invalid input. Please enter a valid number!");
            System.out.println("-".repeat(150));
        }
        else if (e instanceof IOException) {
            clearScreen();
            System.out.println("Failed to save JSON file");
            System.out.println("-".repeat(150));
        }
        else {
            clearScreen();
            System.out.println("Error: " + e.getMessage());
            System.out.println("-".repeat(150));
        }

    }
}
   
