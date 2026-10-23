package oop.gourmetquest;
import  oop.gourmetquest.Model.*;
import  oop.gourmetquest.DataHandler.*;

import java.util.*;


public class GourmetQuest {

    public static final Scanner scanner = new Scanner(System.in);
    public static final Produced produced = new Produced();
    public static final MeatProduct meatProduct = new MeatProduct();
    public static final HerbSpices herbSpices = new HerbSpices();
    public static final Dairy dairy = new Dairy();
    public static final OilCondiments oilCondiments = new OilCondiments();

    void filterIngredients(){
        // System.out.println();
        ingredientSearch: while (true) {
            App.clearScreen();
            System.out.println("Total Grabbed Ingredients: " + getTotalIngredients().size());
            System.out.println("Search recipes through filtering[X to Exit]: ");
            System.out.println("[1] Produced");
            System.out.println("[2] Meat Products");
            System.out.println("[3] Herb and Spices");
            System.out.println("[4] Oil and Condiments");
            System.out.println("[5] Dairy");
            System.out.println("[6] Removed an Ingredients");
            System.out.println("[7] Apply Filters");
            System.out.print("Choose Your Path: ");

            String ingredientsPick = scanner.nextLine();
            switch (ingredientsPick.toLowerCase()) {
                case "1":
                    produced.gatherIngredients();
                    break;
                case "2":
                    meatProduct.gatherIngredients();
                    break;
                case "3":
                    herbSpices.gatherIngredients();
                    break;
                case "4":
                    oilCondiments.gatherIngredients();
                    break;

                case "5":
                    dairy.gatherIngredients();
                    break;

                case "6":

                    break;

                case "7":
                    applyFilters();
                    break;

                case "x":
                    break ingredientSearch;
            
                default:
                    break;
            }
        }
    }   
    
    void SearchName(){
        while (true) {
            System.out.println("-".repeat(150));
            System.out.print("Search[X to Exit]: ");
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
                    System.out.println("Found " + listOfResults.size() + " recipes for " + Search);
                    OutpurAndSaveItem(listOfResults);
                }  
            }
        }
    }
    //filteringredients methods:
    protected void applyFilters() {
        filtering : while (true) {
            App.clearScreen();
            System.out.println("How Do you want to filter[X to Exit]: ");
            System.out.println("[1] Match All -- must have every ingredient ");
            System.out.println("[2] Match Any -- at least one ingredient ");
            System.out.print("Choose Your Path: ");
            String filterPath = scanner.nextLine();
            if (filterPath.equalsIgnoreCase("x")) {
                break filtering;
            }
            
            else {
                List<Recipes> listOfResults = new ArrayList<> ();
                List<String> AllIngredients = getTotalIngredients();
                switch (filterPath) {
                    case "1":
                        listOfResults = FileHandler.filteringEveryItems(AllIngredients);
                        break;
                    case "2":
                        listOfResults = FileHandler.filteringAtLeast1(AllIngredients);
                        break;
                
                    default:
                        break;
                }

                if (listOfResults.isEmpty()) {
                    App.clearScreen();
                    System.out.println("Food not found!\n");  
                }   
                else {
                    if (filterPath.equals("1")) {
                        System.out.println("Found " + listOfResults.size() + " recipes for All Ingredients");
                    }
                    else if (filterPath.equals("2")) {
                        System.out.println("Found " + listOfResults.size() + " recipes for Any Ingredients");
                    }

                    OutpurAndSaveItem(listOfResults);
                }
            }
        }



    }

    // put every ingredients to one list only for filtering and counting
    private List<String> getTotalIngredients() {
        List<String> Basket1 = produced.getVegetables();
        List<String> Basket2 = produced.getFruits();
        List<String> Basket3 = meatProduct.getLandMeats();
        List<String> Basket4 = meatProduct.getFishes();
        List<String> Basket5 = meatProduct.getProcessedMeats();
        List<String> Basket6 = herbSpices.getSpices();
        List<String> Basket7 = herbSpices.getHerbs();
        List<String> Basket8 = oilCondiments.getCondiments();
        List<String> Basket9 = oilCondiments.getSauces();
        List<String> Basket10 = oilCondiments.getOils();
        List<String> Basket11 = dairy.getMilks();
        List<String> Basket12= dairy.getCheeses();
        List<String> Basket13 = dairy.getCreams();
        List<String> Basket14 = dairy.getButters();

        
        List<List<String>> allIngredients = List.of(Basket1, Basket2, Basket3, Basket4, Basket5, 
                                            Basket6, Basket7, Basket8, Basket9, Basket10, 
                                            Basket11, Basket12, Basket13, Basket14);

        List<String> TotalBasket = new ArrayList<>();

        for (List<String> ingredient : allIngredients) {
            TotalBasket.addAll(ingredient);
        }

        return TotalBasket;
    
    }
    // for putting recipes and deciding if to save or not
    private String OutpurAndSaveItem (List<Recipes> listOfResults) {
        while (true) {
            int count = 1;
            for(Recipes Item : listOfResults){
                System.out.println("-".repeat(150));
                System.out.println(count + " -- " + Item.getName());
                count++;
            } 
            System.out.println("-".repeat(30));
            System.out.print("Enter a number to view the recipe or type S to save to save a recipe[X to Exit]: ");
            String PickARecipe = scanner.nextLine();
            if (PickARecipe.equalsIgnoreCase("X")) {
                return "";
            }
            else if (PickARecipe.equalsIgnoreCase("S")) {
                return "S";
            }
            else {
                Recipes selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                System.out.println("-".repeat(150));
                selectedItem.DisplayFood();
            }                        
        }
    }
}
