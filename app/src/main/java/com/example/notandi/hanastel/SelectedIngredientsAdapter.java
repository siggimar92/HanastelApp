package com.example.notandi.hanastel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Notandi on 23-Oct-15.
 */
public class SelectedIngredientsAdapter extends ArrayAdapter<String>{

    private ArrayList<String> ingredientsList = new ArrayList<>();

    public SelectedIngredientsAdapter(Context context, ArrayList<String> _ingredientsList){
        super(context, 0, _ingredientsList);
        ingredientsList = _ingredientsList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        String ingr = getItem(position).toString();
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_list, parent, false);
        }

        //Set the name of the ingredient
        TextView ingredientView = (TextView) view.findViewById(R.id.ingredent_list_item);
        ingredientView.setText(ingr);

        Button deleteButton = (Button) view.findViewById(R.id.ingredient_delete_btn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientsList.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
