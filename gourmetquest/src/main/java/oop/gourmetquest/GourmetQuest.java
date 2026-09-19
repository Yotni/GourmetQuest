package oop.gourmetquest;

import java.util.Scanner;


public class GourmetQuest {
    public static final Scanner scanner = new Scanner(System.in);

    FoodHandler foodhHandler = new FoodHandler();

    String Path;

    GourmetQuest(String Path){
       this.Path = Path;
    }

    void Searchquisine(){
        App.clearScreen();
        System.out.println("Find a Food that will make you feel the power [X to Exit]: ");
        System.out.println();
        System.out.print("Search: ");
        String Search = scanner.nextLine();

        if (Search.equalsIgnoreCase("X"))
            return;
        else{
            foodhHandler.SearchingName(Search);
        }
        

    }
    void IngredientsSearch(){
        // System.out.println();
        System.out.println("Summon Food Through Ingredients: ");


    }
    void FoodSummon(){
         System.out.println("Summoning your Food: ");

         
    }


}
