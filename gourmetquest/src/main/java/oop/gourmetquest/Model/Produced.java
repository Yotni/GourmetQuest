package oop.gourmetquest.Model;

import java.util.*;

import oop.gourmetquest.App;

public class Produced extends FoodCategories {

    public static final Scanner scanner = new Scanner(System.in);

    //https://ospi.k12.wa.us/sites/default/files/2023-08/vegetablesa-z.pdf:
    public static List<String> ListOfVegetables= List.of("Artichoke", "Avocado", "Broccoli", "Brussels Sprouts", "Cabbage",
                                        "Savoy Cabbage", "Carrot", "Cauliflower", "Celeriac", "Celery", "Chard", "Chicory",
                                        "Corn", "Cress", "Cucumber", "Garlic", "Green Bean", "Kale", "Leek", "Lettuce", 
                                        "Mushroom", "Onion", "Parsnip", "Pea", "Pepper", "Potato", "Pumpkin", "Radicchio",
                                        "Radish", "Rhubarb", "turnip", "Shallots", "Spinach", "Squash", "Swede", "Sweet Potato",
                                        "Tomato", "Chestnut"
                                        );

    public static List<String> ListOfFruits = List.of("Apple", "Sausages", "Ham");                                    


    List<String> Vegetables;
    List<String> Fruits;
    
    public Produced () {
        this.Vegetables = new ArrayList<> ();
        this.Fruits = new ArrayList<> ();
    }

    @Override
    public void gatherIngredient(){
        // Guys pwede pala toh sa java hahahahhahaha ang galing
        Produced: while (true) {
            App.clearScreen();
            System.out.println("The fields await your command, what do you want to gather? [X to Exit]");
            System.out.println("[1] Vegetables \n[2] Fruits");
            System.out.print("Choose your field:");
            String producedPath = scanner.nextLine();

            switch (producedPath.toLowerCase()) {
                case "1":
                    CategoriesPrintFormat(ListOfVegetables);
                    ingredientList = ListOfVegetables;
                    break;
                case "2":
                    CategoriesPrintFormat(ListOfFruits);
                    ingredientList = ListOfFruits;
                    break;
                case "x":
                    break Produced;
                default:
                    continue;
            }
            PickingProduced: while (true) {
                System.out.print("\nGather a Produced [X to Exit]:  ");
                String pickProduced = scanner.nextLine();
                if (pickProduced.equalsIgnoreCase("x")) {
                    break PickingProduced;
                }
                String ingredient = GetIngredients(pickProduced, ingredientList);
                if (ingredient.isEmpty()) {
                    System.out.println ("The ingredient " + pickProduced + " is not on the list");
                    continue;
                }

                boolean isduplicate = isduplicate(ingredient);
                if (isduplicate) {
                    System.out.println ("The ingredient " + pickProduced + " is already on the list");
                    continue;
                }

                if (producedPath.equals("1")) {
                    Vegetables.add(ingredient);
                }
                else {
                    Fruits.add(ingredient);
                }
                System.out.println ("The ingredient " + ingredient + " is added on the list");
            } 
        }
            
    } 
    
    @Override 
    protected boolean isduplicate (String ingredient) {
        if (Vegetables.contains(ingredient) || Fruits.contains(ingredient)) {
            return true;
        }
        return false;
    }
    
    public List<String> getVegetables () {
        return Vegetables;
    }

    public List<String> getFruits () {
        return Fruits;
    }

}
