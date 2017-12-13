package com.company;

/**
 * Created by andy on 13.12.2017.
 */
public class User {

    private static int currentID;
    private static boolean isFirst;
    private int id;
    private String name;

    public User(String name) {
        if (!isFirst) {
            currentID = 0;
        }
        this.isFirst = true;
        this.name = name;
        increment();
        this.id = currentID;
    }

    private void increment() {
        currentID++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
