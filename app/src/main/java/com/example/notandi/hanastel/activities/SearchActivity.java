package com.example.notandi.hanastel.activities;

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

import java.util.ArrayList;

/**
 * Created by Notandi on 22-Oct-15.
 */
public class SearchActivity extends MainActivity{

    ArrayList<IngredientRaw> ingredientsChosen = new ArrayList<>();
    SelectedIngredientsAdapter selectedIngredientsAdapter;
    ArrayList<String> rawIngredientNames = new ArrayList<>();
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

        // setup
        searchBox = (TextView) findViewById(R.id.search_Box);
        searchButton = (Button) findViewById(R.id.search_button);


        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.search_Box);
        actv.setAdapter(new ArrayAdapter<> (this, android.R.layout.simple_dropdown_item_1line, rawIngredientNames));


        ListView ingr = (ListView) findViewById(R.id.ingredient_list);
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
            }
        };

        Thread mythread = new Thread(runnable);
        mythread.start();
    }
}
