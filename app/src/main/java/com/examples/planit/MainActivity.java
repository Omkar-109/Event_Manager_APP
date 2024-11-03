package com.examples.planit;
/**
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 * @see com.examples.planit.MainActivity
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.examples.planit.internals.DateSorter;
import com.examples.planit.internals.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    TextView upcomingEventName;
    ImageButton aboutButton;
    DBManager dbManager;
    int[] imageIds = {R.drawable.eventsimgbutton, R.drawable.budgetimagebutton, R.drawable.guestimgbutton, R.drawable.vendorsimgbutton};
    private TextView eventCountdown;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(getApplicationContext());
        upcomingEventName = findViewById(R.id.ucEventName);

        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(this::goToAbout);

        ImageButtonAdapter adapter = new ImageButtonAdapter(this, imageIds);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelected(false);
        bottomNavigationView.setOnItemSelectedListener((@NotNull MenuItem item) -> {
            final int id = item.getItemId();
            if (R.id.budgetNavItem == id) {
                startActivity(new Intent(getApplicationContext(), BudgetActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.guestNavItem == id) {
                startActivity(new Intent(getApplicationContext(), GuestsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (R.id.vendorNavItem == id) {
                startActivity(new Intent(getApplicationContext(), VendorsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else {
                startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
        });

        gridView = findViewById(R.id.girdViewId);
        gridView.setAdapter(adapter);

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

    public void goToVendors(View view) {
        Intent intent = new Intent(MainActivity.this, VendorsActivity.class);
        startActivity(intent);
    }

    public void goToAbout(View view) {
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        eventCountdown = findViewById(R.id.event_countdown);
        ArrayList<Event> events = dbManager.getAllEvents();
        events.sort(new DateSorter());

        if (!events.isEmpty()) {
            Event upcomingEvent = events.get(0);
            upcomingEventName.setText(upcomingEvent.getName());

            long eventTimeInMillis = upcomingEvent.getStartDate().getTime(); // assuming getDateInMillis() provides the time in millis
            long currentTime = System.currentTimeMillis();
            long countdownTime = eventTimeInMillis - currentTime;

            if (countdownTime > 0) {
                startCountdown(countdownTime);
            } else {
                eventCountdown.setText(R.string.default_countdown);
            }
        } else {
            upcomingEventName.setText(R.string.no_events);
            eventCountdown.setText(R.string.default_countdown);
        }
    }

    private void startCountdown(long countdownTime) {
        countDownTimer = new CountDownTimer(countdownTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Convert millis until finished into hours, minutes, seconds
                String formattedTime = String.format(Locale.ENGLISH, "%02d : %02d : %02d : %02d", TimeUnit.MILLISECONDS.toDays(millisUntilFinished), TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24, TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60, TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);

                eventCountdown.setText(formattedTime);
            }

            @Override
            public void onFinish() {
                eventCountdown.setText(R.string.default_countdown);
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Stop the timer to avoid memory leaks
        }
    }
}