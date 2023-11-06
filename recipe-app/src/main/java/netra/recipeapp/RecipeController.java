package netra.recipeapp;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private MealService mealService;

    @GetMapping("/{recipeName}")
    public String getRecipeByName(@PathVariable String recipeName) throws IOException {
        String mealDetails = mealService.getMealByName(recipeName);
        return mealDetails;
    }

   

}

