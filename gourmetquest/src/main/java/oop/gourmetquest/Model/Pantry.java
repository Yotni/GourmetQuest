package oop.gourmetquest.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import oop.gourmetquest.App;

public class Pantry extends FoodCategories{

    public static final Scanner scanner = new Scanner(System.in);

    //https://ospi.k12.wa.us/sites/default/files/2023-08/vegetablesa-z.pdf:
    public static List<String> listOfGrains= List.of(
                                        );

    public static List<String> listOfCannedGoods = List.of("Apple", "Mango", "Pomegranate", "Coconut", " Avocado", "Lemon", "Orange", "Pineapple", "Banana", "Cherry",
                                                    "Berries", "Papaya", "Jackfruit", "Pears"
                                                    );                                    
    // "malagkit and glutinpous rice is same jsut fo langauge barrier"
    public static List<String> listOfBakingStaples = List.of("White Bread", "Flatbreads", "Sourdough", "Cornbread", "rye bread", "Bagel", "Pita", "Long Grain White Rice", "Medium-Grain Rice",
                                                    "Short-Grain Rice", " Whole Grains", "Glutinous rice", "malagkit", "All-Purpose Flour", "Bread Flour", "Cake Flour", "Pastry Flour", "Whole Wheat Flou",
                                                    ""
                                                    );      




    List<String> Grains;
    List<String> CannedGoods;
    List<String> BakingStaples;
    
    public Pantry () {
        this.Grains = new ArrayList<> ();
        this.CannedGoods = new ArrayList<> ();
        this.BakingStaples = new ArrayList<> ();
    }

    @Override
    public void gatherIngredients(){
        // Guys pwede pala toh sa java hahahahhahaha ang galing
        App.clearScreen();
        Produced: while (true) {
            System.out.println("What do you want to gather? [X to Exit]");
            System.out.println("[1] Grains \n[2] Canned Goods \n[3] Baking Staples");
            System.out.print("Choose your field:");
            String producedPath = scanner.nextLine();

            switch (producedPath.toLowerCase()) {
                case "1":
                    CategoriesPrintFormat(listOfGrains);
                    ingredientList = listOfGrains;
                    break;
                case "2":
                    CategoriesPrintFormat(listOfCannedGoods);
                    ingredientList = listOfCannedGoods;
                    break;
                case "3":
                    CategoriesPrintFormat(listOfBakingStaples);
                    ingredientList = listOfBakingStaples;
                    break;
                case "x":
                    break Produced;
                default:
                    App.handleError();
                    continue;
            }
            gatheringIngredients (producedPath, ingredientList);
            App.clearScreen();
        }
            
    } 
    
    @Override 
    protected boolean isduplicate (String ingredient) {
        if (Grains.contains(ingredient) || CannedGoods.contains(ingredient) || BakingStaples.contains(ingredient)) {
            return true;
        }
        return false;
    }

    @Override 
    protected void appendtoList (String ingredientPath, String ingredient) {
        if (ingredientPath.equals("1")) {
            Grains.add(ingredient);
        }
        else if (ingredientPath.equals("2")) {
            CannedGoods.add(ingredient);
        }
        else if (ingredientPath.equals("3")) {
            BakingStaples.add(ingredient);
        }
        System.out.println ("The ingredient " + ingredient + " is added on the list");
    }
    
    public List<String> getGrains () {
        return Grains;
    }

    public List<String> getCannedGoods () {
        return CannedGoods;
    }
    public List<String> getBakingStaples () {
        return BakingStaples;
    }
    
}
