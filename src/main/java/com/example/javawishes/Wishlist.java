package com.example.javawishes;

import java.util.List;

public class Wishlist {
    public String getName() {
        return name;
    }

    public List<Game> getGames() {
        return games;
    }

    String name;
    List<Game> games;

    public Wishlist(String name, List<Game> games) {
        this.name = name;
        this.games = games;
    }
}
