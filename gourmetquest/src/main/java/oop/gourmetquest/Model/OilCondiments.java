package oop.gourmetquest.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import oop.gourmetquest.App;

public class OilCondiments extends FoodCategories {

    public static final Scanner scanner = new Scanner(System.in);

    public static List<String> listOfCondiments = List.of("Soy Sauce");
    public static List<String> listOfSauces = List.of("Ketchup");
    public static List<String> listOfOils = List.of("Canolla Oil");


    List<String> condiments;
    List<String> sauces;
    List<String> oils;


    
    public OilCondiments () {
        this.condiments = new ArrayList<> ();
        this.sauces = new ArrayList<> ();
        this.oils = new ArrayList<> ();
    }


    @Override 
    public void gatherIngredients () {
        Dairy : while (true) {
            App.clearScreen();
            System.out.println("What do you want to gather? [X to Exit]");
            System.out.println("[1] Condiments \n[2] Sauces \n[3] Oils ");
            System.out.print("Choose your field:");
            String DairyPath = scanner.nextLine();

            switch (DairyPath.toLowerCase()) {
                case "1":
                    CategoriesPrintFormat(listOfCondiments);
                    ingredientList = listOfCondiments;
                    break;
                case "2":
                    CategoriesPrintFormat(listOfSauces);
                    ingredientList = listOfSauces;
                    break;
                case "3":
                    CategoriesPrintFormat(listOfOils);
                    ingredientList = listOfOils;
                    break;
                case "x":
                    break Dairy;
                default:
                    continue;
            }
            gatheringIngredients (DairyPath, ingredientList);
        }
    }

    @Override 
    protected boolean isduplicate (String ingredient) {
        if (condiments.contains(ingredient) || sauces.contains(ingredient)
            || oils.contains(ingredient)) {
            return true;
        }
        return false;
    }

    @Override
    protected void appendtoList (String ingredientPath, String ingredient) {
        switch (ingredientPath.toLowerCase()) {
            case "1":
                condiments.add(ingredient);
                break;
            case "2":
                sauces.add(ingredient);
                break;
            case "3":
                oils.add(ingredient);
                break;
            default:
                break;
        }
        System.out.println ("The ingredient " + ingredient + " is added on the list");
    }

    // getters method
    public List<String> getCondiments() {
        return condiments;
    }

    public List<String> getSauces() {
        return sauces;
    }
    public List<String> getOils() {
        return oils;
    }
}
