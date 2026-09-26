package oop.gourmetquest.Model;

import java.util.List;
import java.util.Scanner;

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


    @Override
    public void gatherIngredient() {
        Meat: while (true) {
            App.clearScreen();

            System.out.println("Time to Hunt, What do you want to butcher? [X to Exit]");
            System.out.println("[1] Land meat \n[2] Fishes [3] \nProcess meat");
            System.out.print("Choose your field:");
            String meatProductdPath = scanner.nextLine();

            switch (meatProductdPath.toLowerCase()) {
                case "1":
                    
                    break;
                case "2":

                    break;

                case "3":

                case "x":
                    break Meat;
            
                default:
                    break;
            }

        
        }
        



    }

    @Override
    protected boolean isduplicate(String ingredient) {
        return true;
    }



}
