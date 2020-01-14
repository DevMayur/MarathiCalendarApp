package com.srkakadesir.calendarteachers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.srkakadesir.calendarteachers.adapter.HolidayAdapter;
import com.srkakadesir.calendarteachers.database.DatabaseHelper;
import com.srkakadesir.calendarteachers.database.MonthEntry;
import com.srkakadesir.calendarteachers.model.DaysModel;

import java.util.ArrayList;
import java.util.List;

public class Holidays extends AppCompatActivity {
    private Cursor cursor_day;
    private List<DaysModel> dList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays);


        recyclerView = findViewById(R.id.recycler_view_holidays);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        dList = new ArrayList<>();
        cursor_day = databaseHelper.readHolidays();
        HolidayAdapter adapter = new HolidayAdapter(this,dList);
        recyclerView.setAdapter(adapter);

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
            adapter.notifyDataSetChanged();

            Log.d("days_db_test"," " + id+" " +date+" " +month+" " +isHoliday+" " +marathi_date+" " +info_1+" " +info_2+" " +occassion+" " +occassion_asset+" " +sunrise+" " +sunset+" " +user_note);

        }



    }
}
