package com.examples.allnewownevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class GuestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        ImageButton backToHome = findViewById(R.id.backToHome);

        bottomNavigationView.setSelectedItemId(R.id.guestNavItem);
        bottomNavigationView.setOnItemSelectedListener((@NotNull MenuItem item) -> {
            final int id = item.getItemId();
            if (R.id.budgetNavItem == id) {
                startActivity(new Intent(getApplicationContext(), BudgetActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.eventNavItem == id) {
                startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.vendorNavItem == id) {
                startActivity(new Intent(getApplicationContext(), VendorsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return R.id.guestNavItem == id;
        });

        backToHome.setOnClickListener(view -> this.finish());
    }
}