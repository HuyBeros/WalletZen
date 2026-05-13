package com.walletzen.model;

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
            int id,
            String title,
            String amount,
            String categoryIcon,
            String date,
            String time,
            String note
    ) {

        this.id = id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}