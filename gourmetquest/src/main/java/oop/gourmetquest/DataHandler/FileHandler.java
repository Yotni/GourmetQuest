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

    
    public static List<Recipes> deserialize(){
        try {
            return mapper.readValue(FilePath, new TypeReference<List<Recipes>>() {});
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public static List<Recipes> SearchingName(String Search){

        List<Recipes> foodItems = deserialize();
        List<Recipes> listOfResults = new ArrayList<>();
        if (foodItems != null) {
            for (Recipes foodItem : foodItems){
                if (foodItem.getName().toLowerCase().contains(Search.toLowerCase())){
                    listOfResults.add(foodItem);
                }
            }
        }
        return listOfResults;
    }

}
