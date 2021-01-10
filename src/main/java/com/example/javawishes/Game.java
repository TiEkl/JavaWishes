package com.example.javawishes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    String name;
    ArrayList<String> tags;
    float price;
    int steamID;
    private static final String cheapSharkBaseURL = "https://www.cheapshark.com/api/1.0/games?";



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

    public JSONArray getDeal() throws IOException, InterruptedException {
        if(steamID != -1) {
            return getSteamDeal();
        }
        var cheapSharkURL = cheapSharkBaseURL + String.format("title=%s&limit=1&exact=1",this.name);
        return httpUtil.getArrayReq(cheapSharkURL);
    }

    private JSONArray getSteamDeal() throws IOException, InterruptedException {
        var cheapSharkURL = cheapSharkBaseURL + String.format("steamAppID=%d&limit=1", this.steamID);
        return httpUtil.getArrayReq(cheapSharkURL);
    }
}
