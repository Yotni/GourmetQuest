package oop.gourmetquest;
import  oop.gourmetquest.Model.*;
import  oop.gourmetquest.DataHandler.*;

import java.util.*;


public class GourmetQuest {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Produced produced = new Produced();

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
                List<Recipes> listOfResults = FileHandler.SearchingName(Search);
                if (listOfResults.isEmpty()) {
                    App.clearScreen();
                    System.out.println("Food not found!\n");  
                }   
                else {
                    System.out.println("Found it: ");
                    for(Recipes Item : listOfResults){
                        System.out.println("-".repeat(150));
                        Item.DisplayFood();
                    } 
                }  
            }
        }
    }
    void IngredientsSearch(){
        // System.out.println();
        while (true) {
            App.clearScreen();
            System.out.println("Summon Food Through Ingredients: ");
            System.out.println();
            System.out.print("Choose Your Path: ");
            String ingredientsPick = scanner.nextLine();
            switch (ingredientsPick) {
                case "1":
                    produced.gatherIngredient();
                case "2":

                    break;
                case "4":

                    break;

                case "5":

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
