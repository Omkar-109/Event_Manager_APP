package com.examples.planit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    TextView upcomingEventName;

    ImageButton aboutButton;
    DBManager dbManager;
    int[] imageIds = {
            R.drawable.eventsimgbutton,
            R.drawable.budgetimagebutton,
            R.drawable.guestimgbutton,
            R.drawable.vendorsimgbutton
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(getApplicationContext());
        upcomingEventName=findViewById(R.id.ucEventName);
        // Get the earliest event name
        String earliestEvent = dbManager.getEarliestEventName();

        aboutButton=findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAbout(view);
            }
        });

        if (earliestEvent != null) {
            upcomingEventName.setText(earliestEvent);
        }

        ImageButtonAdapter adapter = new ImageButtonAdapter(this, imageIds);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelected(false);
        bottomNavigationView.setOnItemSelectedListener((@NotNull MenuItem item) -> {
            final int id = item.getItemId();
            if (R.id.budgetNavItem == id) {
                startActivity(new Intent(getApplicationContext(), BudgetActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.guestNavItem == id) {
                startActivity(new Intent(getApplicationContext(), GuestsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.vendorNavItem == id) {
                startActivity(new Intent(getApplicationContext(), VendorsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else {
                startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
        });

        gridView = findViewById(R.id.girdViewId);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    goToEvents(view);
                    break;
                case 1:
                    goToBudget(view);
                    break;
                case 2:
                    goToGuests(view);
                    break;
                case 3:
                    goToVendors(view);
                    break;
            }
        });
    }

    public void goToEvents(View view) {
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }

    public void goToBudget(View view) {
        Intent intent = new Intent(this, BudgetActivity.class);
        startActivity(intent);
    }

    public void goToGuests(View view) {
        Intent intent = new Intent(this, GuestsActivity.class);
        startActivity(intent);
    }

    public void goToVendors(View view) {
        Intent intent = new Intent(MainActivity.this, VendorsActivity.class);
        startActivity(intent);
    }

    public void goToAbout(View view){
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }

}