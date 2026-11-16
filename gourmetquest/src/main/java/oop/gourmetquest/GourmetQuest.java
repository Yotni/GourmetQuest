package oop.gourmetquest;
import  oop.gourmetquest.Model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GourmetQuest {

    public static final Scanner scanner = new Scanner(System.in);
    private static final File FilePathSaving = new File("C:gourmetquest\\src\\main\\Data\\SavedRecipes.json");
    
    private Produced produced;
    private MeatProduct meatProduct;
    private HerbSpices herbSpices;
    private Dairy dairy;
    private OilCondiments oilCondiments;
    private Pantry pantry;

    private FileHandler fileHandler;

    public GourmetQuest (Produced produced, MeatProduct meatProduct, HerbSpices herbSpices, Dairy dairy, OilCondiments oilCondiments, Pantry pantry, FileHandler fileHandler) {
        this.produced = produced;
        this.meatProduct = meatProduct;
        this.herbSpices = herbSpices;
        this.dairy = dairy;
        this.oilCondiments = oilCondiments;
        this.pantry = pantry;
        this.fileHandler = fileHandler;
    }

    void SearchName(){
        App.clearScreen();
        while (true) {
            System.out.print("Search[X to Exit]: ");
            String Search = scanner.nextLine();

            if (Search.equalsIgnoreCase("X"))
                return;
            else{
                List<Recipes> listOfResults = FileHandler.SearchingName(Search);
                if (listOfResults.isEmpty()) {
                    App.clearScreen();
                    System.out.println("Food not found!");
                    System.out.println("-".repeat(150));
                }   
                else {
                    OutputAndSaveItem(listOfResults, Search);
                    App.clearScreen();
                }  
            }
        }
    }

    void filterIngredients(){
        App.clearScreen();
        ingredientSearch: while (true) {
            System.out.println("Total Grabbed Ingredients: " + fileHandler.getTotalIngredients().size());
            System.out.println("Search recipes through filtering[X to Exit]: ");
            System.out.println("[1] Produced");
            System.out.println("[2] Meat Products");
            System.out.println("[3] Herb and Spices");
            System.out.println("[4] Oil and Condiments");
            System.out.println("[5] Dairy");
            System.out.println("[6] Pantry");
            System.out.println("[7] Removed an Ingredients");
            System.out.println("[8] Apply Filters");
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
                    pantry.gatherIngredients();
                    break;

                case "7":
                    removedIngredients();
                    break;

                case "8":
                    applyFilters();
                    break;

                case "x":
                    break ingredientSearch;
            
                default:
                    App.invalidInput();
                    continue;
            }
            App.clearScreen();
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
            System.out.println("[6] Pantry");
            System.out.print("Choose Your Path: ");

            String ingredientsPick = scanner.nextLine();

            if (ingredientsPick.equalsIgnoreCase("x")) {
                break;
            }

            List<String> totalBasketsByCategory = null;
            toRemoved : while (true) {
                if (Integer.parseInt(ingredientsPick) > 1 || Integer.parseInt(ingredientsPick) < 4 ) {
                    totalBasketsByCategory = fileHandler.ouputIngredientsToRemoved(ingredientsPick);
                }
                else {
                    App.invalidInput();
                    break toRemoved;
                }
                App.clearScreen();
                while (true) { 
                    if (ingredientsPick.equals("1")) {
                        System.out.println("Your Ingredients in Produced: ");
                    } 
                    else if (ingredientsPick.equals("2")) {
                        System.out.println("Your Ingredients in Meats: ");
                    }
                    else if (ingredientsPick.equals("3")) {
                        System.out.println("Your Ingredients in Herbs and Spices: ");
                    }

                    else if (ingredientsPick.equals("4")) {
                        System.out.println("Your Ingredients in Condiments and Sauce: ");
                    }

                    else if (ingredientsPick.equals("5")) {
                        System.out.println("Your Ingredients in Dairy: ");
                    }  
                    else if (ingredientsPick.equals("6")) {
                        System.out.println("Your Ingredients in Pantry: ");
                    }  

                    FoodCategories.CategoriesPrintFormat(totalBasketsByCategory);
                    System.out.println();
                    System.out.println("-".repeat(150));
                    System.out.print("Which ingredient do you want to removed[X to Exit]: ");
                    String toRemoved = scanner.nextLine();
                    if (toRemoved.equalsIgnoreCase("x")) {
                        App.clearScreen();
                        break toRemoved;
                    }

                    if (totalBasketsByCategory.isEmpty()) {
                        App.clearScreen();
                        System.out.println("Cannot removed ingredients since basket is empty!");
                        System.out.println("-".repeat(150));
                        continue;
                    }

                    String validatedIngredient  = FoodCategories.GetIngredients(toRemoved, totalBasketsByCategory);

                    if (validatedIngredient.isEmpty()) {
                        App.clearScreen();
                        System.out.println ("The ingredient " + toRemoved + " is not on the list");
                        System.out.println("-".repeat(150));
                    }
                    else {
                        fileHandler.executeRemovalOfIngredients(ingredientsPick, validatedIngredient);
                        break;
                    }    
                }    
            }
            
            
        }
    }

    //filteringredients methods:
    private void applyFilters() {
        App.clearScreen();
        filtering : while (true) {
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
                List<String> AllIngredients = fileHandler.getTotalIngredients();
                switch (filterPath) {
                    case "1":
                        listOfResults = FileHandler.filteringEveryItems(AllIngredients);
                        break;
                    case "2":
                        listOfResults = FileHandler.filteringAtLeast1(AllIngredients);
                        break;
                
                    default:
                        App.invalidInput();
                        continue;
                }

                if (listOfResults.isEmpty()) {
                    App.clearScreen();
                    System.out.println("Food not found!");
                    System.out.println("-".repeat(150));  
                }   
                else {
                    OutputAndSaveItem(listOfResults, filterPath);
                    App.clearScreen();
                }
            }
        }



    }
    // this method is also same as the on ein save
    public static void AddToDoList(List<Recipes> listOfResults, File FilePathToDoList) {
        App.clearScreen();
        while (true) {
            System.out.println("Saved list you might want to do: ");
            FileHandler.outputRecipes(listOfResults);

            System.out.println("-".repeat(150));
            System.out.print("Which Recipe do you want to add in To Do List [X to Exit]: ");
            String PickARecipe = scanner.nextLine();

            if (PickARecipe.equalsIgnoreCase("x")) {
                break;
            }
            else {
                try {
                    Recipes selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                    FileHandler.serialize(selectedItem, FilePathToDoList);
                } catch (IndexOutOfBoundsException e) {
                    App.handleException(e);
                } catch (InputMismatchException e) {
                    App.handleException(e);
                } catch (NumberFormatException e) {
                    App.handleException(e);
                } catch (Exception e) {
                    App.handleException(e);
                }    
                
            }
        }
    }

    // for putting recipes and deciding if to save or not
    private String OutputAndSaveItem (List<Recipes> listOfResults, String pathOrSearch) {
        App.clearScreen();
        while (true) {
            if (pathOrSearch.equals("1")) {
                System.out.println("Found " + listOfResults.size() + " recipes for All Ingredients");
            }
            else if (pathOrSearch.equals("2")) {
                System.out.println("Found " + listOfResults.size() + " recipes for Any Ingredients");
            }
            else{
                System.out.println("Found " + listOfResults.size() + " recipes for " + pathOrSearch);
            }

            FileHandler.outputRecipes(listOfResults);
            
            System.out.println("-".repeat(30));
            System.out.print("Enter a number to view the recipe or type S to save to save a recipe[X to Exit]: ");
            String PickARecipe = scanner.nextLine();
            if (PickARecipe.equalsIgnoreCase("X")) {
                return "";
            }
            else if (PickARecipe.equalsIgnoreCase("S")) {
                SavingRecipes(listOfResults);
                App.clearScreen();
            }
            else {
                try {
                    Recipes selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                    System.out.println("-".repeat(150));
                    selectedItem.DisplayFood();
                    App.clearScreen();
                } catch (NumberFormatException e) {
                    App.handleException(e);
                } catch (IndexOutOfBoundsException  e) {
                    App.handleException(e);
                } catch (InputMismatchException e) {
                    App.handleException(e);
                } catch (Exception e) {
                    App.handleException(e);
                }
            }             
        }
    }

    public static void deleteList(String PickARecipe, List<Recipes> listOfResults, String CallerType) {
        App.clearScreen();
        while (true) {
            
            Recipes selectedItem = null;

            if (CallerType.equalsIgnoreCase("SavedRecipes")) {
                System.out.println("Your saved recipes to delete: ");
                FileHandler.outputRecipes(listOfResults);
                System.out.println("-".repeat(150));
                System.out.print("Select a recipe to delete: ");
                PickARecipe = scanner.nextLine();
                try {
                    selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                } catch (IndexOutOfBoundsException e) {
                    App.handleException(e);
                    continue;
                }  catch (NumberFormatException e) {
                    App.handleException(e);
                    continue;
                } catch (Exception e) {
                    App.handleException(e);
                    continue;
                }

                System.out.println("-".repeat(150));
                System.out.print("Do you want to remove " + selectedItem.getName() + " from Saved Recipes[Y/X]: ");
            }
            else if (CallerType.equalsIgnoreCase("toDoList")) {
                selectedItem = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                selectedItem.DisplayFood();
                System.out.println("-".repeat(150));
                System.out.print("Do you want to remove " + selectedItem.getName() + " from to do list[Y/X]: ");
            }
            
            String toRemoved = scanner.nextLine();
            if (toRemoved.equalsIgnoreCase("x")) {
                break;
            }
            else if(toRemoved.equalsIgnoreCase("y")) {
                try {
                    FileHandler.removedARecipe(selectedItem, listOfResults, CallerType);
                } catch (IOException e) {
                    App.handleException(e);
                } catch (Exception e) {
                    App.handleException(e);
                } 
            }
            else {
                App.invalidInput();
            }       
            
            App.clearScreen();
        }
        
    }

    private void SavingRecipes (List<Recipes> listOfResults) {
        App.clearScreen();
        while (true) {
            System.out.println("Recipe List to Saved: ");
            FileHandler.outputRecipes(listOfResults);

            System.out.println("-".repeat(150));
            System.out.print("Which Recipe do you want to save [X to Exit]: ");
            String PickARecipe = scanner.nextLine();

            if (PickARecipe.equalsIgnoreCase("x")) {
                break;
            }
            else {
                try {
                    Recipes selectedRecipe = listOfResults.get(Integer.parseInt(PickARecipe) - 1);
                    FileHandler.serialize(selectedRecipe, FilePathSaving);
                } catch (IndexOutOfBoundsException e) {
                    App.handleException(e);
                } catch (InputMismatchException e) {
                    App.handleException(e);
                } catch (Exception e) {
                    App.handleException(e);
                }    
                
            }
        }

    }

}
