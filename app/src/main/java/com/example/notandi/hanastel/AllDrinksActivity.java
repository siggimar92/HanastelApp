package com.example.notandi.hanastel;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Notandi on 22-Oct-15.
 */
public class AllDrinksActivity extends MainActivity {

    private ListView listView;
    ArrayList<CocktailRecipe> cocktails = new ArrayList<>();
    AllDrinksAdapter adapter;


    String[] cocktailName ={
            "Mojito",
            "Sex on the beach",
            "Vodka Sprite",
            "Jaager bomb",
            "Stemmari á dollunni",
            "Sveita sælan",
            "Meistarinn úr suðri",
            "Gleymér ey"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_drinks);

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        int i = 0;
        for(String name : cocktailName){
            CocktailRecipe cr = new CocktailRecipe();
            cr.setName(name);
            cr.setDescription(name + "description");
            ArrayList<Ingredient> temp = new ArrayList<>();
            for(Ingredient ing : ingredients){
                temp.add(ing);
            }
            cr.setIngredients(temp);
            cr.setImgName("cocktail");
            cocktails.add(cr);
            ingredients.add(new Ingredient(i, "Ingredient " + String.valueOf(i), "100 ml"));
            i++;
        }

        listView = (ListView) findViewById(R.id.drinks);
        adapter = new AllDrinksAdapter(this, cocktails);
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