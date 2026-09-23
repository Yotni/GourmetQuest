package oop.gourmetquest.Model;

import java.util.List;

public abstract class FoodCategories {

    protected String IngredientName;
    protected String CategoriesPath;
    // protected FoodItems getName;
    protected List<String> ingredientList;

    public abstract void PickMethod(String CategoriesPath);
    

    public static void CategoriesPrintFormat(List<String> ingredientList){
        int count = 0;
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
