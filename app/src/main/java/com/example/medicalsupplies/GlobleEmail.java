package com.example.medicalsupplies;

import android.app.Application;

public class GlobleEmail extends Application {


    int image;
    String name1, name2;

    public GlobleEmail(int image, String name, String des) {
        this.image = image;
        this.name1 = name;
        this.name2 = des;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name1;
    }

    public String getName2() {
        return name2;
    }
}
