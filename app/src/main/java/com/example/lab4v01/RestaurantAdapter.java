package com.example.lab4v01;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RestaurantAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<Restaurant> arrayRestaurant;

    public RestaurantAdapter(Context context,int layout, List<Restaurant> arrayRestaurant) {
        this.context = context;
        this.layout = layout;
        this.arrayRestaurant = arrayRestaurant;
    }

    @Override
    public int getCount() {
        return arrayRestaurant.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(layout,null);
        // anh xa,gan gia tri
        Restaurant r = arrayRestaurant.get(position);
        TextView txtTitle = (TextView) row.findViewById(R.id.title);
        txtTitle.setText("Name: "+arrayRestaurant.get(position).getName());

        TextView txtAddress = (TextView) row.findViewById(R.id.address);
        txtAddress.setText("Address: "+arrayRestaurant.get(position).getAddress());

        ImageView icon = (ImageView)row.findViewById(R.id.icon);
        String type = r.getType();
        if(type.equals("Take out"))
            icon.setImageResource(R.drawable.take_out);
        else if(type.equals("Sit down"))
            icon.setImageResource(R.drawable.instagram);
        else
            icon.setImageResource(R.drawable.like);
        return row;
    }
}
