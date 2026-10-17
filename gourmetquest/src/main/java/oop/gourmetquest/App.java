package oop.gourmetquest;

import java.util.*;

public class App {
    public static final Scanner scanner = new Scanner(System.in);
    public static final GourmetQuest gourmetQuest = new GourmetQuest();
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
