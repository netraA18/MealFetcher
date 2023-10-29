package netra.recipeapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @GetMapping("/{recipeName}")
    public String getRecipeByName(@PathVariable String recipeName) {
        
        return "Recipe for " + recipeName;
    }
    
    
}
