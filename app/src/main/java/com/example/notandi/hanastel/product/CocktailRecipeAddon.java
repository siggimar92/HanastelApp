package com.example.notandi.hanastel.product;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hrafnkell on 31/10/2015.
 */
public class CocktailRecipeAddon extends CocktailRecipe implements Serializable {
    private ArrayList<IngredientAddon> ingredientAddons;

    public CocktailRecipeAddon(){}

    public CocktailRecipeAddon(int cocktailId, String name, String imgPath, ArrayList<Ingredient> ingredients, ArrayList<IngredientAddon> ingredientAddons) {
        super(cocktailId, name, imgPath, ingredients);
        this.ingredientAddons = ingredientAddons;
    }

    public CocktailRecipeAddon(int cocktailId, String name, String imgPath, ArrayList<Ingredient> ingredients, String description, ArrayList<IngredientAddon> ingredientAddons) {
        super(cocktailId, name, imgPath, ingredients, description);
        this.ingredientAddons = ingredientAddons;
    }

    public ArrayList<IngredientAddon> getIngredientAddons() {
        return ingredientAddons;
    }

    public void setIngredientAddons(ArrayList<IngredientAddon> ingredientAddons) {
        this.ingredientAddons = ingredientAddons;
    }
}