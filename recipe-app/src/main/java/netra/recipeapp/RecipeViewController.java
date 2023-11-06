package netra.recipeapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


// @Controller
// public class RecipeViewController {

//     @GetMapping("/home")
//     public String showRecipeForm() {
//         return "home";
//     }

//     @GetMapping("/recipe-details")
//     public String showRecipeDetails(@RequestParam("recipeName") String recipeName) {
//         // You can pass the recipeName to your backend service here
//         // For now, let's just pass it as a parameter to the view
//         return "recipe-details";
//     }
// }



@Controller
public class RecipeViewController {
    @Autowired
    private MealService mealService; 

    @GetMapping("/home")
    public String showRecipeForm() {
        return "home";
    }

    @GetMapping("/recipe-details")
    public String showRecipeDetails(@RequestParam("recipeName") String recipeName) {
        
        String recipeDetails = mealService.getMealByName(recipeName);
        model.addAttribute("recipeDetails", recipeDetails);
        return "recipe-details";
    }

    @RequestMapping(value = "/search-recipe", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchRecipe(@RequestParam("recipeName") String recipeName) {
        return "recipe-details"; // Redirect to the recipe-details page
    }
}
