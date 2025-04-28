package com.example.weatherapp.Model;

import java.io.Serializable;

public class ModelData implements Serializable {
    String Name;

    public ModelData(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }
}
