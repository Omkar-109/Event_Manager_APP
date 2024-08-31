package com.examples.allnewownevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

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

        gridView = findViewById(R.id.girdViewId);


        ImageButtonAdapter adapter = new ImageButtonAdapter(this, imageIds);
        gridView.setAdapter(adapter);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, new String[]{"Event", "Budget", "Guests", "Vendors"});
//        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
                    default:
                        return;
                }

            }
        });





    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

    public void onBackButtonClick(View view) {
        onBackPressed(); // Calls the default back navigation
    }

}