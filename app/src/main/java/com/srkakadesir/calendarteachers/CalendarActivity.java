package com.srkakadesir.calendarteachers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.srkakadesir.calendarteachers.database.DatabaseHelper;
import com.srkakadesir.calendarteachers.database.MonthEntry;
import com.srkakadesir.calendarteachers.model.MonthModel;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

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
    private int number_of_days = 31;
    private ImageView header_main;


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

        int month_id = 0;

        yyyy= getResources().getString(R.string.yyyy);
        mList = new ArrayList<>();
        fetchData();
        setStartDayShift(mList.get(month_id));
        initializeViews();
        initValues();
        setDialog(mList.get(month_id));
        setCurrentMonth();
        setFields(mList.get(month_id));




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
                                setStartDayShift(mList.get(0));
                                setFields(mList.get(0));
                                setDialog(mList.get(0));
                                break;
                            case R.id.february_menu:
                                setStartDayShift(mList.get(1));
                                setFields(mList.get(1));
                                setDialog(mList.get(1));
                                break;
                            case R.id.march_menu:
                                setStartDayShift(mList.get(2));
                                setFields(mList.get(2));
                                setDialog(mList.get(2));
                                break;
                            case R.id.april_menu:
                                setStartDayShift(mList.get(3));
                                setFields(mList.get(3));
                                setDialog(mList.get(3));
                                break;
                            case R.id.may_menu:
                                setStartDayShift(mList.get(4));
                                setFields(mList.get(4));
                                setDialog(mList.get(4));
                                break;
                            case R.id.june_menu:
                                setStartDayShift(mList.get(5));
                                setFields(mList.get(5));
                                setDialog(mList.get(5));
                                break;
                            case R.id.july_menu:
                                setStartDayShift(mList.get(6));
                                setFields(mList.get(6));
                                setDialog(mList.get(6));
                                break;
                            case R.id.august_menu:
                                setStartDayShift(mList.get(7));
                                setFields(mList.get(7));
                                setDialog(mList.get(7));
                                break;
                            case R.id.september_menu:
                                setStartDayShift(mList.get(8));
                                setFields(mList.get(8));
                                setDialog(mList.get(8));
                                break;
                            case R.id.october_menu:
                                setStartDayShift(mList.get(9));
                                setFields(mList.get(9));
                                setDialog(mList.get(9));
                                break;
                            case R.id.november_menu:
                                setStartDayShift(mList.get(10));
                                setFields(mList.get(10));
                                setDialog(mList.get(10));
                                break;
                            case R.id.december_menu:
                                setStartDayShift(mList.get(11));
                                setFields(mList.get(11));
                                setDialog(mList.get(11));
                                break;

                        }

                        return true;
                    }
                });

            }
        });

    }

    private void setStartDayShift(MonthModel model) {
        start_day_shift = model.getStarting_day();
    }

    private void setFields(MonthModel model) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String getCurrentMonth = dateFormat.format(date);
        if (Integer.valueOf(getCurrentMonth) == model.getId()+1) {
            tv_date[Integer.valueOf(dd) + start_day_shift - 1].setBackgroundColor(getResources().getColor(R.color.colorYellow));
            tv_date[Integer.valueOf(dd) + start_day_shift - 1].setTextColor(getResources().getColor(R.color.colorDarkBlue));
        }
            tv_month_name.setText(model.getMonth_name());
            tv_month_id.setText(getMonthNumber(model.getId()));
            tv_marathi_month_name.setText(model.getMarathi_month());
    }

    private void initValues() {
        tv_month_name = findViewById(R.id.tv_month_calendar);
        tv_marathi_month_name = findViewById(R.id.tv_marathi_month_calendar);
        tv_month_id = findViewById(R.id.tv_month_id_calendar);
    }

    private void fetchData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = databaseHelper.readClasses();

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
        Log.d("date_by_mayur",getCurrentDate);
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
        Log.d("start_day_shift",start_day_shift+"");
        for (int i=1 ;i<=35 ;i++){
            if (i - start_day_shift <= no) {
                tv_date[i - 1].setText(getDate(i - start_day_shift));

                final int finalI = i;
                tv_date[i - 1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!getDate(finalI - start_day_shift).equals("")) {
                            dialogSelectedDate(model.getMonth_name(), getDate(finalI - start_day_shift), finalI - 1);
                            Toast.makeText(CalendarActivity.this, "Date : " + String.valueOf(finalI - start_day_shift) + "month : " + model.getMonth_name(), Toast.LENGTH_SHORT).show();
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

}
