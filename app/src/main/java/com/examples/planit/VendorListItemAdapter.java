package com.examples.planit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.examples.planit.internals.Vendor;
import java.util.ArrayList;

public class VendorListItemAdapter extends ArrayAdapter<Vendor> {
    public VendorListItemAdapter(Activity context, ArrayList<Vendor> vendors) {
        super(context, 0, vendors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_vendor_list_item_adapter, parent, false);
        }

        Vendor currentVendor = getItem(position);
        TextView tvName = currentItemView.findViewById(R.id.item_vendor_name);
        assert currentVendor != null;
        tvName.setText("Name: " + currentVendor.getName());

        TextView tvService = currentItemView.findViewById(R.id.item_vendor_service);
        tvService.setText("Service: " + currentVendor.getServiceType());

        TextView tvPhone = currentItemView.findViewById(R.id.item_vendor_phone);
        tvPhone.setText("Contact: " + currentVendor.getContactInfo());

        return currentItemView;
    }
}
