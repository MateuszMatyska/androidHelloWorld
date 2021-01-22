package com.example.helloworld;

public class City {
    private String name;

    public City() {
        this.name = "";
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
