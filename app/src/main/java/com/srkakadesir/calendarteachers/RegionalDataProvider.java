package com.srkakadesir.calendarteachers;

import android.content.Context;
import android.util.Log;

public class RegionalDataProvider {
    private Context context;
    private String date="null";
    private String day = "null";
    private int i;
    public RegionalDataProvider(int i, Context context) {
        this.i = i;
        this.context = context;
    }

    public String getDate(){
        switch(i){
            case 1:
                date= context.getResources().getString(R.string.mar_1);
                break;
            case 2:
                date= context.getResources().getString(R.string.mar_2);
                break;
            case 3:
                date= context.getResources().getString(R.string.mar_3);
                break;
            case 4:
                date= context.getResources().getString(R.string.mar_4);
                break;
            case 5:
                date= context.getResources().getString(R.string.mar_5);
                break;
            case 6:
                date= context.getResources().getString(R.string.mar_6);
                break;
            case 7:
                date= context.getResources().getString(R.string.mar_7);
                break;
            case 8:
                date= context.getResources().getString(R.string.mar_8);
                break;
            case 9:
                date= context.getResources().getString(R.string.mar_9);
                break;
            case 10:
                date= context.getResources().getString(R.string.mar_10);
                break;
            case 11:
                date= context.getResources().getString(R.string.mar_11);
                break;
            case 12:
                date= context.getResources().getString(R.string.mar_12);
                break;
            case 13:
                date= context.getResources().getString(R.string.mar_13);
                break;
            case 14:
                date= context.getResources().getString(R.string.mar_14);
                break;
            case 15:
                date= context.getResources().getString(R.string.mar_15);
                break;
            case 16:
                date= context.getResources().getString(R.string.mar_16);
                break;
            case 17:
                date= context.getResources().getString(R.string.mar_17);
                break;
            case 18:
                date= context.getResources().getString(R.string.mar_18);
                break;
            case 19:
                date= context.getResources().getString(R.string.mar_19);
                break;
            case 20:
                date= context.getResources().getString(R.string.mar_20);
                break;
            case 21:
                date= context.getResources().getString(R.string.mar_21);
                break;
            case 22:
                date= context.getResources().getString(R.string.mar_22);
                break;
            case 23:
                date= context.getResources().getString(R.string.mar_23);
                break;
            case 24:
                date= context.getResources().getString(R.string.mar_24);
                break;
            case 25:
                date= context.getResources().getString(R.string.mar_25);
                break;
            case 26:
                date= context.getResources().getString(R.string.mar_26);
                break;
            case 27:
                date= context.getResources().getString(R.string.mar_27);
                break;
            case 28:
                date= context.getResources().getString(R.string.mar_28);
                break;
            case 29:
                date= context.getResources().getString(R.string.mar_29);
                break;
            case 30:
                date= context.getResources().getString(R.string.mar_30);
                break;
            case 31:
                date= context.getResources().getString(R.string.mar_31);
                break;

            default:
                    date="";
                    break;


        }
        return date;
    }

    public String getDay(int i) {


        if (i == 0 || i==7 || i==14 || i==21 || i==28){
            day = context.getResources().getString(R.string.sunday);
        }else if(i == 1 || i==8 || i==15 || i==22 || i==29){
            day = context.getResources().getString(R.string.monday);
        }else if(i == 2 || i==9 || i==16 || i==23 || i==30){
            day = context.getResources().getString(R.string.tuesday);
        }else if(i == 3 || i==10 || i==17 || i==24 || i==31){
            day = context.getResources().getString(R.string.wednesday);
        }else if(i == 4 || i==11 || i==18 || i==25 || i==32){
            day = context.getResources().getString(R.string.thursday);
        }else if(i == 5 || i==12 || i==19 || i==26 || i==33){
            day = context.getResources().getString(R.string.friday);
        }else if(i == 6 || i==13 || i==20 || i==27 || i==34){
            day = context.getResources().getString(R.string.saturday);
        }

        return day;
    }

}
