package com.example.testapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public int amountDue;
    public String userName ,priority;

    public User() {
    }

    public User(int amountDue, String userName , String priority) {
        this.amountDue = amountDue;
        this.userName = userName;
        this.priority = priority;
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
}

