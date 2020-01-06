package com.srkakadesir.calendarteachers.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "months.sqlite";
    public static final String DBLOCATION= "data/data/com.srkakadesir.calendarteachers/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context){
        super(context,DBNAME,null,1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath((DBNAME)).getPath();
        if (mDatabase !=  null  && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);

    }

    public void closeDatabase() {
        if (mDatabase!=null){
            mDatabase.close();
        }
    }

    public Cursor readClasses(){
        openDatabase();

        return mDatabase.rawQuery("SELECT * FROM "+ MonthEntry.MonthContract.TABLE_NAME + "",null);
    }

}
