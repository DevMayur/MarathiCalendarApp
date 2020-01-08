package com.srkakadesir.calendarteachers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.srkakadesir.calendarteachers.database.DatabaseHelper;
import com.srkakadesir.calendarteachers.database.MonthEntry;
import com.srkakadesir.calendarteachers.model.DaysModel;
import com.srkakadesir.calendarteachers.model.MonthModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CalendarActivity extends AppCompatActivity {

    private int start_day_shift;
    private String mm,dd;
    private String yyyy;
    private TextView []tv_date = new TextView[35];
    private TextView tv_month_name,tv_marathi_month_name, tv_month_id;
    private List<MonthModel> mList;
    private List<DaysModel> dList;
    private int number_of_days = 31;
    private ImageView header_main;
    private List<Cursor> cursor_day_list;
    private ImageView iv_adv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        getCurrentDate();

        header_main = findViewById(R.id.iv_header_main);

        header_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, ShowImageActivity.class);
                startActivity(intent);
            }
        });
        setCurrentMonth();

        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String getCurrentMonth = dateFormat.format(date);

        initValues();
        int month_id = Integer.valueOf(getCurrentMonth)-1;
        setAdvertisement(month_id);

        yyyy= getResources().getString(R.string.yyyy);
        mList = new ArrayList<>();
        dList = new ArrayList<>();
        fetchData();
        setStartDayShift(mList.get(month_id));
        initializeViews();
        setDialog(mList.get(month_id));
        setFields(mList.get(month_id));
        fetchDays(month_id);




        tv_month_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(CalendarActivity.this, tv_month_name);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.months_menu, popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.january_menu:
                                setValues(0);

                                break;
                            case R.id.february_menu:
                                setValues(1);
                                break;
                            case R.id.march_menu:
                                setValues(2);
                                break;
                            case R.id.april_menu:
                                setValues(3);
                                break;
                            case R.id.may_menu:
                                setValues(4);
                                break;
                            case R.id.june_menu:
                                setValues(5);
                                break;
                            case R.id.july_menu:
                                setValues(6);
                                break;
                            case R.id.august_menu:
                                setValues(7);
                                break;
                            case R.id.september_menu:
                                setValues(8);
                                break;
                            case R.id.october_menu:
                                setValues(9);
                                break;
                            case R.id.november_menu:
                                setValues(10);
                                break;
                            case R.id.december_menu:
                                setValues(11);
                                break;

                        }

                        return true;
                    }

                    private void setValues(int n) {

                        for (int i=0 ;i<35 ;i++){
                            tv_date[i].setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        }

                        setStartDayShift(mList.get(n));
                        setFields(mList.get(n));
                        setDialog(mList.get(n));
                        dList.clear();
                        fetchDays(n);
                        setAdvertisement(n);
                    }
                });

            }
        });

    }

    private void setAdvertisement(int n) {

        switch (n) {
            case 0:
                iv_adv.setImageResource(R.drawable.adv_1);
                break;
            case 1:
                iv_adv.setImageResource(R.drawable.adv_2);
                break;
            case 2:
                iv_adv.setImageResource(R.drawable.adv_3);
                break;
            case 3:
                iv_adv.setImageResource(R.drawable.adv_4);
                break;
            case 4:
                iv_adv.setImageResource(R.drawable.adv_5);
                break;
            case 5:
                iv_adv.setImageResource(R.drawable.adv_6);
                break;
            case 6:
                iv_adv.setImageResource(R.drawable.adv_7);
                break;
            case 7:
                iv_adv.setImageResource(R.drawable.adv_8);
                break;
            case 8:
                iv_adv.setImageResource(R.drawable.adv_9);
                break;
            case 9:
                iv_adv.setImageResource(R.drawable.adv_10);
                break;
            case 10:
                iv_adv.setImageResource(R.drawable.adv_11);
                break;
            case 11:
                iv_adv.setImageResource(R.drawable.adv_12);
                break;
        }
    }

    private void setStartDayShift(MonthModel model) {
        start_day_shift = model.getStarting_day();
    }

    private void setFields(MonthModel model) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String getCurrentMonth = dateFormat.format(date);

        if (model.getId()==Integer.valueOf(getCurrentMonth)) {
            if (start_day_shift == 6) {
                if (Integer.valueOf(dd) == 30) {
                    tv_date[0].setBackgroundColor(getResources().getColor(R.color.colorYellow));
                } else if (Integer.valueOf(dd) == 31) {
                    tv_date[1].setBackgroundColor(getResources().getColor(R.color.colorYellow));
                } //0 : 30 , 1 : 31

            } else if (start_day_shift == 5) {// 0: 31

                if (Integer.valueOf(dd) == 31) {
                    tv_date[0].setBackgroundColor(getResources().getColor(R.color.colorYellow));
                }

            } else {
                tv_date[Integer.valueOf(dd) + start_day_shift - 1].setBackgroundColor(getResources().getColor(R.color.colorYellow));
            }

        }

            tv_month_name.setText(model.getMonth_name());
            tv_month_id.setText(getMonthNumber(model.getId()));
            tv_marathi_month_name.setText(model.getMarathi_month());
    }

    private void initValues() {
        tv_month_name = findViewById(R.id.tv_month_calendar);
        tv_marathi_month_name = findViewById(R.id.tv_marathi_month_calendar);
        tv_month_id = findViewById(R.id.tv_month_id_calendar);
        iv_adv = findViewById(R.id.iv_adv_calender);
    }

    private void fetchData() {
        DatabaseHelper monthsDatabaseHelper = new DatabaseHelper(this);
        DatabaseHelper daysDatabaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = monthsDatabaseHelper.getReadableDatabase();
        SQLiteDatabase db1 = daysDatabaseHelper.getReadableDatabase();
        Cursor cursor = monthsDatabaseHelper.readMonths();



        while (cursor.moveToNext()) {
            String month_name = cursor.getString(cursor.getColumnIndex(MonthEntry.MonthContract.MONTH_NAME));
            String marathi_month = cursor.getString(cursor.getColumnIndex(MonthEntry.MonthContract.MARATHI_MONTH));
            int start_day = cursor.getInt(cursor.getColumnIndex(MonthEntry.MonthContract.STARTING_DAY));
            int no_of_days = cursor.getInt(cursor.getColumnIndex(MonthEntry.MonthContract.NO_OF_DAYS));
            int full_moon = cursor.getInt(cursor.getColumnIndex(MonthEntry.MonthContract.FULL_MOON));
            int new_moon = cursor.getInt(cursor.getColumnIndex(MonthEntry.MonthContract.NEW_MOON));
            int id = cursor.getInt(cursor.getColumnIndex(MonthEntry.MonthContract.ID));
            this.number_of_days  = no_of_days;

            MonthModel model = new MonthModel(id,start_day,no_of_days,full_moon,new_moon,month_name,marathi_month);
            mList.add(model);

        }

        cursor_day_list = daysDatabaseHelper.readDays();

        monthsDatabaseHelper.closeDatabase();

    }

    private void fetchDays(int month_number) {

        Cursor cursor_day = cursor_day_list.get(month_number);

        while (cursor_day.moveToNext()) {

            int id = cursor_day.getInt(cursor_day.getColumnIndex(MonthEntry.DaysContract.ID));
            int date = cursor_day.getInt(cursor_day.getColumnIndex(MonthEntry.DaysContract.DATE));
            int month = cursor_day.getInt(cursor_day.getColumnIndex(MonthEntry.DaysContract.MONTH));
            int isHoliday = cursor_day.getInt(cursor_day.getColumnIndex(MonthEntry.DaysContract.ISHOLIDAY));

            String marathi_date = cursor_day.getString(cursor_day.getColumnIndex(MonthEntry.DaysContract.MARATHI_DATE));
            String info_1 = cursor_day.getString(cursor_day.getColumnIndex(MonthEntry.DaysContract.INFO1));
            String info_2 = cursor_day.getString(cursor_day.getColumnIndex(MonthEntry.DaysContract.INFO2));
            String occassion = cursor_day.getString(cursor_day.getColumnIndex(MonthEntry.DaysContract.OCCASSION));
            String occassion_asset = cursor_day.getString(cursor_day.getColumnIndex(MonthEntry.DaysContract.OCCASSION_ASSET));
            String sunrise = cursor_day.getString(cursor_day.getColumnIndex(MonthEntry.DaysContract.SUNRISE));
            String sunset = cursor_day.getString(cursor_day.getColumnIndex(MonthEntry.DaysContract.SUNSET));
            String user_note = cursor_day.getString(cursor_day.getColumnIndex(MonthEntry.DaysContract.USER_NOTE));

            DaysModel model = new DaysModel(id,date,month,isHoliday,marathi_date,info_1,info_2,occassion,occassion_asset,sunrise,sunset,user_note);
            dList.add(model);


            Log.d("days_db_test"," " + id+" " +date+" " +month+" " +isHoliday+" " +marathi_date+" " +info_1+" " +info_2+" " +occassion+" " +occassion_asset+" " +sunrise+" " +sunset+" " +user_note);

        }


    }

    private String getMonthNumber(int id) {
        String required_id = " ";
        switch (id){
            case 1:
                required_id = getResources().getString(R.string.mar_1);
                break;
            case 2:
                required_id = getResources().getString(R.string.mar_2);
                break;
            case 3:
                required_id = getResources().getString(R.string.mar_3);
                break;
            case 4:
                required_id = getResources().getString(R.string.mar_4);
                break;
            case 5:
                required_id = getResources().getString(R.string.mar_5);
                break;
            case 6:
                required_id = getResources().getString(R.string.mar_6);
                break;
            case 7:
                required_id = getResources().getString(R.string.mar_7);
                break;
            case 8:
                required_id = getResources().getString(R.string.mar_8);
                break;
            case 9:
                required_id = getResources().getString(R.string.mar_9);
                break;
            case 10:
                required_id = getResources().getString(R.string.mar_10);
                break;
            case 11:
                required_id = getResources().getString(R.string.mar_11);
                break;
            case 12:
                required_id = getResources().getString(R.string.mar_12);
                break;
        }
        return required_id;
    }


    private void getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("DD");
        Date date = new Date();
        String getCurrentDate = dateFormat.format(date);
        this.dd = getCurrentDate;
    }

    private void setCurrentMonth() {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String getCurrentMonth = dateFormat.format(date);
        Log.d("Month",dateFormat.format(date));
        switch (getCurrentMonth){
            case "01" :
                mm = getResources().getString(R.string.january);

                break;
            case "02" :
                mm = getResources().getString(R.string.february);

                break;
            case "03":
                mm = getResources().getString(R.string.march);

                break;
            case "04" :
                mm = getResources().getString(R.string.april);

                break;
            case "05" :
                mm = getResources().getString(R.string.may);

                break;
            case "06":
                mm = getResources().getString(R.string.june);

                break;
            case "07" :
                mm = getResources().getString(R.string.july);

                break;
            case "08" :
                mm = getResources().getString(R.string.august);

                break;
            case "09":
                mm = getResources().getString(R.string.september);

                break;
            case "10" :
                mm = getResources().getString(R.string.october);

                break;
            case "11" :
                mm = getResources().getString(R.string.november);

                break;
            case "12":
                mm = getResources().getString(R.string.december);

                break;
        }
    }



    private void setDialog(final MonthModel model) {
        int no = model.getNo_of_days();
        for (int i=1 ;i<=35 ;i++){
            if (i - start_day_shift <= no) {
                tv_date[i - 1].setText(getDate(i - start_day_shift));

                final int finalI = i;
                tv_date[i - 1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!getDate(finalI - start_day_shift).equals("")) {
                            dialogSelectedDate(model.getMonth_name(), getDate(finalI - start_day_shift), finalI - 1);
                        }
                    }
                });
            }else{
                tv_date[i - 1].setText("");
            }
        }
        if (start_day_shift == 6){
            if (no >=30) {
                tv_date[0].setText(getDate(30));
                tv_date[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogSelectedDate(model.getMonth_name(), getDate(30), 0);
                    }
                });
            }

            if (no == 31) {
                tv_date[1].setText(getDate(31));
                tv_date[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogSelectedDate(model.getMonth_name(), getDate(31), 1);
                    }
                });
            }

        }else if( start_day_shift == 5 && no == 31){
            tv_date[0].setText(getDate(31));
            tv_date[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogSelectedDate(model.getMonth_name(), getDate(31),  0);
                }
            });

        }
    }

    private String getDate(int i) {
        String date;
        date="";
        RegionalDataProvider regionalDataProvider = new RegionalDataProvider(i,this);
        date = regionalDataProvider.getDate();

        return date;
    }

    private void initializeViews() {

        //column 1

        tv_date[0] = findViewById(R.id.date_r1c1);
        tv_date[1] = findViewById(R.id.date_r2c1);
        tv_date[2] = findViewById(R.id.date_r3c1);
        tv_date[3] = findViewById(R.id.date_r4c1);
        tv_date[4] = findViewById(R.id.date_r5c1);
        tv_date[5] = findViewById(R.id.date_r6c1);
        tv_date[6] = findViewById(R.id.date_r7c1);


        //column 2

        tv_date[7] = findViewById(R.id.date_r1c2);
        tv_date[8] = findViewById(R.id.date_r2c2);
        tv_date[9] = findViewById(R.id.date_r3c2);
        tv_date[10] = findViewById(R.id.date_r4c2);
        tv_date[11] = findViewById(R.id.date_r5c2);
        tv_date[12] = findViewById(R.id.date_r6c2);
        tv_date[13] = findViewById(R.id.date_r7c2);


        //column 3

        tv_date[14] = findViewById(R.id.date_r1c3);
        tv_date[15] = findViewById(R.id.date_r2c3);
        tv_date[16] = findViewById(R.id.date_r3c3);
        tv_date[17] = findViewById(R.id.date_r4c3);
        tv_date[18] = findViewById(R.id.date_r5c3);
        tv_date[19] = findViewById(R.id.date_r6c3);
        tv_date[20] = findViewById(R.id.date_r7c3);

        //column 4

        tv_date[21] = findViewById(R.id.date_r1c4);
        tv_date[22] = findViewById(R.id.date_r2c4);
        tv_date[23] = findViewById(R.id.date_r3c4);
        tv_date[24] = findViewById(R.id.date_r4c4);
        tv_date[25] = findViewById(R.id.date_r5c4);
        tv_date[26] = findViewById(R.id.date_r6c4);
        tv_date[27] = findViewById(R.id.date_r7c4);


        //column 5

        tv_date[28] = findViewById(R.id.date_r1c5);
        tv_date[29] = findViewById(R.id.date_r2c5);
        tv_date[30] = findViewById(R.id.date_r3c5);
        tv_date[31] = findViewById(R.id.date_r4c5);
        tv_date[32] = findViewById(R.id.date_r5c5);
        tv_date[33] = findViewById(R.id.date_r6c5);
        tv_date[34] = findViewById(R.id.date_r7c5);


    }


    private void dialogSelectedDate(String current_month,String selected_date,int id) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.selected_date_layout, null);

        TextView date = dialogView.findViewById(R.id.tv_date_selected_date_dialog);
        TextView day = dialogView.findViewById(R.id.tv_day_selected_date_dialog);
        TextView month = dialogView.findViewById(R.id.tv_month_selected_date_dialog);
        TextView info_1 = dialogView.findViewById(R.id.tv_info1_selected_date_dialog);
        TextView info_2 = dialogView.findViewById(R.id.tv_info2_selected_date_dialog);
        TextView info_3 = dialogView.findViewById(R.id.tv_info3_selected_date_dialog);
        TextView occasion = dialogView.findViewById(R.id.tv_occassion_selected_date_layout);
        TextView sunrise = dialogView.findViewById(R.id.tv_sunrise_selected_date_layout);
        TextView sunset = dialogView.findViewById(R.id.tv_sunset_selected_date_layout);
        ImageView iv_red = dialogView.findViewById(R.id.iv_red_dot_selected_date);
        ImageView iv_blue = dialogView.findViewById(R.id.iv_blue_dot_selected_date);
        ImageView iv_green = dialogView.findViewById(R.id.iv_green_dot_selected_date);
        ImageView iv_occassion = dialogView.findViewById(R.id.iv_occassion_selected_date_layout);
        info_1.setVisibility(View.VISIBLE);
        iv_red.setVisibility(View.VISIBLE);
        info_2.setVisibility(View.VISIBLE);
        iv_blue.setVisibility(View.VISIBLE);
        occasion.setVisibility(View.VISIBLE);


        if(dList.size() >= Integer.valueOf(selected_date)) { //to avoid app crashing
            info_3.setText(String.valueOf(dList.get(Integer.valueOf(selected_date)-1).getMarathi_date()));
            info_1.setText(String.valueOf(dList.get(Integer.valueOf(selected_date)-1).getInfo_1()));
            info_2.setText(String.valueOf(dList.get(Integer.valueOf(selected_date)-1).getInfo_2()));
            occasion.setText(String.valueOf(dList.get(Integer.valueOf(selected_date)-1).getOccassion()));
            sunrise.setText(String.valueOf(dList.get(Integer.valueOf(selected_date)-1).getSunrise()));
            sunset.setText(String.valueOf(dList.get(Integer.valueOf(selected_date)-1).getSunset()));

            //setNullValues(Integer.valueOf(selected_date));
            if (dList.get(Integer.valueOf(selected_date)-1).getInfo_1()==null){
                info_1.setVisibility(View.GONE);
                iv_red.setVisibility(View.GONE);
            }
            if (dList.get(Integer.valueOf(selected_date)-1).getInfo_2()==null){
                info_2.setVisibility(View.GONE);
                iv_blue.setVisibility(View.GONE);
            }
            if (dList.get(Integer.valueOf(selected_date)-1).getOccassion()==null){
                occasion.setVisibility(View.GONE);
            }
            if (dList.get(Integer.valueOf(selected_date)-1).getOccassion_asset()==null){
                iv_occassion.setVisibility(View.GONE);
            }
        }

        RegionalDataProvider dayProvider = new RegionalDataProvider(Integer.valueOf(selected_date),this);
        day.setText(dayProvider.getDay(id));

        Typeface font = Typeface.createFromAsset(getAssets(), "shivaji01.ttf");
        day.setTypeface(font);
        month.setTypeface(font);
        month.setText(current_month);
        date.setTypeface(font);
        date.setText(selected_date);

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }

    private void setNullValues(int selected_date) {

    }

}
