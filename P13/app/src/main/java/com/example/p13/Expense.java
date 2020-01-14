package com.example.p13;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity-class for the expense-objects created by the user
 * @author rasmusoberg
 */

@Entity(tableName = "expense_table")
public class Expense {
    public static final String PRICE_EXP = "PRICE_EXP";

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String category;

    @ColumnInfo(name = PRICE_EXP)
    private double price;
    private int year, month, day;

    public Expense(String title, String category, double price, int year, int month, int day){
        this.title = title;
        this.category = category;
        this.price = price;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }
}
