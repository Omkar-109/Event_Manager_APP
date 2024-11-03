package com.examples.planit;
/**
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 *
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.examples.planit.internals.Guest;
import java.util.ArrayList;

public class GuestListItemAdapter extends ArrayAdapter<Guest> {
    public GuestListItemAdapter(Activity context, ArrayList<Guest> guests) {
        super(context, 0, guests);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_guest_list_item_adapter, parent, false);
        }

        Guest currentGuest = getItem(position);
        TextView tvName = currentItemView.findViewById(R.id.item_guest_name);
        assert currentGuest != null;
        tvName.setText("Name: " + currentGuest.getName());

        TextView tvPhone = currentItemView.findViewById(R.id.item_guest_phone);
        tvPhone.setText("Phone: " + currentGuest.getPhone());

        TextView tvEmail = currentItemView.findViewById(R.id.item_guest_email);
        tvEmail.setText("Email: " + currentGuest.getEmail());

        return currentItemView;
    }
}
