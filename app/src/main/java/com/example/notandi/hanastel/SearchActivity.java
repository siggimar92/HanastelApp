package com.example.notandi.hanastel;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Notandi on 22-Oct-15.
 */
public class SearchActivity extends MainActivity{

    String param = "Name";
    ArrayList<String> ingredients = new ArrayList<>();
    ArrayAdapter ingredientAdapter;
    SelectedIngredientsAdapter selectedIngredientsAdapter;
    TextView searchBox;
    Button searchButton;

    String[] drinks = {"Vodki", "Gin", "Romm", "Appelsínusafi", "Sítróna", "Rjómi"};
    //ArrayList<String> drinks = new ArrayList<>(myDbHelper.getStuff(param));

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // setup
        searchBox = (TextView) findViewById(R.id.search_Box);
        searchButton = (Button) findViewById(R.id.search_button);


        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.search_Box);
        actv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, drinks));

        ingredientAdapter = new ArrayAdapter<String>(this, R.layout.ingredient_list, ingredients);
        ListView ingr = (ListView) findViewById(R.id.ingredient_list);
        //ingr.setAdapter(ingredientAdapter);

        selectedIngredientsAdapter = new SelectedIngredientsAdapter(this,ingredients);
        ingr.setAdapter(selectedIngredientsAdapter);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ingredient = (String) parent.getItemAtPosition(position);
                ingredients.add(ingredient);
                selectedIngredientsAdapter.notifyDataSetChanged();
                searchBox.setText("");
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

    public void onSearchClick(View view){
        Runnable runnable = new Runnable() {
            public void run() {

                long endTime = System.currentTimeMillis() +
                        20*1000;

                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception e) {}
                    }

                }
                handler.sendEmptyMessage(0);
            }
        };

        Thread mythread = new Thread(runnable);
        mythread.start();
    }
}
