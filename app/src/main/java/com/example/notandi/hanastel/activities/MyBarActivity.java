package com.example.notandi.hanastel.activities;

import android.os.Bundle;
import android.view.Menu;

import com.example.notandi.hanastel.R;

/**
 * Created by Notandi on 22-Oct-15.
 */
public class MyBarActivity extends MainActivity {

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

    /*public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
    }*/
}

