package com.example.helloworld;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("id_stacji")
    private int id;
    @SerializedName("stacja")
    private String name;

    public City() {
        this.id=0;
        this.name = "";
    }

    public City(int id, String name) {
        this.id=id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
