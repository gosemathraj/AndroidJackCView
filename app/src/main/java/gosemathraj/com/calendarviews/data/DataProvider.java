package gosemathraj.com.calendarviews.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by iamsparsh on 25/2/17.
 */

public class DataProvider extends ContentProvider {

    private DbHelper dbHelper = null;
    private final static int APPOINTMENT = 1;
    private final static int APPOINTMENT_ID = 2;

    private final static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI(Contract.AUTHORITY,Contract.APPOINTMENT_PATH,APPOINTMENT);
        uriMatcher.addURI(Contract.AUTHORITY,Contract.APPOINTMENT_PATH + "#",APPOINTMENT_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursorData = null;

        switch(uriMatcher.match(uri)){

            case APPOINTMENT :
                cursorData = db.query(Contract.AppointmentEntry.TABLE_NAME,
                                        projection,
                                        selection,
                                        selectionArgs,
                                        null,
                                        null,
                                        orderBy);
                break;

            case APPOINTMENT_ID :
                cursorData = db.query(Contract.AppointmentEntry.TABLE_NAME,
                                        projection,
                                        Contract.AppointmentEntry.COLUMN_APPOINTMENT_ID + " =?",
                                        new String[]{String.valueOf(Contract.AppointmentEntry.COLUMN_APPOINTMENT_ID)},
                                        null,
                                        null,
                                        orderBy);
                break;

            default :
                throw new UnsupportedOperationException("Unknown uri" + uri);
        }

        cursorData.setNotificationUri(getContext().getContentResolver(),uri);
        return cursorData;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri returnUri = null;
        long id;

        switch(uriMatcher.match(uri)){

            case APPOINTMENT :
                id = db.insert(Contract.AppointmentEntry.TABLE_NAME,null,contentValues);
                if(id > 0){
                    returnUri = Contract.AppointmentEntry.buildAppoinrmentUri(id);
                }else{
                    throw new UnsupportedOperationException("Unknown uri" + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows;

        switch(uriMatcher.match(uri)){

            case APPOINTMENT:
                rows = db.delete(Contract.AppointmentEntry.TABLE_NAME, s, strings);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }

        if(s == null || rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);

        }

        return rows;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows;

        switch(uriMatcher.match(uri)){

            case APPOINTMENT:
                rows = db.update(Contract.AppointmentEntry.TABLE_NAME, contentValues, s, strings);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }
}
