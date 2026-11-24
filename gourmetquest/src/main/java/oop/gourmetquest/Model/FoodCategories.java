package oop.gourmetquest.Model;

import java.util.*;


public abstract class FoodCategories {

    public static final Scanner scanner = new Scanner(System.in);
    protected List<String> ingredientList;
    
    FoodCategories () {
        ingredientList = new ArrayList<> ();
    }

    public abstract void gatherIngredients();
    protected abstract  boolean isduplicate(String ingredient);
    protected abstract void appendtoList (String ingredientPath, String ingredient);



    public void gatheringIngredients (String ingredientPath, List<String> ingredientList) {
        while (true) {
            System.out.println("-".repeat(150));
            System.out.print("Gather a Produced [X to Exit]:  ");
            String gatherIngredients = scanner.nextLine();

            if (gatherIngredients.equalsIgnoreCase("x")) {
                break;
            }
            String ingredient = validateIngredients(gatherIngredients, ingredientList);

            if (ingredient.isEmpty()){
                continue;
            }
            appendtoList(ingredientPath, ingredient);
        }
    }

    

    public String validateIngredients (String pickIngredients, List<String> ingredientList) {
        
        String ingredient = GetIngredients(pickIngredients, ingredientList);

        if (isduplicate(ingredient)) {
            System.out.println ("The ingredient " + ingredient + " is already on the list");
            return "";
        }

        if (ingredient.isEmpty()) {
            System.out.println ("The ingredient " + pickIngredients + " is not on the given list");
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


    public static void CategoriesPrintFormat(List<String> ingredientList) {
        int maxWidth = 150;
        int currentLineLength = 0;
        System.out.println("-".repeat(maxWidth));

        for (String item : ingredientList) {
            String formattedItem = item + " | ";

            if (currentLineLength + formattedItem.length() > maxWidth) {
                System.out.println();
                currentLineLength = 0;
            }

            System.out.print(formattedItem);
            currentLineLength += formattedItem.length();
        }
        System.out.println();
    }

}
