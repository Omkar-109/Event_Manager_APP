package com.examples.planit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.examples.planit.internals.Event;

import java.util.ArrayList;

public class EventListItemAdapter extends ArrayAdapter<Event> {

    public EventListItemAdapter(Activity context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Event currentEvent = getItem(position);


        TextView tvName = currentItemView.findViewById(R.id.item_event_name);
        assert currentEvent != null;
        String name = "Name: " + currentEvent.getName();
        tvName.setText(name);


        TextView tvDatetime = currentItemView.findViewById(R.id.item_dt);
        String startDate = "Starting In: " + currentEvent.getFormattedStartDate();
        tvDatetime.setText(startDate);

        TextView tvBudget = currentItemView.findViewById(R.id.item_budget);
        String budgetItem = "Remaining Budget: " + currentEvent.getBudget().getRemainingBudget();
        tvBudget.setText(budgetItem);

        // then return the recyclable view
        return currentItemView;
    }
}