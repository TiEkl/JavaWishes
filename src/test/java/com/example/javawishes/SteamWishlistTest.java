package com.example.javawishes;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SteamWishlistTest {

    @Test
    void populateWishlistWithTagsTest() throws IOException, InterruptedException {
        var tags = new HashSet<String>();
        tags.add("Politics");
        var steamlist = new SteamWishlist("steamlist", tags, "76561197985090074");
        steamlist.populateWishlist();
        int expected = 1;
        int actual = steamlist.getGames().size();
        assertEquals(expected, actual);
    }

    @Test
    void populateWishlistWithoutTagsTest() throws IOException, InterruptedException {

        var steamlist = new SteamWishlist("steamlist", "76561197985090074");
        steamlist.populateWishlist();
        int expected = 94;
        int actual = steamlist.getGames().size();
        assertEquals(expected, actual);
    }

}