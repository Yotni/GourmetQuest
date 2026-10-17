package oop.gourmetquest.Model;

import java.util.*;


public abstract class FoodCategories {

    // protected String IngredientName;
    // protected String CategoriesPath;
    // protected FoodItems getName;

    public static final Scanner scanner = new Scanner(System.in);
    protected List<String> ingredientList;
    
    FoodCategories () {
        ingredientList = new ArrayList<> ();
    }

    public abstract void gatherIngredients();
    protected abstract  boolean isduplicate(String ingredient);
    protected abstract void appendtoList (String ingredientPath, String ingredient);



    public void gatheringIngredients (String ingredientPath, List<String> ingredientList) {
        gathering : while (true) {
            System.out.print("\nGather a Produced [X to Exit]:  ");
            String gatherHerbsSpices = scanner.nextLine();

            if (gatherHerbsSpices.equalsIgnoreCase("x")) {
                break gathering;
            }
            String ingredient = PutIngredientToList(gatherHerbsSpices, ingredientList);

            if (ingredient.isEmpty()){
                continue;
            }
            appendtoList(ingredientPath, ingredient);
        }
    }

    // method for gatheringIngredients
    

    public String PutIngredientToList (String pickIngredients, List<String> ingredientList) {
        
        String ingredient = GetIngredients(pickIngredients, ingredientList);

        if (isduplicate(ingredient)) {
            System.out.println ("The ingredient " + ingredient + " is already on the list");
            return "";
        }

        if (ingredient.isEmpty()) {
            System.out.println ("The ingredient " + pickIngredients + " is not on the list");
            return "";
        }

        return ingredient;

    }

    public static String GetIngredients (String pickProduced, List<String> ingredientsList) {
        for (String ingredient : ingredientsList){
            if (pickProduced.equalsIgnoreCase((ingredient))){
                return ingredient;
            }
        }
        return "";
    } 


    public static void CategoriesPrintFormat(List<String> ingredientList){
        int count = 0;
        System.out.println("-".repeat(150));
        for (String item : ingredientList){
            if(count != 14){
                System.out.print(item + " | ");
            }
            else{
                System.out.println();
                count = 0;
            }  
            count ++;
        }
    }

}
