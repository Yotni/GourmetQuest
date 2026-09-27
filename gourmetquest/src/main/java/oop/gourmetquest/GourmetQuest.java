package oop.gourmetquest;
import  oop.gourmetquest.Model.*;
import  oop.gourmetquest.DataHandler.*;

import java.util.*;


public class GourmetQuest {
    
    public static final Scanner scanner = new Scanner(System.in);
    public static final Produced produced = new Produced();
    public static final MeatProduct meatProduct = new MeatProduct();

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
    void filterIngredients(){
        // System.out.println();
        ingredientSearch: while (true) {
            App.clearScreen();
            System.out.println("Summon Food Through Ingredients: ");
            
            System.out.println("Total Grabbed Ingredients: " + getTotalIngredients());
            System.out.println();
            System.out.print("Choose Your Path: ");
            String ingredientsPick = scanner.nextLine();
            switch (ingredientsPick.toLowerCase()) {
                case "1":
                    produced.gatherIngredient();
                    break;
                case "2":
                    meatProduct.gatherIngredient();
                    break;
                case "4":

                    break;

                case "x":
                    break ingredientSearch;
            
                default:
                    break;
            }


        }

    }
    void FoodSummon(){
         System.out.println("Summoning your Food: ");

    }

    public int getTotalIngredients() {
        List<String> Basket1 = produced.getVegetables();
        List<String> Basket2 = produced.getFruits();
        List<String> Basket3 = meatProduct.getLandMeats();
        List<String> Basket4 = meatProduct.getFishes();
        List<String> Basket5 = meatProduct.getProcessedMeats();
        
        int TotalBasket = Basket1.size() + Basket2.size() + Basket3.size() + Basket4.size() + Basket5.size();
        return TotalBasket;
    
    }
}
