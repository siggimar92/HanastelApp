package com.example.notandi.hanastel.product;

import com.example.notandi.hanastel.domain.IngredientRaw;

import java.io.Serializable;

/**
 * Created by Hrafnkell on 31/10/2015.
 */
public class IngredientAddon extends IngredientRaw implements Serializable {

    private boolean isAvailable;
    private String Quantity;

    public IngredientAddon(){}

    public IngredientAddon(int _id, String name, boolean isAvailable) {
        super(_id, name);
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}