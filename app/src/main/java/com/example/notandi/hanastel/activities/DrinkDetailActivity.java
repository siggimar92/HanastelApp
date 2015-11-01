package com.example.notandi.hanastel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.notandi.hanastel.adapters.DrinkDetailIngredientsAddonAdapter;
import com.example.notandi.hanastel.domain.Cocktail;
import com.example.notandi.hanastel.product.CocktailRecipe;
import com.example.notandi.hanastel.R;
import com.example.notandi.hanastel.adapters.DrinkDetailIngredientsAdapter;
import com.example.notandi.hanastel.product.CocktailRecipeAddon;
import com.example.notandi.hanastel.product.IngredientAddon;

import java.util.ArrayList;

/**
 * Created by SigurdurMarAtlason on 30/10/15.
 */
public class DrinkDetailActivity extends AllDrinksActivity {

    CocktailRecipe cr;
    CocktailRecipeAddon cra;
    Boolean isRandom = false;
    TextView drinkName;
    ImageView drinkImage;
    ListView drinkIngredients;
    TextView drinkDescription;
    ImageButton starButton;
    DrinkDetailIngredientsAdapter adapter;
    DrinkDetailIngredientsAddonAdapter adapterAddon;
    boolean hasClickedFavorite = false;
    ArrayList<CocktailRecipe> favoriteDrinks = new ArrayList<CocktailRecipe>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        starButton = (ImageButton) findViewById(R.id.favorite_star);
        drinkName = (TextView) findViewById(R.id.drink_detail_name);
        drinkIngredients = (ListView) findViewById(R.id.drink_detail_ingredients_list);
        drinkDescription = (TextView) findViewById(R.id.drink_detail_description);
        drinkImage = (ImageView) findViewById(R.id.drink_detail_image);

        Intent intent = getIntent();
        if(intent.getBooleanExtra("isSearchDetail", false)){
            cra = (CocktailRecipeAddon) intent.getSerializableExtra("clickedCocktailSearch");
            adapter = new DrinkDetailIngredientsAdapter(this, cra.getIngredientsList());
            drinkName.setText(cra.getName());
            drinkDescription.setText(cra.getDescription());
            drinkImage.setImageResource(cra.getImgResourceId(getApplicationContext()));
            drinkIngredients.setAdapter(adapter);
        }
        else{
            cr = (CocktailRecipe)intent.getSerializableExtra("clickedCocktail");
            adapter = new DrinkDetailIngredientsAdapter(this, cr.getIngredientsList());
            drinkDescription.setText(cr.getDescription());
            drinkImage.setImageResource(cr.getImgResourceId(getApplicationContext()));
            drinkName.setText(cr.getName());
            drinkIngredients.setAdapter(adapter);
        }

        /*if (myDbHelper.searchForFavorite(cr)) {
            hasClickedFavorite = true;
            starButton.setBackgroundResource(R.drawable.btn_favorite_star);
        } else {
            hasClickedFavorite = false;
            starButton.setBackgroundResource(R.drawable.btn_favorite_star_alpha);
        }*/

        isRandom = (Boolean)intent.getSerializableExtra("isRandom");




        starButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasClickedFavorite) {
                    hasClickedFavorite = true;
                    starButton.setBackgroundResource(R.drawable.btn_favorite_star);
                    //myDbHelper.addFavorite(cr);
                } else {
                    hasClickedFavorite = false;
                    starButton.setBackgroundResource(R.drawable.btn_favorite_star_alpha);
                    //myDbHelper.deleteFavorite(cr);
                }
            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        //if (isRandom) {
        overridePendingTransition(R.anim.fade_in, R.anim.shrink);
        //} else {
        //    overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
        //}
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