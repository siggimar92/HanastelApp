package com.example.notandi.hanastel.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.notandi.hanastel.db.DatabaseHelper;
import com.example.notandi.hanastel.R;
import com.example.notandi.hanastel.domain.Cocktail;
import com.example.notandi.hanastel.domain.Favorite;
import com.example.notandi.hanastel.domain.IngredientInDrink;
import com.example.notandi.hanastel.domain.IngredientRaw;
import com.example.notandi.hanastel.domain.MyBarIngredient;
import com.example.notandi.hanastel.product.CocktailRecipe;
import com.example.notandi.hanastel.product.Ingredient;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public DatabaseHelper myDbHelper;

    ArrayList<Cocktail> domainCocktails;
    ArrayList<Favorite> domainFavorites;
    ArrayList<IngredientRaw> domainIngredientsRaw;
    ArrayList<IngredientInDrink> domainIngredientsInDrinks;
    ArrayList<MyBarIngredient> domainMyBarIngredients;

    ArrayList<CocktailRecipe> recipes;
    ArrayList<Ingredient> ingredients;

    public static boolean dbThreadActivated = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbHelper = new DatabaseHelper(this);
        startDataBase();
        Log.d("onCreate", "Entering onCreate");
        domainCocktails = myDbHelper.getDomainCocktails();
        domainFavorites = myDbHelper.getDomainFavorites();
        domainIngredientsRaw = myDbHelper.getDomainIngredientRaw();
        domainIngredientsInDrinks = myDbHelper.getDomainIngredientsInDrinks();
        domainMyBarIngredients = myDbHelper.getDomainMyBarIngredient();
        mapDomainToProduct();
    }

    private void mapDomainToProduct(){
        ingredients = new ArrayList<>();
        recipes = new ArrayList<>();
        for(Cocktail c : domainCocktails) {

            CocktailRecipe cocktailRecipe = new CocktailRecipe();
            cocktailRecipe.setName(c.getName());
            cocktailRecipe.setDescription(c.getDescription());
            cocktailRecipe.setImgName(c.getPicture());
            cocktailRecipe.setCocktailId(c.get_id());

            cocktailRecipe.setIngredients(new ArrayList<Ingredient>());
            // Set quantity and ingredient id
            for (IngredientInDrink i : domainIngredientsInDrinks) {
                if (i.getCocktailId() == c.get_id()) {
                    Ingredient newIng = new Ingredient();
                    newIng.setId(i.getIngredientId());
                    newIng.setQuantity(i.getQuantity());
                    newIng.setName("name");
                    cocktailRecipe.addIngredient(newIng);
                }
            }

            // Set names for Ingredients
            for (IngredientRaw ir : domainIngredientsRaw) {
                for (Ingredient ing : cocktailRecipe.getIngredientsList()) {
                    if (ir.get_id() == ing.getId()) {
                        ing.setName(ir.getName());

                    }
                }
            }
            recipes.add(cocktailRecipe);

        }
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    private void dbCycle(){
        myDbHelper = new DatabaseHelper(this);
        new Populate().execute();
        dbThreadActivated = true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstance){
        super.onSaveInstanceState(savedInstance);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAllDrinksClick(View view){
        Intent intent = new Intent(this, AllDrinksActivity.class);
        startActivity(intent);

        // animation
        overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out);
    }

    public void onSearchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);

        // animation
        //overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out);
    }

    public void onMyBarClick(View view){
        Intent intent = new Intent(this, MyBarActivity.class);
        startActivity(intent);

        // animation
        //overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out);
    }

    public void onRandomClick(View view){
        Intent intent = new Intent(this, RandomActivity.class);
        startActivity(intent);

        // animation
        //overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out);
    }

    public void startDataBase(){
        try{
            myDbHelper.createDataBase();
            Log.d("mytag", "creating database");
        }
        catch (IOException ioe) {
            Log.d("mytag","exception creating database");
            throw new Error("Unable to create database");
        }

        try {
            Log.d("mytag", "opening database");
            myDbHelper.openDataBase();
        }
        catch (SQLiteException sqle) {
            Log.d("mytag", "exception opening database");
            throw sqle;
        }
    }

    public void fillDomain() {
        domainCocktails = myDbHelper.getDomainCocktails();
        domainFavorites = myDbHelper.getDomainFavorites();
        domainIngredientsRaw = myDbHelper.getDomainIngredientRaw();
        domainIngredientsInDrinks = myDbHelper.getDomainIngredientsInDrinks();
        domainMyBarIngredients = myDbHelper.getDomainMyBarIngredient();
    }

    private class Populate extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            startDataBase();
            fillDomain();
            publishProgress();

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... params){
            Log.d("populate", "onProgressUpdate: DONE");
            Log.d("populate", "Cocktail(0): " + domainCocktails.get(0).getName());
        }

        protected void onPostExecute(){
            Log.d("populate", "onPostExecute: function call, background proccess finished");
        }
    }

}
