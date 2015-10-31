package com.example.notandi.hanastel.domain;

/**
 * Created by Hrafnkell on 30/10/2015.
 */
public class Favorite {
    private int _id;
    private int CocktailId;

    public Favorite(){}

    public Favorite(int _id, int cocktailId) {
        this._id = _id;
        CocktailId = cocktailId;
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
}
