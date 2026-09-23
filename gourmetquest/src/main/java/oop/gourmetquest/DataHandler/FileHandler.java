package oop.gourmetquest.DataHandler;
import  oop.gourmetquest.Model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class FileHandler {

    private static final File FilePath = new File("C:\\Users\\ASUS\\Documents\\2nd year advance study\\GourmetQuest\\gourmetquest\\src\\main\\Data\\formatted_data.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    
    public static List<FoodItems> deserialize(){
        try {
            return mapper.readValue(FilePath, new TypeReference<List<FoodItems>>() {});
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public static List<FoodItems> SearchingName(String Search){

        List<FoodItems> foodItems = deserialize();
        List<FoodItems> ListOfResults = new ArrayList<>();
        if (foodItems != null) {
            for (FoodItems foodItem : foodItems){
                if (foodItem.getName().toLowerCase().contains(Search.toLowerCase())){
                    ListOfResults.add(foodItem);
                }
            }
        }
        return ListOfResults;
    }
    

}
