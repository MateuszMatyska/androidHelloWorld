package com.example.helloworld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyLocation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyLocation extends Fragment {
    private ArrayList<City> cities = new ArrayList<City>();
    private ArrayList<City> allCities = new ArrayList<City>();
    private AlertDialog dialog;

    public MyLocation() {
    }

    public static MyLocation newInstance(String param1, String param2) {
        MyLocation fragment = new MyLocation();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_location, container, false);
        if(cities.isEmpty() == true){
            fetchData(view);
        }
        fillCityList(view);
        onFabButtonClick(view);
        return view;
    }

    private void fillCityList(View view) {
        ListView list = view.findViewById(R.id.CityList);

        CityAdapter adapter = new CityAdapter(view.getContext(), cities);

        list.setAdapter(adapter);
    }

    private void onFabButtonClick(View view) {
        FloatingActionButton btn = view.findViewById(R.id.fabBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(view);
            }
        });
    }

    private void onCreateDialog(View view) {

        final CharSequence[] citiesSet = getCitiesNames();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_city)
                .setItems(citiesSet, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        City city = allCities.get(which);
                        cities.add(city);
                        dialog.cancel();
                        refreshCityList(view);
                    }
                });

        dialog = builder.create();
        dialog.show();
    }

    private CharSequence[] getCitiesNames() {
        ArrayList<String> names = new ArrayList<String>();

        for(City city: allCities) {
            names.add(city.getName());
        }

        CharSequence[] list = names.toArray(new CharSequence[names.size()]);

        return list;
    }

    private void refreshCityList(View view) {
        ListView list = view.findViewById(R.id.CityList);
        CityAdapter adapter = new CityAdapter(view.getContext(), cities);
        list.setAdapter(adapter);
    }

    private void fetchData(View view) {
        Call<List<City>> call = ApiClient.getInstance().getMyApi().getCities();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                List<City> cityList = response.body();

                for (int i = 0; i < cityList.size(); i++) {
                    allCities.add(cityList.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}