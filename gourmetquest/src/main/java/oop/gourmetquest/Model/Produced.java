package oop.gourmetquest.Model;

import java.util.*;

public class Produced extends FoodCategories {

    public static final Scanner scanner = new Scanner(System.in);

    //https://ospi.k12.wa.us/sites/default/files/2023-08/vegetablesa-z.pdf:
    public static List<String> VegetablesList= List.of("Artichoke", "Avocado", "Broccoli", "Brussels Sprouts", "Cabbage",
                                        "Savoy Cabbage", "Carrot", "Cauliflower", "Celeriac", "Celery", "Chard", "Chicory",
                                        "Corn", "Cress", "Cucumber", "Garlic", "Green Bean", "Kale", "Leek", "Lettuce", 
                                        "Mushroom", "Onion", "Parsnip", "Peas", "Pepper", "Potato", "Pumpkin", "Radicchio",
                                        "Radish", "Rhubarb", "turnip", "Shallots", "Spinach", "Squash", "Swede", "Sweet Potato",
                                        "Tomato", "Chestnut"
                                        );


    List<String> Vegetables;
    List<String> Fruits;
    



    Produced (List<String> Vegetables, List<String> Fruits) {
        this.Vegetables = Vegetables;
        this.Fruits = Fruits;
    }


    @Override
    public void PickMethod(String ProducedPath){
        if(ProducedPath.equals("1")){
            System.out.println("Vegetables: ");
            CategoriesPrintFormat(VegetablesList);
            


        }

        
    }

}
