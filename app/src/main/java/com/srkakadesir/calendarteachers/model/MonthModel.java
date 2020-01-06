package com.srkakadesir.calendarteachers.model;

public class MonthModel {
    int id,starting_day,no_of_days,full_moon,new_moon;
    String month_name,marathi_month;

    public MonthModel() {
    }

    public MonthModel(int id, int starting_day, int no_of_days, int full_moon, int new_moon, String month_name, String marathi_month) {
        this.id = id;
        this.starting_day = starting_day;
        this.no_of_days = no_of_days;
        this.full_moon = full_moon;
        this.new_moon = new_moon;
        this.month_name = month_name;
        this.marathi_month = marathi_month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStarting_day() {
        return starting_day;
    }

    public void setStarting_day(int starting_day) {
        this.starting_day = starting_day;
    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    public int getFull_moon() {
        return full_moon;
    }

    public void setFull_moon(int full_moon) {
        this.full_moon = full_moon;
    }

    public int getNew_moon() {
        return new_moon;
    }

    public void setNew_moon(int new_moon) {
        this.new_moon = new_moon;
    }

    public String getMonth_name() {
        return month_name;
    }

    public void setMonth_name(String month_name) {
        this.month_name = month_name;
    }

    public String getMarathi_month() {
        return marathi_month;
    }

    public void setMarathi_month(String marathi_month) {
        this.marathi_month = marathi_month;
    }
}
