package oop.gourmetquest;
import  oop.gourmetquest.Model.*;
import  oop.gourmetquest.DataHandler.*;

import java.util.*;


public class GourmetQuest {
    public static final Scanner scanner = new Scanner(System.in);

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
                List<FoodItems> ListOfResults = FileHandler.SearchingName(Search);
                if (ListOfResults.isEmpty()) {
                    App.clearScreen();
                    System.out.println("Food not found!\n");  
                }   
                else {
                    System.out.println("Found it: ");
                    for(FoodItems Item : ListOfResults){
                        System.out.println("-".repeat(30));
                        Item.DisplayFood();
                    } 
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
                    // wala pa dinie eh
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
