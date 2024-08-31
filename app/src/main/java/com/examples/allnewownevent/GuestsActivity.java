package com.examples.allnewownevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class GuestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests);
    }

    public void onBackButtonClick(View view) {
        onBackPressed(); // Calls the default back navigation
    }
}