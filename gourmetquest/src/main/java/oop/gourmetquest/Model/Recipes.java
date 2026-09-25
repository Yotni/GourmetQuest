package oop.gourmetquest.Model;

import java.util.*;

public class Recipes {

    public  String Name;
    public  String url;
    public  String Author;
    public  List<String> Ingredients;
    public  String Description;
    
    public  List<String> Method;

    Recipes(){}


    Recipes(String Name, String url, List<String> Ingredients, String Description, String Author, List<String> Method){
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
    public List<String> getMethod(){
        return Method;
    }


    public void DisplayFood(){
        int i = 1;
        int j = 1;
        System.out.println("Name: " + this.Name);
        System.out.println("URl: " + url);
        System.out.println("Author: " + Author);
        System.out.println("-".repeat(30));
        System.out.println("Ingredients: ");
        for(String Ingedient : Ingredients){
            System.out.println(i +" - " + Ingedient);
            i++;
        }
        System.out.println("-".repeat(30));
        System.out.println("Method: " );
        for(String steps : Method){
            System.out.println("Step " + j + ":\n" +" \t- " + steps);
            j++;
        }
    }


    
}

