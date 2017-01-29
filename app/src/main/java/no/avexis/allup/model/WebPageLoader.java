package no.avexis.allup.model;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import no.avexis.allup.db.WebPageAPI;
import no.avexis.allup.db.WebPageContract;
import no.avexis.allup.db.WebPageContract.WebPageDbHelper;

/**
 * Created by Sindre Bøyum on 28.01.2017.
 */

//public class WebPageLoader extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
//    SimpleCursorAdapter mAdapter;
//
//    static final String[] PROJECTION = {
//                WebPageContract.WebPageEntry._ID,
//                WebPageContract.WebPageEntry.COLUMN_NAME_STATUS,
//                WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT,
//                WebPageContract.WebPageEntry.COLUMN_NAME_TITLE,
//                WebPageContract.WebPageEntry.COLUMN_NAME_URL
//    };
//
//    static final String WHERE = "";
//
//    static final String ORDER_BY =
//             WebPageContract.WebPageEntry.COLUMN_NAME_STATUS + " ASC, " + WebPageContract.WebPageEntry.COLUMN_NAME_TITLE + " ASC";
//
//    @Override
//    protected void onCreate(Bundle savedInstance) {
//        super.onCreate(savedInstance);
//
//        ProgressBar progressBar = new ProgressBar(this);
//        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
//        progressBar.setIndeterminate(true);
//        getListView().setEmptyView(progressBar);
//
//        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
//        root.addView(progressBar);
//
//        String[] fromColumns = {WebPageContract.WebPageEntry.COLUMN_NAME_TITLE};
//        int[] toViews = {android.R.id.text1};
//
//        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, fromColumns, toViews, 0);
//        setListAdapter(mAdapter);
//
//        getLoaderManager().initLoader(0, null, this);
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return new CursorLoader(this, WebPageContract.WebPageEntry.CONTENT_URI, PROJECTION, WHERE, null, ORDER_BY);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        mAdapter.swapCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        mAdapter.swapCursor(null);
//    }
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        // TODO: Open "edit web page listing"
//    }
//}

public class WebPageLoader extends AsyncTaskLoader<List<WebPage>> {
    WebPageContract.WebPageDbHelper mDbHelper = new WebPageContract.WebPageDbHelper(getContext());

    public WebPageLoader(Context context) {
        super(context);
    }

    @Override
    public List<WebPage> loadInBackground() {

//        List<WebPage> list = new ArrayList<WebPage>();
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//        final String[] PROJECTION = {
//                WebPageContract.WebPageEntry._ID,
//                WebPageContract.WebPageEntry.COLUMN_NAME_STATUS,
//                WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT,
//                WebPageContract.WebPageEntry.COLUMN_NAME_TITLE,
//                WebPageContract.WebPageEntry.COLUMN_NAME_URL
//        };
//
//        // Filter results WHERE "title" = 'My Title'
//        // String selection = WebPageContract.WebPageEntry.COLUMN_NAME_TITLE + " = ?";
//        // String[] selectionArgs = { "My Title" };
//
//        // How you want the results sorted in the resulting Cursor
//        final String SORT_ORDER =
//             WebPageContract.WebPageEntry.COLUMN_NAME_TITLE + " ASC";
//
//        Cursor cursor = db.query(
//                WebPageContract.WebPageEntry.TABLE_NAME,
//                PROJECTION,
//                null,
//                null,
//                null,
//                null,
//                SORT_ORDER
//        );
//        WebPage webPage;
//        while(cursor.moveToNext()) {
//            webPage = new WebPage();
//
//            webPage.setStatus(cursor.getInt(
//                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS)));
//            webPage.setStatusText(cursor.getString(
//                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT)));
//            webPage.setTitle(cursor.getString(
//                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_TITLE)));
//            webPage.setUrl(cursor.getString(
//                    cursor.getColumnIndexOrThrow(WebPageContract.WebPageEntry.COLUMN_NAME_URL)));
//            list.add(webPage);
//        }
//        cursor.close();
//
//        return list;
        return WebPageAPI.getWebPages(0, 15, getContext());
    }
}
