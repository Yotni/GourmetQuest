package oop.gourmetquest;

import java.io.File;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

// this is the fie handler for the json we eiahter created or downloaded from the internet
public class FoodHandler {

    File FilePath = new File("C:\\Users\\ASUS\\Documents\\2nd year advance study\\OOP_GourmetQuest\\gourmetquest\\src\\main\\Data\\formatted_data.json");
    ObjectMapper mapper = new ObjectMapper();


    public List<FoodItems> Dezzsireliaze(){
        try {
            return mapper.readValue(FilePath, new TypeReference<List<FoodItems>>() {});
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public void SearchingName(String Search){

        List<FoodItems> foodItems = Dezzsireliaze();
        if (foodItems != null) {
            for (FoodItems foodItem : foodItems){
                if (Search.equalsIgnoreCase(foodItem.getName())){
                    System.out.println("Found it: ");
                    System.out.println(foodItem.getName());

                }
            }
        }
        
    }
    

    
    

}
