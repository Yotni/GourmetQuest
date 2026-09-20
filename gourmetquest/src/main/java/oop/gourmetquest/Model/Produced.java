package oop.gourmetquest.Model;

import java.util.*;

public class Produced extends FoodCategories {

     public static final Scanner scanner = new Scanner(System.in);

    List<String> Veegetables;
    List<String> Fruits;



    Produced (List<String> Veegetables, List<String> Fruits) {
        this.Veegetables = Veegetables;
        this.Fruits = Fruits;
    }


    @Override
    public void PickMethod(){
        System.out.println("Hotsdog");
        
    }

}
