package com.example.javawishes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class JavaWishesApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void startUpDataTest() {
        var app = new JavaWishesApplication();
        var actual = app.startup();
        int expected = 1;
        Assertions.assertEquals(expected, actual.length);
    }

}
