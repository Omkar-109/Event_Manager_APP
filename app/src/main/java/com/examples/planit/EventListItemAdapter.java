package com.examples.planit;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.examples.planit.internals.Event;
import com.examples.planit.internals.EventStatus;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EventListItemAdapter extends ArrayAdapter<Event> {

    private final NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    public EventListItemAdapter(Activity context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // Inflate the custom layout if it's null
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }

        // Get the current Event
        Event currentEvent = getItem(position);
        assert currentEvent != null;

        // Set event name
        TextView tvName = currentItemView.findViewById(R.id.item_event_name);
        tvName.setText(currentEvent.getName());

        // Set event date and time
        TextView tvDatetime = currentItemView.findViewById(R.id.item_dt);
        tvDatetime.setText(currentEvent.getFormattedStartDate());

        // Set total and remaining budget
        TextView tvTotalBudget = currentItemView.findViewById(R.id.item_total_budget);
        String budgetTxt = "Total Budget: " + numberFormatter.format(currentEvent.getBudget().getTotalBudget());
        tvTotalBudget.setText(budgetTxt);

        TextView tvRemBudget = currentItemView.findViewById(R.id.item_remaining_budget);
        budgetTxt = "Remaining Budget: " + numberFormatter.format(currentEvent.getBudget().getRemainingBudget());
        tvRemBudget.setText(budgetTxt);

        // Handle event status circle
        View statusCircle = currentItemView.findViewById(R.id.event_status_circle);

        // Update the circle color based on event status
        updateEventStatusCircle(statusCircle, currentEvent.getEventStatus());

        // Animate the circle
        animateCircle(statusCircle);

        // Return the completed view to render on screen
        return currentItemView;
    }

    // Method to update the circle color based on event status
    private void updateEventStatusCircle(View circleView, EventStatus status) {
        // Ensure that we use the circle shape drawable
        circleView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_shape));

        // Update the color based on the status
        switch (status) {
            case UPCOMING:
                ((GradientDrawable) circleView.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
            case ONGOING:
                ((GradientDrawable) circleView.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.purple));
                break;
            case COMPLETED:
                ((GradientDrawable) circleView.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.red));
                break;
            case CANCELLED:
                ((GradientDrawable) circleView.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.black));
                break;
        }
    }


    // Method to animate the circle
    private void animateCircle(View circleView) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(circleView, "scaleX", 1f, 0.8f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(circleView, "scaleY", 1f, 0.8f, 1f);

        scaleX.setRepeatCount(ObjectAnimator.INFINITE);
        scaleY.setRepeatCount(ObjectAnimator.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2500);
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
    }

}