package no.avexis.allup.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import no.avexis.allup.model.WebPage;

/**
 * Created by Sindre Bøyum on 28.01.2017.
 */

public class WebPageAPI {
    private static SQLiteDatabase db;
    private static WebPageContract.WebPageDbHelper webPageDbHelper;


    public static WebPage getWebPage(int id, Context context) {
        webPageDbHelper = new WebPageContract.WebPageDbHelper(context);
        db = webPageDbHelper.getReadableDatabase();
        WebPage webPage = null;

        final String[] PROJECTION = {
                WebPageContract.WebPageEntry._ID,
                WebPageContract.WebPageEntry.COLUMN_NAME_STATUS,
                WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT,
                WebPageContract.WebPageEntry.COLUMN_NAME_TITLE,
                WebPageContract.WebPageEntry.COLUMN_NAME_URL
        };
        String SELECTION = WebPageContract.WebPageEntry._ID + " = ?";
        String[] SELECTION_ARGS = { Integer.toString(id) };

        Cursor cursor = db.query(
                WebPageContract.WebPageEntry.TABLE_NAME,
                PROJECTION,
                SELECTION,
                SELECTION_ARGS,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            webPage = new WebPage();

            webPage.setId(cursor.getLong(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry._ID)));
            webPage.setStatus(cursor.getInt(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS)));
            webPage.setStatusText(cursor.getString(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT)));
            webPage.setTitle(cursor.getString(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_TITLE)));
            webPage.setUrl(cursor.getString(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_URL)));

        }

        cursor.close();

        return webPage;
    }

    public static List<WebPage> getWebPages(final int OFFSET, final int LIMIT, Context context) {

        List<WebPage> list = new ArrayList<WebPage>();
        webPageDbHelper = new WebPageContract.WebPageDbHelper(context);
        SQLiteDatabase db = webPageDbHelper.getReadableDatabase();

        final String[] PROJECTION = {
                WebPageContract.WebPageEntry._ID,
                WebPageContract.WebPageEntry.COLUMN_NAME_STATUS,
                WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT,
                WebPageContract.WebPageEntry.COLUMN_NAME_TITLE,
                WebPageContract.WebPageEntry.COLUMN_NAME_URL
        };

        // Filter results WHERE "title" = 'My Title'
        // String selection = WebPageContract.WebPageEntry.COLUMN_NAME_TITLE + " = ?";
        // String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        final String SORT_ORDER =
                WebPageContract.WebPageEntry.COLUMN_NAME_TITLE + " ASC";

        Cursor cursor = db.query(
                WebPageContract.WebPageEntry.TABLE_NAME,
                PROJECTION,
                null,
                null,
                null,
                null,
                SORT_ORDER,
                OFFSET + "," + LIMIT
        );

        WebPage webPage;
        while(cursor.moveToNext()) {
            webPage = new WebPage();

            webPage.setStatus(cursor.getInt(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS)));
            webPage.setStatusText(cursor.getString(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT)));
            webPage.setTitle(cursor.getString(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_TITLE)));
            webPage.setUrl(cursor.getString(
                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_URL)));
            list.add(webPage);
        }
        cursor.close();

        return list;
    }

    public static void storeWebPage(WebPage webPage, Context context) {
        SQLiteDatabase db = webPageDbHelper.getWritableDatabase();
        webPageDbHelper = new WebPageContract.WebPageDbHelper(context);

        ContentValues values = new ContentValues();
        values.put(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS, 0);
        values.put(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT, "");
        values.put(WebPageContract.WebPageEntry.COLUMN_NAME_TITLE, webPage.getTitle());
        values.put(WebPageContract.WebPageEntry.COLUMN_NAME_URL, webPage.getUrl());

        long newRowId = db.insert(WebPageContract.WebPageEntry.TABLE_NAME, null, values);
    }

    public static void updateWebPage(WebPage webPage, Context context) {
        SQLiteDatabase db = webPageDbHelper.getWritableDatabase();
        webPageDbHelper = new WebPageContract.WebPageDbHelper(context);
        final String WHERE = WebPageContract.WebPageEntry._ID + " = ?";
        final String[] WHERE_ARGS = { Long.toString(webPage.getId())};

        ContentValues values = new ContentValues();
        values.put(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS, webPage.getStatus());
        values.put(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT, webPage.getStatusText());
        values.put(WebPageContract.WebPageEntry.COLUMN_NAME_TITLE, webPage.getTitle());
        values.put(WebPageContract.WebPageEntry.COLUMN_NAME_URL, webPage.getUrl());
        db.update(WebPageContract.WebPageEntry.TABLE_NAME, values, WHERE, WHERE_ARGS);
    }

    public static void deleteWebPage(WebPage webPage, Context context) {
        SQLiteDatabase db = webPageDbHelper.getWritableDatabase();
        webPageDbHelper = new WebPageContract.WebPageDbHelper(context);

        final String WHERE = WebPageContract.WebPageEntry._ID + " = ?";
        final String[] WHERE_ARGS = {Long.toString(webPage.getId())};

        db.delete(WebPageContract.WebPageEntry.TABLE_NAME, WHERE, WHERE_ARGS);
    }
}
