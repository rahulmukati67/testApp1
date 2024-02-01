package com.example.testapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public int amountDue;
    public String userName ,priority ,userId;

    public User() {
    }

    public User(int amountDue, String userName , String priority , String userId) {
        this.amountDue = amountDue;
        this.userName = userName;
        this.priority = priority;
        this.userId =userId;
    }

    public int getAmountDue() {
        return amountDue;
    }
    public String getPriority() {
        return priority;
    }

    public String getUserName() {
        return userName;
    }
    public String getuserId() {
        return userId;
    }
}

