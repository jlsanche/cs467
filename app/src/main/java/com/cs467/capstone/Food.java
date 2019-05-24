package com.cs467.capstone;

public class Food {
    protected String name;
    protected String rating;

    public Food(String n, String r) {
        this.name = n;
        this.rating = r;
    }

    public String getName(){
        return this.name;
    }
}