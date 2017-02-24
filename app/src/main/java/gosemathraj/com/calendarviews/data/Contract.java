package gosemathraj.com.calendarviews.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by RajeshG on 24-02-2017.
 */

public class Contract {

    public final static String AUTHORITY = "gosemathraj.com.calendarviews";
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public final static String APPOINTMENT_PATH = "appointment";

    public static class AppointmentEntry implements BaseColumns{

        public final static Uri CONTENT_TYPE_URI = BASE_CONTENT_URI.buildUpon().appendPath(APPOINTMENT_PATH).build();

        public final static String TABLE_NAME = "APPOINTMENT_TABLE";
        public final static String COLUMN_APPOINTMENT_ID = "appointment_id";
        public final static String COLUMN_APPOINTMENT_TYPE = "appointment_type";
        public final static String COLUMN_PATIENT_NAME = "patient_name";
        public final static String COLUMN_PATIENT_EMAIL = "patient_email";
        public final static String COLUMN_PATIENT_MOBILE = "patient_mobile";
        public final static String COLUMN_START_TIME = "start_time";
        public final static String COLUMN_END_TIME = "end_time";
        public final static String COLUMN_DATE = "date";

        public static Uri buildAppoinrmentUri(long id){
            return ContentUris.withAppendedId(CONTENT_TYPE_URI,id);
        }
    }
}
