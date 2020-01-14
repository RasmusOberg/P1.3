package com.example.p13;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity-class for the income-objects created by the user
 * @author rasmusoberg
 */
@Entity(tableName = "income_table")
public class Income {
    public static final String PRICE = "Price";
    public static final String DATE = "Date";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = PRICE)
    private double price;
    @ColumnInfo(name = DATE)
    private String date;

    private String title;
    private String category;
    private int year;
    private int month;
    private int day;

    public Income(String title, String category, double price, int year, int month, int day){
        this.title = title;
        this.category = category;
        this.price = price;
        this.year = year;
        this.month = month;
        this.day = day;
        this.date = "" + year + month + day;
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

    public String getDate(){ return date; }

    public void setDate(String string){
        this.date = string;
    }

}
