package com.examples.planit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public class ImageButtonAdapter extends BaseAdapter {
    private final Context mContext;
    private final int[] mImageIds;

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
            gridItem = inflater.inflate(R.layout.grid_item, parent, false);
        } else {
            gridItem = convertView;
        }

        ImageView imageView = gridItem.findViewById(R.id.grid_image_button);
        imageView.setImageResource(mImageIds[position]);

        return gridItem;
    }
}
