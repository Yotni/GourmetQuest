package oop.gourmetquest.Model;
import java.util.*;

import oop.gourmetquest.App;

public class HerbSpices extends FoodCategories {
    
    public static final Scanner scanner = new Scanner(System.in);

    public static List<String> listOfHerbs= List.of("Salt");
    public static List<String> listOfSpices= List.of("Chilli Pepper");

    List<String> herbs;
    List<String> spices;


    public HerbSpices() {
        this.herbs = new ArrayList<> ();
        this.spices = new ArrayList<> ();
    }


    @Override 
    public void gatherIngredients() {
        HerbSpices : while (true) {
            App.clearScreen();
            System.out.println("Gather Herbs and Spices! [X to Exit]");
            System.out.println("[1] Herbs \n[2] Spices");
            System.out.print("Choose your Herb and Spices:");
            String herbsSpicesPath = scanner.nextLine();

            switch (herbsSpicesPath.toLowerCase()) {
                case "1":
                    CategoriesPrintFormat(listOfHerbs);
                    ingredientList = listOfHerbs;
                    break;
                case "2":
                    CategoriesPrintFormat(listOfSpices);
                    ingredientList = listOfSpices;
                    break;
                case "x":
                    break HerbSpices;
                default:
                    continue;
            }
            gatheringIngredients (herbsSpicesPath, ingredientList);
        }
    }

    @Override 
    protected boolean isduplicate (String ingredient) {
        if (herbs.contains(ingredient) || spices.contains(ingredient)) {
            return true;
        }
        return false;
    }

    @Override
    protected void appendtoList (String ingredientPath, String ingredient) {
        if (ingredientPath.equals("1")) {
            herbs.add(ingredient);
        }
        else {
            spices.add(ingredient);
        }
        System.out.println ("The ingredient " + ingredient + " is added on the list");
    }


    //getters methods
    public List<String> getHerbs () {
        return herbs;
    }

    public List<String> getSpices () {
        return spices;
    }

}
