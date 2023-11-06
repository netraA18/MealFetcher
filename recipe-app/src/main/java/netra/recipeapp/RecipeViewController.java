package netra.recipeapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeViewController {

    @GetMapping("/home")
    public String showRecipeForm() {
        return "home";
    }

    @GetMapping("/recipe-details")
    public String showRecipeDetails(@RequestParam("recipeName") String recipeName) {
        // You can pass the recipeName to your backend service here
        // For now, let's just pass it as a parameter to the view
        return "recipe-details";
    }
}
