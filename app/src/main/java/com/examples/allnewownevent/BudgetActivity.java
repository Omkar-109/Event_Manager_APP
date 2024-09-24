package com.examples.allnewownevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.examples.allnewownevent.gig.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BudgetActivity extends AppCompatActivity {


    ImageButton backToHome;
    BottomNavigationView bottomNavigationView;
    ListView eventListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        backToHome = findViewById(R.id.backToHome);
        bottomNavigationView = findViewById(R.id.bottom_navigation1);
        eventListView = findViewById(R.id.budgeteventlist);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked event

                // Start EventBudgetDetailActivity and pass event details
                Intent intent = new Intent(BudgetActivity.this, EventBudgetDetailActivity.class);
                //intent.putExtra("event_name", event.getName());
                //intent.putExtra("total_budget", event.getBudget());
                startActivity(intent);
            }
        });



        bottomNavigationView.setSelectedItemId(R.id.guestNavItem);
        bottomNavigationView.setOnItemSelectedListener((@NotNull MenuItem item) -> {
            final int id = item.getItemId();
            if (R.id.budgetNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), BudgetActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.eventNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.vendorNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), VendorsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return R.id.guestNavItem == id;
        });
        backToHome.setOnClickListener(view -> this.finish());
    }
}