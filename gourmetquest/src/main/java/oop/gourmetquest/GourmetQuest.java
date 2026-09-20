package oop.gourmetquest;
import  oop.gourmetquest.Model.*;
import  oop.gourmetquest.IO.*;


import java.util.*;



public class GourmetQuest {
    public static final Scanner scanner = new Scanner(System.in);

    FileHandler foodhHandler = new FileHandler();
    // FoodItems foodItems = new FoodItems();

    String Path;

    GourmetQuest(String Path){
       this.Path = Path;
    }

    void Searchquisine(){
        App.clearScreen();
        System.out.println("Find a Food that will make you feel the power [X to Exit]: ");
        System.out.println();
        while (true) {
            System.out.print("Search: ");
            String Search = scanner.nextLine();

            if (Search.equalsIgnoreCase("X"))
                return;
            else{
                FoodItems Found = foodhHandler.SearchingName(Search);
                if (Found != null) {
                    Found.DisplayFood();
                }
                if (Found == null) {
                    App.clearScreen();
                    System.out.println("Food not found!\n");
                    
                }
            }
        }
    }
    void IngredientsSearch(){
        // System.out.println();
        System.out.println("Summon Food Through Ingredients: ");
        System.out.println();
        while (true) {
            System.out.print("Choose Your Path: ");
            int ingredientsPick = scanner.nextInt();
            switch (ingredientsPick) {
                case 1:
                    
                    break;
                case 2:

                    break;
                case 3:

                    break;

                case 4:

                    break;
            
                default:
                    break;
            }


        }

    }
    void FoodSummon(){
         System.out.println("Summoning your Food: ");

         
    }


}
