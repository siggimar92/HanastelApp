package com.example.notandi.hanastel.domain;

/**
 * Created by Hrafnkell on 30/10/2015.
 */
public class IngredientInDrink {
    private int _id;
    private int CocktailId;
    private int IngredientId;
    private String Quantity;

    public IngredientInDrink(){}

    public IngredientInDrink(int _id, int cocktailId, int ingredientId, String quantity) {
        this._id = _id;
        CocktailId = cocktailId;
        IngredientId = ingredientId;
        Quantity = quantity;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getCocktailId() {
        return CocktailId;
    }

    public void setCocktailId(int cocktailId) {
        CocktailId = cocktailId;
    }

    public int getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(int ingredientId) {
        IngredientId = ingredientId;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
