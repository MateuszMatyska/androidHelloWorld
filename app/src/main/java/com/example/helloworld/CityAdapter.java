package com.example.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.Navigation;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City> {
    public CityAdapter(Context context, ArrayList<City> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_view, parent, false);
        }

        TextView cityName = convertView.findViewById(R.id.city_name);
        ImageButton cityBtn = convertView.findViewById(R.id.city_btn);

        cityName.setText(city.getName());
        cityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("city", city.getName());
                Navigation.findNavController(v).navigate(R.id.nav_to_details,bundle);
            }
        });

        return convertView;
    }
}
