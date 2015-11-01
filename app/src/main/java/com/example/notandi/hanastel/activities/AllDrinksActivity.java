package com.example.notandi.hanastel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.notandi.hanastel.adapters.AllFilteredDrinksAdapter;
import com.example.notandi.hanastel.product.CocktailRecipe;
import com.example.notandi.hanastel.product.CocktailRecipeAddon;
import com.example.notandi.hanastel.product.Ingredient;
import com.example.notandi.hanastel.R;
import com.example.notandi.hanastel.adapters.AllDrinksAdapter;

import java.util.ArrayList;

/**
 * Created by Notandi on 22-Oct-15.
 */
public class AllDrinksActivity extends MainActivity {

    private ListView listView;
    AllDrinksAdapter adapter;
    AllFilteredDrinksAdapter filteredAdapter;
    boolean isSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_drinks);
        Intent i = getIntent();
        isSearch = i.getBooleanExtra("isSearch", false);

        listView = (ListView) findViewById(R.id.drinks);
        adapter = new AllDrinksAdapter(this, recipes);

        filteredAdapter = new AllFilteredDrinksAdapter(this, filteredRecipes);

        if(isSearch){
            listView.setAdapter(filteredAdapter);
        }
        else{
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = parent.getItemAtPosition(position);
                onDrinkDetailClick(view,o);
                //selectedIngredientsAdapter.notifyDataSetChanged();
                //searchBox.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public void onDrinkDetailClick(View view, Object o) {
        Intent intent = new Intent(this, DrinkDetailActivity.class);
        if(isSearch){
            CocktailRecipeAddon cra = (CocktailRecipeAddon) o;
            intent.putExtra("clickedCocktailSearch", cra);
            //intent.putExtra("clickedCocktailSearchList", cra.getIngredientAddons());
            intent.putExtra("isSearchDetail", true);
        }
        else{
            CocktailRecipe cr = (CocktailRecipe) o;
            intent.putExtra("clickedCocktail", cr);
            intent.putExtra("isSearchDetail", false);

        }

        intent.putExtra("isRandom", false);
        startActivity(intent);
    }
}