package oop.gourmetquest.Model;

import java.util.*;


import oop.gourmetquest.App;

public class OilCondiments extends FoodCategories {

    public static final Scanner scanner = new Scanner(System.in);

    public static List<String> listOfCondiments = List.of("Vinegar", "Soy Sauce", "Mayonnaise", "Mustard", "Patis", "Fish Sauce", "Bagoong", 
        "Worcestershire Sauce", "Hot Mustard", "Relish", "Pickles", "Honey", "BBQ Rub", "Mirin", "Miso Paste"
    );
    public static List<String> listOfSauces = List.of("Ketchup", "Hot Sauce", "Barbecue Sauce", "Salsa", "Tomato Sauce", "Sriracha",
        "Oyster Sauce", "Hoisin Sauce", "Teriyaki Sauce", "Sweet Chili Sauce", "Worcestershire Sauce", "Pesto", "Alfredo Sauce", "Marinara Sauce",
        "Fish Sauce", "Chili Garlic Sauce",
        "Gravy", "Cheese Sauce"
    );
    public static List<String> listOfOils = List.of( "Canola Oil", "Olive Oil", "Vegetable Oil",
        "Sunflower Oil", "Sesame Oil", "Coconut Oil", "Corn Oil", "Peanut Oil", "Soybean Oil", "Avocado Oil", "Butter", "Margarine", "Ghee"
    );


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
        App.clearScreen();
        oilCondiments : while (true) {
            System.out.println("What do you want to gather? [X to Exit]");
            System.out.println("[1] Condiments \n[2] Sauces \n[3] Oils ");
            System.out.println("-".repeat(30));
            System.out.print("Select an option: ");
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
                    break oilCondiments;
                default:
                    App.handleError();
                    continue;
            }
            gatheringIngredients (DairyPath, ingredientList);
            App.clearScreen();
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
