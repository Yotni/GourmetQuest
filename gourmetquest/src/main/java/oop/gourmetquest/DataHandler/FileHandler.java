package oop.gourmetquest.DataHandler;
import  oop.gourmetquest.Model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class FileHandler {

    File FilePath = new File("C:\\Users\\ASUS\\Documents\\2nd year advance study\\GourmetQuest\\gourmetquest\\src\\main\\Data\\formatted_data.json");
    ObjectMapper mapper = new ObjectMapper();


    public List<FoodItems> deserialize(){
        try {
            return mapper.readValue(FilePath, new TypeReference<List<FoodItems>>() {});
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public FoodItems SearchingName(String Search){

        List<FoodItems> foodItems = deserialize();
        if (foodItems != null) {
            for (FoodItems foodItem : foodItems){
                if (Search.equalsIgnoreCase(foodItem.getName())){
                    System.out.println("Found it: ");
                    return foodItem;

                }
            }
        }
        return null;
        
    }

}
