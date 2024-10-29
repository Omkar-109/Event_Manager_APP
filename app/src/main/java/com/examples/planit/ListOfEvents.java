package com.examples.planit;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.examples.planit.internals.Event;

import java.util.ArrayList;

public class ListOfEvents extends AppCompatActivity {
    ArrayList<Event> eventArrayList;
    EventListItemAdapter eventListItemAdapter;

    ImageButton backtohome;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_of_events);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listtheevents);
        eventArrayList = dbManager.getAllEvents();
        eventListItemAdapter = new EventListItemAdapter(this, eventArrayList);
        listView.setAdapter(eventListItemAdapter);
        backtohome= findViewById(R.id.imageButton3);
        backtohome.setOnClickListener(view -> this.finish());
    }
}