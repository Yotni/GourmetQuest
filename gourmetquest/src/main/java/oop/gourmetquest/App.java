package oop.gourmetquest;

import java.util.Scanner;

public class App {
    public static final Scanner scanner = new Scanner(System.in);
    public static final GourmetQuest gourmetQuest = new GourmetQuest("Path");
    public static void main( String[] args ){
        
        while(true){
            System.out.println("Gourmet Quest: Search A food that will change your Life!");
            System.out.println("[1] Summon a Cuisine");
            System.out.println("[2] Summon Food Through Ingredients");
            System.out.println("[3] Summon the Food from your Ingedients");
            System.out.println("[4] Close the Gate");

            System.out.print("Choose Your Path: ");
            
            int FoodPath = scanner.nextInt();

            switch (FoodPath) {
                case 1:
                    gourmetQuest.Searchquisine();
                    break;
                case 2:
                    gourmetQuest.IngredientsSearch();
                    break;
                case 3:
                    gourmetQuest.FoodSummon();
                    break;
                case 4:
                    System.out.println("\nUntil we meet again traveler\n");
                    programmedClosed();
                default:
                    clearScreen();
                    invalidInput();
                    continue;

            }
            clearScreen();
        }
        


    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void invalidInput() {
        System.out.println("Though shall not pass! When you cannot even input the right way!!!\n");

    }
    public static void programmedClosed(){
        System.exit(0);
    }

    

}
