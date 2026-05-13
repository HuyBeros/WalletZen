package com.example.walletzen.model;

public class Transaction {

    private int id;

    private String title;
    private String amount;
    private String categoryIcon;
    private String date;
    private String time;
    private String note;

    public Transaction() {

    }

    public Transaction(
            String title,
            String amount,
            String categoryIcon,
            String date,
            String time,
            String note
    ) {

        this.title = title;
        this.amount = amount;
        this.categoryIcon = categoryIcon;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNote(String note) {
        this.note = note;
    }
}