package com.examples.planit;
/**
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 *
 */
import static com.examples.planit.internals.RSVPStatus.PENDING;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.examples.planit.internals.Guest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;


public class GuestsActivity extends AppCompatActivity {

    FloatingActionButton floatBtnGuest;
    BottomNavigationView bottomNavigationView;
    ImageButton backToHome;
    ArrayList<Guest> guestArrayList;
    GuestListItemAdapter guestListItemAdapter;
    ArrayList<String> eventNames;
    DBManager dbManager;

    // Retrieve event names from the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests);

        dbManager = new DBManager(getApplicationContext());
        guestArrayList = dbManager.getAllGuests();
        guestListItemAdapter = new GuestListItemAdapter(this, guestArrayList);
        eventNames = dbManager.getAllEventNames();
        ListView listView = findViewById(R.id.listViewGuest);
        listView.setAdapter(guestListItemAdapter);

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
                String selectedEvent = eventSpinner.getSelectedItem().toString();
                String name = guestName.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();

                // Validation logic
                if (name.isEmpty() || emailStr.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(GuestsActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Logic to add guest to database
                    addGuest(selectedEvent, name, emailStr, phone);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
    private void addGuest(String event, String name, String email, String phone) {
        Guest newGuest = new Guest(name, email, phone, PENDING);
        boolean result = dbManager.addGuest(newGuest);

        if (result) {
            // Successfully added the guest, refresh the list
            guestArrayList.add(newGuest);
            guestListItemAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Guest added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add guest", Toast.LENGTH_SHORT).show();
        }
    }
}