package com.cs467.capstone;

import com.google.firebase.database.*;

public class CreateAddFood {

    protected DatabaseReference db;
    private final String childName = "UsersFood";

    // creates a new food and calls addFood() to add to database
    public void createAddNewFood(Food newFood, String userId) {
        db = FirebaseDatabase.getInstance().getReference().child(childName).child(userId);
        addFood(newFood);
    }

    // adds food (name + rating) to database
    public void addFood(Food food) {
        db.child(food.getName()).setValue(food);
    }
}