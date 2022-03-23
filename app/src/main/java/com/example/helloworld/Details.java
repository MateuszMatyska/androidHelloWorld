package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Details extends Fragment {

    public Details() {
    }

    public static Details newInstance(String param1, String param2) {
        Details fragment = new Details();
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
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        TextView text = view.findViewById(R.id.Title);
        text.setText(getArguments().getString("city"));
        fetchData(view);
        return view;
    }

    private void fillFetchedData(View view, float temperature, float fall) {
        TextView tempText = view.findViewById(R.id.temperature);
        TextView fallText = view.findViewById(R.id.fall);

        tempText.setText("Temperature " + String.valueOf(temperature));
        fallText.setText("Fall " + String.valueOf(fall));
    }

    private void fetchData(View view) {
        int id = Integer.parseInt(getArguments().getString("id"));
        Call<Weather> call = ApiClient.getInstance().getMyApi().getWeather(id);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();

                fillFetchedData(view, weather.getTemperature(), weather.getFall());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}