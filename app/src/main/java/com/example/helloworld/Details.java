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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Details.
     */
    // TODO: Rename and change types and number of parameters
    public static Details newInstance(String param1, String param2) {
        Details fragment = new Details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        TextView text = view.findViewById(R.id.Title);
        text.setText(getArguments().getString("city"));
        fetchData(view);
        return view;
    }

    private void fillFetchedData(View view, float temperature, float fall) {
        TextView tempText = view.findViewById(R.id.temperature);
        TextView fallText = view.findViewById(R.id.fall);

        tempText.setText(String.valueOf(temperature));
        fallText.setText(String.valueOf(fall));
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