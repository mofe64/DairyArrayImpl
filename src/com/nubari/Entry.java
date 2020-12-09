package com.nubari;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Entry {
    private String title;
    private String entry;
    private String date;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    private Calendar calendar;

    public Entry(String title, String entry) {
        this.title = title;
        this.entry = entry;
        calendar = Calendar.getInstance();
        this.date = dateFormat.format(calendar.getTime());
    }

    public Entry(String title) {
        this.title = title.toLowerCase();
        calendar = Calendar.getInstance();
        this.date = dateFormat.format(calendar.getTime());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getDate() {
        System.out.println(date);
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Entry Details: " + "\n" +
                "Title: " + this.getTitle() + "\n" +
                "Entry: " + this.getEntry() + "\n" +
                "Date: " + this.getDate();
    }
}
