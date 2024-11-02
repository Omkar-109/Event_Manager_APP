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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.examples.planit.internals.Vendor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VendorsActivity extends AppCompatActivity {
    ArrayList<Vendor> vendorArrayList;
    VendorListItemAdapter vendorListItemAdapter;
    DBManager dbManager;
    ArrayList<String> eventNames;

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
        dbManager = new DBManager(getApplicationContext());
        vendorArrayList = dbManager.getAllVendors();
        vendorListItemAdapter = new VendorListItemAdapter(this, vendorArrayList);
        eventNames = dbManager.getAllEventNames();
        ListView listView = findViewById(R.id.listViewVendor);
        listView.setAdapter(vendorListItemAdapter);
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
                String selectedEvent = eventSpinner.getSelectedItem().toString();
                String name = vendorName.getText().toString().trim();
                String service = serviceType.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                String vendorAddress = address.getText().toString().trim();
                // Validation logic
                if (name.isEmpty() || service.isEmpty() || phone.isEmpty() || emailStr.isEmpty() || vendorAddress.isEmpty()) {
                    Toast.makeText(VendorsActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Logic to add vendor
                    addVendor(selectedEvent, name, service, phone, emailStr, vendorAddress);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
    private void addVendor(String event, String name, String service, String phone, String email, String address) {
        Vendor newVendor = new Vendor(name, service, phone);
        boolean result = dbManager.addVendor(newVendor);

        if (result) {
            // Successfully added the vendor, refresh the list
            vendorArrayList.add(newVendor);
            vendorListItemAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Vendor added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add vendor", Toast.LENGTH_SHORT).show();
        }
    }
}