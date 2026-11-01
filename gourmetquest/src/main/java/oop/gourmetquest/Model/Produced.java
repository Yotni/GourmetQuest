package oop.gourmetquest.Model;

import java.util.*;

import oop.gourmetquest.App;

public class Produced extends FoodCategories {

    public static final Scanner scanner = new Scanner(System.in);

    //https://ospi.k12.wa.us/sites/default/files/2023-08/vegetablesa-z.pdf:
    public static List<String> listOfVegetables= List.of("Artichoke", "Broccoli", "Brussels Sprouts", "Cabbage",
                                        "Savoy Cabbage", "Carrot", "Cauliflower", "Celeriac", "Celery", "Chard", "Chicory",
                                        "Corn", "Cress", "Cucumber", "Garlic", "Green Bean", "Kale", "Leek", "Lettuce", 
                                        "Mushroom", "Onion", "Parsnip", "Pea", "Pepper", "Potato", "Pumpkin", "Radicchio",
                                        "Radish", "Rhubarb", "turnip", "Shallots", "Spinach", "Squash", "Swede", "Sweet Potato",
                                        "Tomato", "Chestnut"
                                        );

    public static List<String> listOfFruits = List.of("Apple", "Mango", "Pomegranate, Coconut", " Avocado");                                    


    List<String> Vegetables;
    List<String> Fruits;
    
    public Produced () {
        this.Vegetables = new ArrayList<> ();
        this.Fruits = new ArrayList<> ();
    }

    @Override
    public void gatherIngredients(){
        // Guys pwede pala toh sa java hahahahhahaha ang galing
        Produced: while (true) {
            App.clearScreen();
            System.out.println("What do you want to gather? [X to Exit]");
            System.out.println("[1] Vegetables \n[2] Fruits");
            System.out.print("Choose your field:");
            String producedPath = scanner.nextLine();

            switch (producedPath.toLowerCase()) {
                case "1":
                    CategoriesPrintFormat(listOfVegetables);
                    ingredientList = listOfVegetables;
                    break;
                case "2":
                    CategoriesPrintFormat(listOfFruits);
                    ingredientList = listOfFruits;
                    break;
                case "x":
                    break Produced;
                default:
                    continue;
            }
            gatheringIngredients (producedPath, ingredientList);
        }
            
    } 
    
    @Override 
    protected boolean isduplicate (String ingredient) {
        if (Vegetables.contains(ingredient) || Fruits.contains(ingredient)) {
            return true;
        }
        return false;
    }

    @Override 
    protected void appendtoList (String ingredientPath, String ingredient) {
        if (ingredientPath.equals("1")) {
            Vegetables.add(ingredient);
        }
        else {
            Fruits.add(ingredient);
        }
        System.out.println ("The ingredient " + ingredient + " is added on the list");
    }
    
    public List<String> getVegetables () {
        return Vegetables;
    }

    public List<String> getFruits () {
        return Fruits;
    }

}
