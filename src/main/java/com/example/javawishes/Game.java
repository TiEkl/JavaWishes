package com.example.javawishes;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    String name;
    ArrayList<String> tags;
    float price;
    int steamID;



    public Game(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Game(String name, float price, ArrayList<String> tags) {
        this.name = name;
        this.tags = tags;
        this.price = price;
    }

    public Game(String name, ArrayList<String> tags, float price, int steamID) {
        this.name = name;
        this.steamID = steamID;
        this.tags = tags;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s%n" +
                "Tags: %s%n" +
                "Price: %.2f%n", this.name, this.tags.toString(), this.price);
    }
}
