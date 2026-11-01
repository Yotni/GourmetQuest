package oop.gourmetquest;
import  oop.gourmetquest.Model.*;
import  oop.gourmetquest.DataHandler.*;

import java.util.*;
import java.lang.*;


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
                    removedIngredients();
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
            App.clearScreen();
            System.out.println("-".repeat(150));
            System.out.print("Search[X to Exit]: ");
            String Search = scanner.nextLine();

            if (Search.equalsIgnoreCase("X"))
                return;
            else{
                List<Recipes> listOfResults = FileHandler.SearchingName(Search);
                if (listOfResults.isEmpty()) {
                    System.out.println("Food not found!\n");  
                }   
                else {
                    System.out.println("Found " + listOfResults.size() + " recipes for " + Search);
                    OutputAndSaveItem(listOfResults);
                }  
            }
        }
    }

    private void removedIngredients () {
        App.clearScreen();
        while (true) {
            System.out.println("Remove an ingredient from which category[X to Exit]: ");
            System.out.println("[1] Produced");
            System.out.println("[2] Meat Products");
            System.out.println("[3] Herb and Spices");
            System.out.println("[4] Oil and Condiments");
            System.out.println("[5] Dairy");
            System.out.print("Choose Your Path: ");

            String ingredientsPick = scanner.nextLine();

            if (ingredientsPick.equalsIgnoreCase("1")) {
                System.out.println("Your Ingredients in Produced: ");
                List<String> Basket1 = produced.getVegetables();
                List<String> Basket2 = produced.getFruits();
                List<String> totalBasketsByCategory = combineBaskets(Basket1, Basket2);
                FoodCategories.CategoriesPrintFormat(totalBasketsByCategory);
            }
            else if (ingredientsPick.equalsIgnoreCase("2")) {
                System.out.println("Your Ingredients in Meats: ");
                List<String> Basket3 = meatProduct.getLandMeats();
                List<String> Basket4 = meatProduct.getFishes();
                List<String> Basket5 = meatProduct.getProcessedMeats();
                List<String> totalBasketsByCategory = combineBaskets(Basket3, Basket4, Basket5);
                FoodCategories.CategoriesPrintFormat(totalBasketsByCategory);
            }
            else if (ingredientsPick.equalsIgnoreCase("3")) {
                System.out.println("Your Ingredients in Herbs and Spices: ");
                List<String> Basket6 = herbSpices.getSpices();
                List<String> Basket7 = herbSpices.getHerbs();
                List<String> totalBasketsByCategory = combineBaskets(Basket6, Basket7);
                FoodCategories.CategoriesPrintFormat(totalBasketsByCategory);
            }
            else if (ingredientsPick.equalsIgnoreCase("4")) {
                System.out.println("Your Ingredients in Condiments and Sauce: ");
                List<String> Basket8 = oilCondiments.getCondiments();
                List<String> Basket9 = oilCondiments.getSauces();
                List<String> Basket10 = oilCondiments.getOils();
                List<String> totalBasketsByCategory = combineBaskets(Basket8, Basket9, Basket10);
                FoodCategories.CategoriesPrintFormat(totalBasketsByCategory);
            }
            else if (ingredientsPick.equalsIgnoreCase("4")) { 
                System.out.println("Your Ingredients in Dairy: ");
                List<String> Basket11 = dairy.getMilks();
                List<String> Basket12= dairy.getCheeses();
                List<String> Basket13 = dairy.getCreams();
                List<String> Basket14 = dairy.getButters();
                List<String> totalBasketsByCategory = combineBaskets(Basket11, Basket12, Basket13, Basket14);
                FoodCategories.CategoriesPrintFormat(totalBasketsByCategory);
            }
        }
    }







    //filteringredients methods:
    private void applyFilters() {
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

                    OutputAndSaveItem(listOfResults);
                }
            }
        }



    }

    // for putting recipes and deciding if to save or not
    private String OutputAndSaveItem (List<Recipes> listOfResults) {
        while (true) {
            FileHandler.outputRecipes(listOfResults);
            
            System.out.println("-".repeat(30));
            System.out.print("Enter a number to view the recipe or type S to save to save a recipe[X to Exit]: ");
            String PickARecipe = scanner.nextLine();
            if (PickARecipe.equalsIgnoreCase("X")) {
                return "";
            }
            else if (PickARecipe.equalsIgnoreCase("S")) {
                SavingRecipes(listOfResults);
            }
            else {
                try {
                    Recipes selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                    System.out.println("-".repeat(150));
                    selectedItem.DisplayFood(); 
                    System.out.println("-".repeat(150));
                } catch (IndexOutOfBoundsException  e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }             
        }
    }

    private void SavingRecipes (List<Recipes> listOfResults) {
        Recipes selectedItem;
        while (true) {
            System.out.print("Which Recipe do you want to save [X to Exit]: ");
            String PickARecipe = scanner.nextLine();

            if (PickARecipe.equalsIgnoreCase("x")) {
                break;
            }
            else {
                try {
                    selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                    FileHandler.serialize(selectedItem);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
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
    @SafeVarargs
    public final List<String> combineBaskets(List<String>... baskets) {

    List<String> totalBasketsByCategory  = new ArrayList<>();

    for (List<String>  basket : baskets) {
        for (String player : basket) {
            totalBasketsByCategory.add(player);
        }
    }

    return totalBasketsByCategory;
}
}
