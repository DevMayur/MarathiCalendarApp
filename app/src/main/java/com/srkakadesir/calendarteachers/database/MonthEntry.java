package com.srkakadesir.calendarteachers.database;

public class MonthEntry {
    public static class MonthContract{
        public static String TABLE_NAME = "months";
        public static final String ID= "id";
        public static final String MONTH_NAME= "month_name";
        public static final String MARATHI_MONTH= "marathi_month";
        public static final String STARTING_DAY= "starting_day";
        public static final String NO_OF_DAYS= "no_of_days";
        public static final String FULL_MOON= "full_moon";
        public static final String NEW_MOON= "new_moon";
    }

    public static class DaysContract{
        public static String TABLE_NAME = "days";
        public static final String ID = "id";
        public static final String DATE = "date";
        public static final String MONTH = "month";
        public static final String MARATHI_DATE = "marathi_date";
        public static final String INFO1 = "info_1";
        public static final String INFO2 = "info_2";
        public static final String OCCASSION = "occassion";
        public static final String OCCASSION_ASSET = "occassion_asset";
        public static final String SUNRISE = "sunrise";
        public static final String SUNSET = "sunset";
        public static final String ISHOLIDAY = "isHoliday";
        public static final String USER_NOTE = "UserNote";
    }

}
