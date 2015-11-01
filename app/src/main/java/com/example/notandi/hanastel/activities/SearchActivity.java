package com.example.notandi.hanastel.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.notandi.hanastel.R;
import com.example.notandi.hanastel.adapters.SelectedIngredientsAdapter;
import com.example.notandi.hanastel.domain.IngredientRaw;
import com.example.notandi.hanastel.product.CocktailRecipe;
import com.example.notandi.hanastel.product.CocktailRecipeAddon;
import com.example.notandi.hanastel.product.Ingredient;
import com.example.notandi.hanastel.product.IngredientAddon;

import java.util.ArrayList;

/**
 * Created by Notandi on 22-Oct-15.
 */
public class SearchActivity extends MainActivity{

    ArrayList<IngredientRaw> ingredientsChosen = new ArrayList<>();
    SelectedIngredientsAdapter selectedIngredientsAdapter;
    ArrayList<String> rawIngredientNames = new ArrayList<>();
    ArrayList<CocktailRecipeAddon> cocktailCopies = new ArrayList<>();

    TextView searchBox;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        for(IngredientRaw ir : domainIngredientsRaw){
            if(!rawIngredientNames.contains(ir.getName())){
                rawIngredientNames.add(ir.getName());
            }
        }

        for(CocktailRecipe cr : recipes){
            CocktailRecipeAddon cra = new CocktailRecipeAddon();
            cra.setName(cr.getName());
            cra.setDescription(cr.getDescription());
            cra.setImgName(cr.getImgName());
            cra.setCocktailId(cr.getCocktailId());

            ArrayList<IngredientAddon> addons = new ArrayList<>();
            for(Ingredient i : cr.getIngredientsList()){
                IngredientAddon ia = new IngredientAddon();
                ia.setName(i.getName());
                ia.set_id(i.getId());
                ia.setIsAvailable(false);
                addons.add(ia);
            }
            cra.setIngredientAddons(addons);
            cra.setIngredients(cr.getIngredientsList());
            cocktailCopies.add(cra);
        }

        // setup
        searchBox = (TextView) findViewById(R.id.search_Box);
        searchButton = (Button) findViewById(R.id.search_button);


        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.search_Box);
        actv.setAdapter(new ArrayAdapter<> (this, android.R.layout.simple_dropdown_item_1line, rawIngredientNames));


        ListView ingr = (ListView) findViewById(R.id.ingredient_list);

        ingr.setBackgroundColor(Color.argb(125,0,0,0));

        selectedIngredientsAdapter = new SelectedIngredientsAdapter(this,ingredientsChosen);
        ingr.setAdapter(selectedIngredientsAdapter);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ingredientName = (String) parent.getItemAtPosition(position);
                boolean bail = false;
                for(IngredientRaw ir : ingredientsChosen ){
                    if(ingredientName.equals(ir.getName())){
                        bail = true;
                    }
                }
                if(!bail){
                    int _id = -1;
                    for(IngredientRaw ir : domainIngredientsRaw){
                        if(ingredientName.equals(ir.getName())){
                            _id = ir.get_id();
                        }
                    }
                    IngredientRaw ir = new IngredientRaw(_id, ingredientName );
                    ingredientsChosen.add(ir);
                    selectedIngredientsAdapter.notifyDataSetChanged();
                    searchBox.setText("");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        filteredRecipes.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public void onSearchClicked(View view){
        //set all ingredients in all cocktails to false
        setAllIngredientsAvailableFalse();

        if(!ingredientsChosen.isEmpty()){
            for(CocktailRecipeAddon r : cocktailCopies){
                for(IngredientAddon in : r.getIngredientAddons()){
                    for(IngredientRaw i : ingredientsChosen){
                        if(i.get_id() == in.get_id()){
                            in.setIsAvailable(true);
                            break;
                        }
                    }
                }
                boolean noAvailable = true;
                for(IngredientAddon ingra : r.getIngredientAddons()){
                    if(ingra.isAvailable()) noAvailable = false;
                }
                if(!noAvailable){
                    CocktailRecipeAddon cra = new CocktailRecipeAddon();
                    cra.setName(r.getName());
                    cra.setCocktailId(r.getCocktailId());
                    cra.setImgName(r.getImgName());
                    cra.setIngredientAddons(r.getIngredientAddons());
                    cra.setDescription(r.getDescription());
                    cra.setIngredients(r.getIngredientsList());
                    filteredRecipes.add(cra);
                }
            }
        }
        Intent intent = new Intent(this, AllDrinksActivity.class);
        intent.putExtra("isSearch", true);
        startActivity(intent);
    }

    public void setAllIngredientsAvailableFalse(){
        for(CocktailRecipeAddon cr : cocktailCopies){
            for(IngredientAddon i : cr.getIngredientAddons()){
                i.setIsAvailable(false);
            }
        }

    }

    /*public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
    }*/
}