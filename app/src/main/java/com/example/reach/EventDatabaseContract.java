package com.example.reach;
import android.provider.BaseColumns;
public class EventDatabaseContract {
    private EventDatabaseContract() {}
    public static class EventEntry implements BaseColumns
    {
        //table names
        public static final String tblEvent_name = "Event";
        public static final String tblUser_name = "User";
        public static final String tblEventTypes_name = "Event_Types";


        //Users table column names
        public static final String user_id = "USER_ID";
        public static final String email = "EMAIL";
        public static final String pwd = "PASSWORD";
        public static final String usrnme = "USERNAME";

        //Event table column names
        public static final String event_id = "EVENT_ID";
        public static final String event_name = "EVENT_NAME";
        public static final String location = "LOCATION";
        public static final String range = "RANGE";
        public static final String event_type_id = "EVENT_TYPE_ID";
        public static final String capacity = "CAPACITY";
        public static final String start_date = "START_DATE";
        public static final String end_date = "END_DATE";
        public static final String desc = "DESCRIPTION";

        //Event types column names
        public static final String event_type = "EVENT_TYPE";

    }
}
