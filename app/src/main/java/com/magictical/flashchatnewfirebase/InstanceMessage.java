package com.magictical.flashchatnewfirebase;

/**
 * Created by magic on 2018-07-14.
 */

public class InstanceMessage {
    private String message;
    private String author;

    //default constructor
    public InstanceMessage() {
    }

    //constructor for existing data

    public InstanceMessage(String message, String author) {
        this.message = message;
        this.author = author;
    }

    //getters


    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
