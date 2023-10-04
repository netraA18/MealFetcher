import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a dish: ");
            String dish = scanner.nextLine();
            String mealResponse = MealAPIUtil.getMealByName(dish);

            if (mealResponse.startsWith("error")) {
                System.out.println(mealResponse);
            } else {
                // Parse the JSON response
                /*
                 * mealObject contains an array of meals, so the code extracts
                 * this array using mealObject.get("meals") and stores it in the
                 * mealsArray
                 */
                JSONParser parser = new JSONParser();
                JSONObject mealObject = (JSONObject) parser.parse(mealResponse);

                // Extract and print the meal details
                /*
                 * checks if the mealsArray is not null and not empty so like if
                 * there are meals in the array, it extracts the first meal's details
                 */
                JSONArray mealsArray = (JSONArray) mealObject.get("meals");
                JSONObject meal;
                for (int i = 0; i < mealsArray.size(); i++) {
                    if (mealsArray != null && !mealsArray.isEmpty()) {
                        meal = (JSONObject) mealsArray.get(i);
                        String mealName = (String) meal.get("strMeal");
                        String mealCategory = (String) meal.get("strCategory");
                        String mealInstructions = (String) meal.get("strInstructions");
                        String youTube = (String) meal.get("strYoutube");

                        String[] sentences = mealInstructions.split("\\.");

                        System.out.println("Meal Name:\t\t " + mealName + "\n");
                        System.out.println("Category:\t\t " + mealCategory+ "\n");
                        System.out.println("Youtube:\t\t " + youTube + "\n");
                        System.out.println("Instructions:"+ "\n");
                        
                        

                        for (int j = 1; j < sentences.length; j++) {
                            System.out.println(j + ") " + sentences[j].trim());

                        }
                        System.out.println();

                        System.out.println("Measurements and Ingredients:\n ");
                        for (int m = 1; m <= 20; m++) {
                            String mealMeasurements = (String) meal.get("strMeasure" + m);
                            String mealIngredients = (String) meal.get("strIngredient" + m);
                            if (mealMeasurements != null && !mealMeasurements.trim().isEmpty() && mealIngredients != null && !mealIngredients.trim().isEmpty()) {
                                System.out.println(" ~ " + mealMeasurements + " of " + mealIngredients);
                            }
                        }
                        System.out.println();

                        
                        

                    } else {
                        System.out.println("No meal data found for " + dish);
                        
                    }

                }

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
