package com.example.javawishes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class JavaWishesApplication implements CommandLineRunner {

    String wishPath = String.format(".%1$sdata%1$s", File.separator);
    public static void main(String[] args) {
        System.out.println("Starting application");
        SpringApplication.run(JavaWishesApplication.class, args);
        System.out.println("Closing application");
    }
    public void run(String... args) {
        var fileNames = startup();
        System.out.printf("Welcome to JavaWishes %n" +
                        "Wishlists found: %d%n", fileNames.length);
        for(int i = 0; i < fileNames.length; i++) {
            System.out.printf("%d. %s %n", i + 1, fileNames[i]);
        }
    }

    public String[] startup() {
        File folder = new File(wishPath);
        if(folder.exists()) {
            File[] files = folder.listFiles();
            if(files != null) {
                return Arrays.stream(files)
                        .filter(f -> f.getName().contains(".json"))
                        .map(f -> f.getName().substring(0, f.getName().indexOf('.')))
                        .toArray(String[]::new);
            }
            else {
                return new String[0];
            }
        }
        else {
            if(folder.mkdir()) {
                System.out.println("Data folder created!%n");
            }
            else {
                System.out.println("Could not create data folder!%n");
            }
            return new String[0];
        }
    }
}
