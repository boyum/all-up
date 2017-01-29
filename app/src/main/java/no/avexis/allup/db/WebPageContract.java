package no.avexis.allup.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

import no.avexis.allup.model.WebPage;

/**
 * Created by sindre on 28.01.2017.
 */

public final class WebPageContract {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WebPageEntry.TABLE_NAME + " (" +
                    WebPageEntry._ID + " INTEGER PRIMARY KEY," +
                    WebPageEntry.COLUMN_NAME_STATUS + " INTEGER," +
                    WebPageEntry.COLUMN_NAME_STATUS_TEXT + " TEXT," +
                    WebPageEntry.COLUMN_NAME_TITLE + " TEXT," +
                    WebPageEntry.COLUMN_NAME_URL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WebPageEntry.TABLE_NAME;



    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private WebPageContract() {}



    public static class WebPageEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
//        public static final Uri CONTENT_URI = Uri.parse("content://avexis.allup");
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_STATUS_TEXT = "status_text";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_URL = "url";
    }

    public static class WebPageDbHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 3;
        public static final String DATABASE_NAME = "WebPage.db";

        public WebPageDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
