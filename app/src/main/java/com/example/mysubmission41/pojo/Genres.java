package com.example.mysubmission41.pojo;

import com.google.gson.annotations.SerializedName;

public class Genres {

    @SerializedName("name")
    private String name;

    public Genres(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

