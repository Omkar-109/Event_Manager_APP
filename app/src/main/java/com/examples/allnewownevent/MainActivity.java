package com.examples.allnewownevent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Change the status bar color to black
            getWindow().setStatusBarColor(Color.GRAY);
        }

        ImageButtonAdapter adapter = new ImageButtonAdapter(this, imageIds);


        gridView = findViewById(R.id.girdViewId);
        gridView.setAdapter(adapter);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, new String[]{"Event", "Budget", "Guests", "Vendors"});
//        gridView.setAdapter(adapter);

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

        // BottomNavigationView bottomNavigationView = findViewById(R.)
        /*
        NavigationBarView.OnItemSelectedListener((@NotNull MenuItem item) -> {
            switch (item.getItemId()) {
                case R.id.item1 -> {
                    // Respond to navigation item 1 click
                    return true;
                }
                case R.id.item2 -> {
                    // Respond to navigation item 2 click
                    return true;
                }
            }
            return false;
        });
        */


        /*
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        });
        */
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

    public void goToVendors(View view){
        Intent intent = new Intent(MainActivity.this, VendorsActivity.class);
        startActivity(intent);
    }

}