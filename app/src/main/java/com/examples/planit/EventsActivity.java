package com.examples.planit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.examples.planit.internals.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EventsActivity extends AppCompatActivity {

    ArrayList<Event> eventArrayList;
    EventListItemAdapter eventListItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        ImageButton backToHome = findViewById(R.id.backToHome);
        ListView listView = findViewById(R.id.listView);
        eventArrayList = new ArrayList<>();
        eventListItemAdapter = new EventListItemAdapter(this, eventArrayList);
        listView.setAdapter(eventListItemAdapter);

        bottomNavigationView.setSelectedItemId(R.id.eventNavItem);
        bottomNavigationView.setOnItemSelectedListener((@NotNull MenuItem item) -> {
            final int id = item.getItemId();
            if (R.id.budgetNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), BudgetActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.guestNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), GuestsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.vendorNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), VendorsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return R.id.eventNavItem == id;
        });


        backToHome.setOnClickListener(view -> this.finish());

        //add event floating button
        FloatingActionButton fab = findViewById(R.id.addEventBtn);
        fab.setOnClickListener(addNewEvent());
    }

    private @NonNull View.OnClickListener addNewEvent() {
        return v -> {
            // Inflate the custom layout
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_add_event, null);

            // Create the AlertDialog
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(EventsActivity.this);
            dialogBuilder.setView(dialogView);

            // Initialize dialog views
            EditText nameInput = dialogView.findViewById(R.id.input_name);
            TextView dateTextView = dialogView.findViewById(R.id.input_date);
            TextView timeTextView = dialogView.findViewById(R.id.input_time);
            EditText budgetInput = dialogView.findViewById(R.id.input_budget);
            Button submitButton = dialogView.findViewById(R.id.submit_button);

            // Create and show the dialog
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();

            // Handle Date selection
            dateTextView.setOnClickListener(v1 -> {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EventsActivity.this, (view, year1, month1, dayOfMonth) -> {
                    // Set the selected date to the TextView
                    dateTextView.setText(String.format(Locale.ENGLISH, "%d/%d/%d", dayOfMonth, month1 + 1, year1));
                }, year, month, day);
                datePickerDialog.show();
            });

            // Handle Time selection
            timeTextView.setOnClickListener(v2 -> {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(EventsActivity.this, (view, hourOfDay, minute1) -> {
                    // Set the selected time to the TextView
                    timeTextView.setText(String.format(Locale.ENGLISH, "%d:%02d", hourOfDay, minute1));
                }, hour, minute, true);
                timePickerDialog.show();
            });

            // Handle Submit button click
            submitButton.setOnClickListener(v3 -> {
                // Collect the data from the input fields
                String name = nameInput.getText().toString().trim();
                String date = dateTextView.getText().toString().trim();
                String time = timeTextView.getText().toString().trim();
                String budget = budgetInput.getText().toString().trim();

                if (name.isEmpty()) {
                    nameInput.setError("Name is required");
                    return;
                }

                if (date.equals("Select Date")) {
                    Toast.makeText(EventsActivity.this, "Date is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (time.equals("Select Time")) {
                    Toast.makeText(EventsActivity.this, "Time is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (budget.isEmpty()) {
                    budgetInput.setError("Budget is required");
                    return;
                }
                // Extracting date components
                String[] dateParts = date.split("/");
                int dayOfMonth = Integer.parseInt(dateParts[0]);   // Day of month
                int month = Integer.parseInt(dateParts[1]) - 1;    // Month (subtract 1 because Calendar.MONTH is zero-based)
                int year = Integer.parseInt(dateParts[2]);         // Year

                // Extracting time components
                String[] timeParts = time.split(":");
                int hourOfDay = Integer.parseInt(timeParts[0]);     // Hour of day (24-hour format)
                int minute = Integer.parseInt(timeParts[1]);        // Minute

                // Creating Calendar instance and setting date and time
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                // Getting Date object from Calendar
                Date selectedDateTime = calendar.getTime();

                Event e = new Event(name);
                e.setStartDate(selectedDateTime);
                eventArrayList.add(e);

                eventListItemAdapter.notifyDataSetChanged();
                // All fields are valid, proceed with submission
                Toast.makeText(EventsActivity.this, "Event Created Successfully", Toast.LENGTH_SHORT).show();

                // Close the dialog
                dialog.dismiss();
            });
        };
    }
}