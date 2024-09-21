package com.examples.allnewownevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class BudgetActivity extends AppCompatActivity {
    ImageButton backToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        backToHome = findViewById(R.id.backToHome);

        backToHome.setOnClickListener(view -> this.finish());
    }
}