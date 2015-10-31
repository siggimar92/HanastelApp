package com.example.notandi.hanastel.domain;

/**
 * Created by Hrafnkell on 30/10/2015.
 */
public class MyBarIngredient {
    private int _id;
    private int IngredientId;

    public MyBarIngredient() {
    }

    public MyBarIngredient(int _id, int ingredientId) {
        this._id = _id;
        IngredientId = ingredientId;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(int ingredientId) {
        IngredientId = ingredientId;
    }
}
