package com.example.javawishes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


public class SteamWishlist extends Wishlist {
    String steamID;
    Set<String> tags;
    private static final String steamBaseUrl = "https://store.steampowered.com/wishlist/profiles/%s/wishlistdata/";


    public SteamWishlist(String name, Set<String> tags, String steamID) {
        super(name, new ArrayList<Game>());
        if(tags.isEmpty()) {
            this.tags = null;
        } else {
            this.tags = tags;
        }
        this.steamID = steamID;
    }
    public SteamWishlist(String name, String steamID) {
        super(name, new ArrayList<Game>());
        this.tags = null;
        this.steamID = steamID;
    }

    public void populateWishlist() throws IOException, InterruptedException {

        var json = fetchSteamWishlist();
        Iterator<String> keys = json.keys();

        while(keys.hasNext()) {
            var key = keys.next();
            String gameName = "";
            float price = 0;
            var tags = new ArrayList<String>();
            if(json.get(key) instanceof JSONObject) {
                JSONObject current = (JSONObject) json.get(key);

                if(current.get("tags") instanceof JSONArray) {
                    JSONArray jsontags = current.getJSONArray("tags");
                    for(int i = 0; i < jsontags.length(); i++) {
                        tags.add(jsontags.getString(i));
                    }
                }
                if(tags.size() > 0) {
                    if(current.get("name") instanceof String) {
                        gameName = (String) current.get("name");
                    }
                    if(current.get("subs") instanceof JSONArray) {
                        JSONArray subs = current.getJSONArray("subs");
                        if(subs.length() > 0) {
                            JSONObject obj = (JSONObject) subs.get(0);
                            if(obj.get("price") instanceof Integer) {
                                price = (float) obj.getInt("price") / 100;
                            }
                        }
                    }
                }
            }
            if(this.tags == null || !Collections.disjoint(this.tags, tags)) {
                super.games.add(new Game(gameName, tags, price, Integer.parseInt(key)));
            }
        }
    }

    private JSONObject fetchSteamWishlist() throws IOException, InterruptedException {
        var steamUrl = String.format(steamBaseUrl, steamID);
        return httpUtil.getObjectReq(steamUrl);
    }

    public String getSteamID() {
        return steamID;
    }


    public Set<String> getTags() {
        return tags;
    }

}
