package com.example.testapp;

import java.util.Vector;

public class FoodObjHints {
    Vector<FoodObjFood> food = new Vector<FoodObjFood>();

    public void setFood(FoodObjFood f) {
        food.add(f);
    }
}