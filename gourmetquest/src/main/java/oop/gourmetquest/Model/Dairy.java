package oop.gourmetquest.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import oop.gourmetquest.App;

public class Dairy extends FoodCategories{

    public static final Scanner scanner = new Scanner(System.in);

    public static List<String> listOfMilks = List.of("Whole Milk", "Reduced-Fat", "Skim", "Sheep Milk", "Goat Milk", "Soy Milk", "Almond Milk", "Oat Milk", "Coconut Milk",
                                                    "Pea Milk", "Evaporated milk", "Condensed Milk", "Fresh Milk"
                                                    );
    public static List<String> listOfCheeses = List.of("Cheddar", "Mozzarella", "Gouda", "Parmesan", "Feta", "Cream Cheese", "Blue Cheese" ,"American Cheese");
    public static List<String> listOfCreams = List.of("Whipped Cream", "Light Cream", "Heavy Cream", "Clotted Cream ", "");
    public static List<String> listOfbutters = List.of("Unsalted Butter", "Cultured Butter", "Grass-Fed Butter", "Sweet Cream");

    List<String> milks;
    List<String> cheeses;
    List<String> creams;
    List<String> butters;

    
    public Dairy () {
        this.milks = new ArrayList<> ();
        this.cheeses = new ArrayList<> ();
        this.creams = new ArrayList<> ();
        this.butters = new ArrayList<> ();
    }


    @Override 
    public void gatherIngredients () {
        App.clearScreen();
        Dairy : while (true) {
            System.out.println("What do you want to gather? [X to Exit]");
            System.out.println("[1] Milks \n[2] Cheeses \n[3] Butter \n[4] Creams");
            System.out.print("Choose your field:");
            String DairyPath = scanner.nextLine();

            switch (DairyPath.toLowerCase()) {
                case "1":
                    CategoriesPrintFormat(listOfMilks);
                    ingredientList = listOfMilks;
                    break;
                case "2":
                    CategoriesPrintFormat(listOfCheeses);
                    ingredientList = listOfCheeses;
                    break;
                case "3":
                    CategoriesPrintFormat(listOfCreams);
                    ingredientList = listOfCreams;
                    break;
                case "4":
                    CategoriesPrintFormat(listOfbutters);
                    ingredientList = listOfbutters;
                    break;
                case "x":
                    break Dairy;
                default:
                    App.invalidInput();
                    continue;
            }
            gatheringIngredients (DairyPath, ingredientList);
            App.clearScreen();
        }
    }

    @Override 
    protected boolean isduplicate (String ingredient) {
        if (milks.contains(ingredient) || cheeses.contains(ingredient)
            || butters.contains(ingredient) || creams.contains(ingredient)) {
            return true;
        }
        return false;
    }

    @Override
    protected void appendtoList (String ingredientPath, String ingredient) {
        switch (ingredientPath.toLowerCase()) {
            case "1":
                milks.add(ingredient);
                break;
            case "2":
                cheeses.add(ingredient);
                break;
            case "3":
                creams.add(ingredient);
                break;
            case "4":
                butters.add(ingredient);
                break;
        
            default:
                break;
        }
        System.out.println ("The ingredient " + ingredient + " is added on the list");
    }


    // getters method
    public List<String> getMilks() {
        return milks;
    }

    public List<String> getCheeses() {
        return cheeses;
    }
    public List<String> getCreams() {
        return creams;
    }

    public List<String> getButters() {
        return butters;
    }


}



