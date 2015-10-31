package com.example.notandi.hanastel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SigurdurMarAtlason on 30/10/15.
 */
public class DrinkDetailActivity extends AllDrinksActivity {

    CocktailRecipe cr;
    Boolean isRandom = false;
    TextView drinkName;
    ImageView drinkImage;
    ListView drinkIngredients;
    TextView drinkDescription;
    DrinkDetailIngredientsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        Intent i = getIntent();
        cr = (CocktailRecipe)i.getSerializableExtra("clickedCocktail");

        drinkName = (TextView) findViewById(R.id.drink_detail_name);
        drinkIngredients = (ListView) findViewById(R.id.drink_detail_ingredients_list);
        drinkDescription = (TextView) findViewById(R.id.drink_detail_description);
        drinkImage = (ImageView) findViewById(R.id.drink_detail_image);
        adapter = new DrinkDetailIngredientsAdapter(this, cr.getIngredientsList());

        drinkName.setText(cr.getName());

        isRandom = (Boolean)i.getSerializableExtra("isRandom");
        if (!isRandom) {
            drinkIngredients.setAdapter(adapter);
        }
        drinkDescription.setText(cr.getDescription());
        drinkImage.setImageResource(cr.getImgResourceId(getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
}
