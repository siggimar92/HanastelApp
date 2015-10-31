package com.example.notandi.hanastel.product;

import android.widget.Button;

import java.io.Serializable;

/**
 * Created by Notandi on 23-Oct-15.
 */
@SuppressWarnings("serial")
public class Ingredient implements Serializable {
    int _id;
    String name;
    String quantity;

    public Ingredient(){

    }

    public Ingredient(int _id, String name, String quantity){
        this._id = _id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getId() {
        return _id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
