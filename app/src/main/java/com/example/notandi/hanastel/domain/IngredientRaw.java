package com.example.notandi.hanastel.domain;

/**
 * Created by Hrafnkell on 30/10/2015.
 */
public class IngredientRaw {
    private int _id;
    private String Name;

    public IngredientRaw() {
    }

    public IngredientRaw(int _id, String name) {
        this._id = _id;
        Name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
