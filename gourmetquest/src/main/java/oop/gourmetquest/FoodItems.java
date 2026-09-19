package oop.gourmetquest;

import java.util.ArrayList;
import java.util.List;

public class FoodItems {

    public  String Name;
    public  String url;
    public  String Author;
    public  ArrayList<String> Ingredients;
    public  String Description;
    public  ArrayList<String> Method;

    FoodItems(){}


    FoodItems(String Name, String url, ArrayList<String> Ingredients, String Description, String Author, ArrayList<String> Method){
        this.Name = Name;
        this.url = url;
        this.Author = Author;
        this.Description = Description;
        this.Ingredients = Ingredients;
        this.Method = Method;
    }


    // Getters
    public String getName(){
        return Name;
    }
    public String getUrl(){
         return url;
    }
    public List<String> getIngredients(){
        return Ingredients;
    }
    public String getDescription(){
        return Description;
    }
    public String getAuthor(){
        return Author;
    }
    public ArrayList<String> getMethod(){
        return Method;
    }





    
}

