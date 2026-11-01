package oop.gourmetquest;

import java.io.File;
import java.util.*;
import java.util.logging.FileHandler;

import oop.gourmetquest.Model.Recipes;

public class App {
    public static final Scanner scanner = new Scanner(System.in);
    public static final GourmetQuest gourmetQuest = new GourmetQuest();
    private static final File FilePathSaving = new File("C:\\Users\\ASUS\\Documents\\2nd year advance study\\GourmetQuest\\gourmetquest\\src\\main\\Data\\SavedRecipes.json");


    public static void main( String[] args ){
        
        while(true){
            System.out.println("Gourmet Quest: Search A food that will change your Life!");
            System.out.println("[1] Search recipes");
            System.out.println("[2] Saved Recipes");
            System.out.println("[3] To do List");
            System.out.println("[3] What can I cook? [Random Recipes]");
            System.out.println("[4] Exit");
            System.out.print("Choose Your Path: ");
            
            String FoodPath = scanner.nextLine();

            switch (FoodPath) {
                case "1":
                    searchRecipes();
                    break;
                case "2":
                    SavedRecipes();
                    break;
                case "3":

                    break;
                case "4":
                    System.out.println("\nUntil we meet again traveler\n");
                    programmedClosed();
                default:
                    clearScreen();
                    invalidInput();
                    continue;

            }
            clearScreen();
        }
    }

    private static void searchRecipes() {
        searchRecipes : while (true) {
            clearScreen();
            System.out.println("How do you want to search[X to exit]:");
            System.out.println("[1] Search through name");
            System.out.println("[2] Search through filtering");
            System.out.print("Choose Your Path: ");

            String SearchPath = scanner.nextLine();
                if (SearchPath.equalsIgnoreCase("X")) {
                    break searchRecipes;
                }
                if (SearchPath.equalsIgnoreCase("1")) {
                    gourmetQuest.SearchName();
                }
                else if (SearchPath.equalsIgnoreCase("2")) {
                    gourmetQuest.filterIngredients();
                }
                else {
                    invalidInput();
                }            
        }

    }

    // nigga my error dindi dahil sa [] nung save json file if wala nun di makaka save if meron nun di gaga na filepath....length == 0 sionce it is not zero i thinl
    private static void SavedRecipes() {
    
        clearScreen();
        savedrecipes : while (true) {
            System.out.println("Recipes You have saved [Type TD to put it into to do list]: ");

            if(!FilePathSaving.exists()) {
                System.out.println("Could not find file path for Saving!");
                break savedrecipes;
            }

            List<Recipes> listOfResults = oop.gourmetquest.DataHandler.FileHandler.deserialize(FilePathSaving);

            if (listOfResults.isEmpty()) {
                System.out.println("Your saved recipes is empty!");
            }
            else {
                oop.gourmetquest.DataHandler.FileHandler.outputRecipes(listOfResults);

            }

            System.out.println("-".repeat(30));
                System.out.print("Choose a recipe to view[X to Exit]: ");
                String PickARecipe = scanner.nextLine();

                if (PickARecipe.equalsIgnoreCase("X")) {
                    break savedrecipes;
                }
                if (listOfResults.isEmpty()) {
                    System.out.println("-".repeat(150));
                    System.out.println("You cannot choose if you dont have a choice at all!");
                    System.out.println("-".repeat(150));
                }
                else if (PickARecipe.equalsIgnoreCase("TD")) {
                    // gourmetQuest.SearchName();
                }
                else {
                    Recipes selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                    System.out.println("-".repeat(150));
                    selectedItem.DisplayFood(); 
                }            

        }

    }

    private static void RandomRecipes () {
        System.out.println("Choose a meal type: ");
        System.out.println("[1] Breakfast");
        System.out.println("[2] Lunch");
        System.out.println("[4] Dessert ");
        System.out.println("[5] Appetizer");
        System.out.print("Choose Your Path: ");

        int pickrandomRecipe = scanner.nextInt();


    }






    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void invalidInput() {
        System.out.println("Input is invalid!\n");

    }
    public static void programmedClosed(){
        System.exit(0);
    }
   
    

}
