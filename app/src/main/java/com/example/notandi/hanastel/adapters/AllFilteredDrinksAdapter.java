package com.example.notandi.hanastel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notandi.hanastel.R;
import com.example.notandi.hanastel.product.CocktailRecipe;
import com.example.notandi.hanastel.product.CocktailRecipeAddon;

import java.util.List;

/**
 * Created by SigurdurMarAtlason on 29/10/15.
 */
public class AllFilteredDrinksAdapter extends ArrayAdapter<CocktailRecipeAddon> {

    public AllFilteredDrinksAdapter(Context context, List<CocktailRecipeAddon> objects)  {
        super(context, 0, objects);
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        CocktailRecipeAddon cr = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drink_layout, parent, false);
        }


        TextView nameView = (TextView) convertView.findViewById(R.id.row_drink);
        nameView.setText(cr.getName());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.row_image);
        imageView.setImageResource(cr.getImgResourceId(getContext()));
        //recipes.get(position).getImgPath()

        return convertView;
    }
}