package netra.recipeapp;


import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MealService {

    public String getMealByName(String dish) throws IOException {
        
        String mealResponse = MealAPIUtil.getMealByName(dish);

        
        if (mealResponse.startsWith("error")) {
            return mealResponse;
        } else {
            
            return mealResponse;
        }
    }
}
