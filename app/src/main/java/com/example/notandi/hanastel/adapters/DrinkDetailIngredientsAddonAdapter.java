package com.example.notandi.hanastel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.notandi.hanastel.R;
import com.example.notandi.hanastel.product.Ingredient;
import com.example.notandi.hanastel.product.IngredientAddon;

import java.util.ArrayList;

/**
 * Created by SigurdurMarAtlason on 30/10/15.
 */
public class DrinkDetailIngredientsAddonAdapter extends ArrayAdapter<IngredientAddon> {

    private ArrayList<IngredientAddon> ingredientsList = new ArrayList<>();

    public DrinkDetailIngredientsAddonAdapter(Context context, ArrayList<IngredientAddon> _ingredientsList){
        super(context, 0, _ingredientsList);
        ingredientsList = _ingredientsList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        IngredientAddon ingr = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_drink_detail_ingredients_row, parent, false);
        }

        //Set the name of the ingredient
        TextView name = (TextView) convertView.findViewById(R.id.drink_detail_ingredients_row_name);
        name.setText(ingr.getName());

        TextView quantity = (TextView) convertView.findViewById(R.id.drink_detail_ingredients_row_quantity);
        if(ingr.getQuantity() != null){
            quantity.setText(ingr.getQuantity());
        }
        else{
            quantity.setText("*");
        }

        return convertView;
    }
    
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
