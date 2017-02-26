package gosemathraj.com.calendarviews.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iamsparsh on 26/2/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "appointment.db";
    private final static int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + Contract.AppointmentEntry.TABLE_NAME + "("
                                + Contract.AppointmentEntry.COLUMN_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTO INCREMENT, "
                                + Contract.AppointmentEntry.COLUMN_APPOINTMENT_TYPE + " TEXT NOT NULL, "
                                + Contract.AppointmentEntry.COLUMN_PATIENT_NAME + " TEXT NOT NULL, "
                                + Contract.AppointmentEntry.COLUMN_PATIENT_MOBILE + " INTEGER NOT NULL, "
                                + Contract.AppointmentEntry.COLUMN_PATIENT_EMAIL + " TEXT NOT NULL, "
                                + Contract.AppointmentEntry.COLUMN_START_TIME + " TEXT NOT NULL, "
                                + Contract.AppointmentEntry.COLUMN_END_TIME + " TEXT NOT NULL, "
                                + Contract.AppointmentEntry.COLUMN_DATE + " TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Contract.AppointmentEntry.TABLE_NAME );
        onCreate(sqLiteDatabase);

    }
}
