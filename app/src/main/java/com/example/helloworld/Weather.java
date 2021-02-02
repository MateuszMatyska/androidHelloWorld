package com.example.helloworld;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("temperatura")
    private float Temperature;
    @SerializedName("suma_opadu")
    private float Fall;

    public Weather() {
        this.Fall = 0;
        this.Temperature = 0;
    }

    public Weather(float _temp, float _fall) {
        this.Fall = _fall;
        this.Temperature = _fall;
    }

    public float getTemperature() {
        return Temperature;
    }

    public float getFall() {
        return Fall;
    }
}
