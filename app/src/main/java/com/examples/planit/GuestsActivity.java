package com.examples.planit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;


public class GuestsActivity extends AppCompatActivity {

    private String[] eventNames = {"Event 1", "Event 2", "Event 3"};

    FloatingActionButton floatBtnGuest;
    BottomNavigationView bottomNavigationView;
    ImageButton backToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests);

        floatBtnGuest = findViewById(R.id.floatingGuest);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        backToHome = findViewById(R.id.backToHome);

        floatBtnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddGuestDialog();
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


    private void showAddGuestDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_guest, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(GuestsActivity.this);
        dialogBuilder.setView(dialogView);

        Spinner eventSpinner = dialogView.findViewById(R.id.event_spinner);
        EditText guestName = dialogView.findViewById(R.id.input_guest_name);
        EditText phoneNumber = dialogView.findViewById(R.id.input_phone);
        EditText email = dialogView.findViewById(R.id.input_email);
        Button createGuestButton = dialogView.findViewById(R.id.create_guest_button);

        // Populate Spinner with event names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eventNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner.setAdapter(adapter);

        AlertDialog dialog = dialogBuilder.create();

        createGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validation logic here
                if (guestName.getText().toString().trim().isEmpty() ||
                        phoneNumber.getText().toString().trim().isEmpty() ||
                        email.getText().toString().trim().isEmpty()) {

                    Toast.makeText(GuestsActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Logic to add guest
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
}