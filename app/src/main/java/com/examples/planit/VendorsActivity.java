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

public class VendorsActivity extends AppCompatActivity {

    private final String[] eventNames = {"Event 1", "Event 2", "Event 3"};

    FloatingActionButton floatVendor;
    ImageButton backToHome;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);
        backToHome = findViewById(R.id.backToHome2);
        bottomNavigationView = findViewById(R.id.bottom_navigation1);
        floatVendor = findViewById(R.id.floatingVendors);

        floatVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddVendorDialog();
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.vendorNavItem);
        bottomNavigationView.setOnItemSelectedListener((@NotNull MenuItem item) -> {
            final int id = item.getItemId();
            if (R.id.guestNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), GuestsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.eventNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.budgetNavItem == id) {
                finish();
                startActivity(new Intent(getApplicationContext(), BudgetActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return R.id.vendorNavItem == id;
        });

        backToHome.setOnClickListener(view -> this.finish());
    }


    private void showAddVendorDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_vendor, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(VendorsActivity.this);
        dialogBuilder.setView(dialogView);

        Spinner eventSpinner = dialogView.findViewById(R.id.event_spinner);
        EditText vendorName = dialogView.findViewById(R.id.input_vendor_name);
        EditText serviceType = dialogView.findViewById(R.id.input_service_type);
        EditText phoneNumber = dialogView.findViewById(R.id.input_phone);
        EditText email = dialogView.findViewById(R.id.input_email);
        EditText address = dialogView.findViewById(R.id.input_address);
        Button createVendorButton = dialogView.findViewById(R.id.create_vendor_button);

        // Populate Spinner with event names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eventNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner.setAdapter(adapter);

        AlertDialog dialog = dialogBuilder.create();

        createVendorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validation logic here
                if (vendorName.getText().toString().trim().isEmpty() ||
                        serviceType.getText().toString().trim().isEmpty() ||
                        phoneNumber.getText().toString().trim().isEmpty() ||
                        email.getText().toString().trim().isEmpty() ||
                        address.getText().toString().trim().isEmpty()) {

                    Toast.makeText(VendorsActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Logic to add vendor
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
}