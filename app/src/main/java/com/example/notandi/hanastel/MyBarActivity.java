package com.example.notandi.hanastel;

import android.os.Bundle;
import android.view.Menu;

/**
 * Created by Notandi on 22-Oct-15.
 */
public class MyBarActivity extends MainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bar);
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
