/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.controller;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int quantity;

    // Constructor
    public Book(int id, String title, String author, String publisher, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    // --- THIS IS THE PART THAT FIXES THE ERROR ---
    // Getters (must be public so the JSP can read them)
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public int getQuantity() { return quantity; }
}