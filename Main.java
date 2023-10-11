import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a dish: ");
            String dish = scanner.nextLine();
            String mealResponse = MealAPIUtil.getMealByName(dish);

            if (mealResponse.startsWith("error")) {
                System.out.println(mealResponse);
            } else {
                processMealResponse(mealResponse, mealResponse);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processMealResponse(String mealResponse, String dish) {
        JSONParser parser = new JSONParser();
        JSONObject mealObject;
        JSONArray mealsArray;
        try {
            mealObject = (JSONObject) parser.parse(mealResponse);
            mealsArray = (JSONArray) mealObject.get("meals");
        } catch (ParseException e) {
            System.out.println("Error parsing JSON response.");
            return;
        }

        if (mealsArray != null && !mealsArray.isEmpty()) {
            for (int i = 0; i < mealsArray.size(); i++) {
                printMealDetails((JSONObject) mealsArray.get(i));
            }
        } else {
            System.out.println("No meal data found for " + dish);
        }
    }

    private static void printMealDetails(JSONObject meal) {
        String mealName = (String) meal.get("strMeal");
        String mealCategory = (String) meal.get("strCategory");
        String mealInstructions = (String) meal.get("strInstructions");
        String youTube = (String) meal.get("strYoutube");

        String[] sentences = mealInstructions.split("\\.");

        System.out.println("Meal Name:\t\t " + mealName + "\n");
        System.out.println("Category:\t\t " + mealCategory + "\n");
        System.out.println("Youtube:\t\t " + youTube + "\n");
        System.out.println("Instructions:" + "\n");

        for (int j = 1; j < sentences.length; j++) {
            System.out.println(j + ") " + sentences[j].trim());
        }
        System.out.println();

        System.out.println("Measurements and Ingredients:\n ");
        for (int m = 1; m <= 20; m++) {
            String mealMeasurements = (String) meal.get("strMeasure" + m);
            String mealIngredients = (String) meal.get("strIngredient" + m);
            if (mealMeasurements != null && !mealMeasurements.trim().isEmpty()
                    && mealIngredients != null && !mealIngredients.trim().isEmpty()) {
                System.out.println(" ~ " + mealMeasurements + " of " + mealIngredients);
            }
        }
        System.out.println();
    }
}
