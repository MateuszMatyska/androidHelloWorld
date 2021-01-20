package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyLocation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyLocation extends Fragment {
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
        return view;
    }


}