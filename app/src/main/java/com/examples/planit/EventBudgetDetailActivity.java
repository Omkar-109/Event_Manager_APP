package com.examples.planit;
/**
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 *
 */
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;


public class EventBudgetDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_budget_detail);

        // Get data from the Intent
        String eventName = getIntent().getStringExtra("event_name");
        double totalBudget = getIntent().getDoubleExtra("total_budget", 0);

        // Set event name and budget
        TextView eventNameView = findViewById(R.id.event_name);
        TextView totalBudgetView = findViewById(R.id.total_budget);
        TextView amountLeftView = findViewById(R.id.amount_left);

        eventNameView.setText(eventName);
        totalBudgetView.setText("Total Budget: $" + totalBudget);
        // Assume amountLeft is calculated based on payments made
        //amountLeftView.setText("Amount Left: $" + calculateAmountLeft());

        // Handle floating action button for adding a new payment
        FloatingActionButton fab = findViewById(R.id.add_payment_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddPaymentDialog();
            }
        });
    }

    // Show the Add Payment dialog
    private void showAddPaymentDialog() {
        // Inflate the dialog with custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_payment, null);

        // Create a dialog builder and set the custom layout
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(EventBudgetDetailActivity.this);
        dialogBuilder.setView(dialogView); // Use the inflated layout

        // Reference the input fields in the dialog
        EditText paymentName = dialogView.findViewById(R.id.input_payment_name);
        EditText paymentNote = dialogView.findViewById(R.id.input_payment_note);
        EditText paymentAmount = dialogView.findViewById(R.id.input_payment_amount);
        EditText paidDate = dialogView.findViewById(R.id.input_paid_date);

        // Date picker on clicking the date field
        paidDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(paidDate);
            }
        });


        AlertDialog dialog = dialogBuilder.create();
        // Create Payment Button
        Button createPaymentButton = dialogView.findViewById(R.id.create_payment_button);
        createPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paymentName.getText().toString().trim().isEmpty() || paymentAmount.getText().toString().trim().isEmpty() || paidDate.getText().toString().trim().isEmpty()) {
                    Toast.makeText(EventBudgetDetailActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Add logic to create a payment
                    // For example, add the payment to the list and update the display
                    Toast.makeText(EventBudgetDetailActivity.this, "Payment Created", Toast.LENGTH_SHORT).show();
                    dialog.dismiss(); // Dismiss the dialog after creation
                }
            }
        });

        // show the dialog
        dialog.show();
    }


    // Show DatePicker dialog
    private void showDatePickerDialog(final EditText paidDate) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(EventBudgetDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                paidDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}