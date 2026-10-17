package oop.gourmetquest.Model;

import java.util.*;

import oop.gourmetquest.App;

public class MeatProduct extends FoodCategories{

    Scanner scanner = new Scanner(System.in);

    //https://www.onlyfoods.net/category/meat
    public static List<String> ListOfLandMeats = List.of ("Pork", "Beef", "Veal", "Mutton", "Lamb", "Chicken", "Duck",
                                                        "Turkey", "Goose", "Pheasant");
                                                        
    //https://www.onlyfoods.net/category/meat
    public static List<String> ListOfFishes = List.of ("Salmon", "Lobster", "Crab", "Prawn",  "MusselS", "Scallops", "Oysters", 
                                                        "Cod", "Fishfillet", "Anchovies", "Haddock","Trout", "Bass", "Mackerel");
    //https://www.onlyfoods.net/cured-meats
    public static List<String> ListOfProcessedMeats = List.of("Tuna", "Bacon", "Salami", "Sausage", "chorizo");

    List<String> landMeats;
    List<String> fishes;
    List<String> processedMeats;

    public MeatProduct() {
        this.landMeats = new ArrayList<> ();
        this.fishes = new ArrayList<> ();
        this.processedMeats = new ArrayList<> ();

    }

    @Override
    public void gatherIngredients() {
        Meat: while (true) {
            App.clearScreen();

            System.out.println("What do you want to butcher? [X to Exit]");
            System.out.println("[1] Land meat \n[2] Fishes [3] \nProcess meat");
            System.out.print("Choose your field:");
            String meatProductPath = scanner.nextLine();

            switch (meatProductPath.toLowerCase()) {
                case "1":
                    CategoriesPrintFormat(ListOfLandMeats);
                    ingredientList = ListOfLandMeats;
                    break;
                case "2":
                    CategoriesPrintFormat(ListOfFishes);
                    ingredientList = ListOfFishes;
                    break;
                case "3":
                    CategoriesPrintFormat(ListOfProcessedMeats);
                    ingredientList = ListOfProcessedMeats;
                    break;
                case "x":
                    break Meat;
            
                default:
                    continue;
            }
            gatheringIngredients (meatProductPath, ingredientList);
        }
    }

    @Override
    protected boolean isduplicate(String ingredient) {
        if (landMeats.contains(ingredient) || fishes.contains(ingredient) || processedMeats.contains(ingredient)) {
            return true;
        }
        return false;
    }

    @Override
    protected void appendtoList (String ingredientPath, String ingredient) {
        if (ingredientPath.equals("1")) {
            landMeats.add(ingredient);
        }
        else if (ingredientPath.equals("2")) {
                    fishes.add(ingredient);
        }
        else {
            processedMeats.add(ingredient);
        }
        System.out.println ("The ingredient " + ingredient + " is added on the list");
    }


    // getters methods
    public List<String> getLandMeats() {
        return landMeats;
    }
    public List<String> getFishes() {
        return fishes;
    }
    public List<String> getProcessedMeats() {
        return processedMeats;
    }
}
