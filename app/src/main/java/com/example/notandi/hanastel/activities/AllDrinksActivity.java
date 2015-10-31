package com.example.notandi.hanastel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.notandi.hanastel.product.CocktailRecipe;
import com.example.notandi.hanastel.product.Ingredient;
import com.example.notandi.hanastel.R;
import com.example.notandi.hanastel.adapters.AllDrinksAdapter;

import java.util.ArrayList;

/**
 * Created by Notandi on 22-Oct-15.
 */
public class AllDrinksActivity extends MainActivity {

    private ListView listView;
    ArrayList<CocktailRecipe> cocktails = new ArrayList<>();
    AllDrinksAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_drinks);


        listView = (ListView) findViewById(R.id.drinks);
        adapter = new AllDrinksAdapter(this, recipes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CocktailRecipe cr = (CocktailRecipe) parent.getItemAtPosition(position);
                onDrinkDetailClick(view, cr);
                //selectedIngredientsAdapter.notifyDataSetChanged();
                //searchBox.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: Set state to as before clicking on details
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public void onDrinkDetailClick(View view, CocktailRecipe cr) {
        Intent intent = new Intent(this, DrinkDetailActivity.class);
        intent.putExtra("clickedCocktail", cr);
        intent.putExtra("isRandom", false);
        startActivity(intent);
    }
}