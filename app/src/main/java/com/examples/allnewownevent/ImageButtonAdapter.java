package com.examples.allnewownevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.examples.allnewownevent.R;

public class ImageButtonAdapter extends BaseAdapter {
    private Context mContext;
    private int[] mImageIds;

    public ImageButtonAdapter(Context context, int[] imageIds) {
        mContext = context;
        mImageIds = imageIds;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItem;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridItem = inflater.inflate(R.layout.grid_item, null);
        } else {
            gridItem = convertView;
        }

        ImageView imageView = gridItem.findViewById(R.id.grid_image_button);
        imageView.setImageResource(mImageIds[position]);

        return gridItem;
    }
}
