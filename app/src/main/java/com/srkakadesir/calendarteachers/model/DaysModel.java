package com.srkakadesir.calendarteachers.model;

public class DaysModel {
    int id,date,month,isHoliday;
    String marathi_date,info_1,info_2,occassion,occassion_asset,sunrise,sunset,user_note;

    public DaysModel() {
    }

    public DaysModel(int id, int date, int month, int isHoliday, String marathi_date, String info_1, String info_2, String occassion, String occassion_asset, String sunrise, String sunset, String user_note) {
        this.id = id;
        this.date = date;
        this.month = month;
        this.isHoliday = isHoliday;
        this.marathi_date = marathi_date;
        this.info_1 = info_1;
        this.info_2 = info_2;
        this.occassion = occassion;
        this.occassion_asset = occassion_asset;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.user_note = user_note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(int isHoliday) {
        this.isHoliday = isHoliday;
    }

    public String getMarathi_date() {
        return marathi_date;
    }

    public void setMarathi_date(String marathi_date) {
        this.marathi_date = marathi_date;
    }

    public String getInfo_1() {
        return info_1;
    }

    public void setInfo_1(String info_1) {
        this.info_1 = info_1;
    }

    public String getInfo_2() {
        return info_2;
    }

    public void setInfo_2(String info_2) {
        this.info_2 = info_2;
    }

    public String getOccassion() {
        return occassion;
    }

    public void setOccassion(String occassion) {
        this.occassion = occassion;
    }

    public String getOccassion_asset() {
        return occassion_asset;
    }

    public void setOccassion_asset(String occassion_asset) {
        this.occassion_asset = occassion_asset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getUser_note() {
        return user_note;
    }

    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }
}
