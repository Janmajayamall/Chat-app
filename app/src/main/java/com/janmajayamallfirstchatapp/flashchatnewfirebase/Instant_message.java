package com.janmajayamallfirstchatapp.flashchatnewfirebase;

public class Instant_message {

    private String message;
    private String author;

    public Instant_message(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public Instant_message() {
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
