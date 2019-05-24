package com.cs467.capstone;

import com.google.firebase.database.*;

public class CreateAddFood {

    protected DatabaseReference db;

    // creates a new food and calls addFood() to add to database
    public void createAddNewFood(Food newFood) {
        db = FirebaseDatabase.getInstance().getReference();
        addFood(newFood);
    }

    // adds food (name + rating) to database
    public void addFood(Food food) {
        db.child(food.getName()).setValue(food);
    }
}