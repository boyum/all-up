package no.avexis.allup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import no.avexis.allup.R;
import no.avexis.allup.db.WebPageContract;
import no.avexis.allup.model.WebPage;
import no.avexis.allup.model.WebPageAdapter;
import no.avexis.allup.model.WebPageLoader;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<List<WebPage>>{
    WebPageAdapter mWebPageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToActivity(AddWebPageActivity.class);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mWebPageAdapter = new WebPageAdapter(this, new ArrayList<WebPage>());
        WebPageLoader webPageLoader = new WebPageLoader(getApplicationContext());

//        ListView listView = (ListView) findViewById(R.id.webPage_list);
//        listView.setAdapter(mWebPageAdapter);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.webPage_list);
        recyclerView.setAdapter(mWebPageAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();


        Subscriber<List<WebPage>> subscriber = new Subscriber<List<WebPage>>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(List<WebPage> webPages) {
                sendToActivity(EditWebPageActivity.class);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        mWebPageAdapter = new WebPageAdapter(this, new ArrayList<WebPage>());
        WebPageLoader webPageLoader = new WebPageLoader(getApplicationContext());

//        ListView listView = (ListView) findViewById(R.id.webPage_list);
//        listView.setAdapter(mWebPageAdapter);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.webPage_list);
        recyclerView.setAdapter(mWebPageAdapter);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }

//    private void sendToAddWP() {
//        Intent intent = new Intent(this, AddWebPageActivity.class);
//        startActivity(intent);
//    }

    private void sendToActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this, activity.getClass());
        startActivity(intent);
    }

    private void sendToActivity(Class<? extends Activity> activity, WebPage webPage) {
        Intent intent = new Intent(this, activity.getClass());
        intent.putExtra(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS, webPage.getStatus());
        intent.putExtra(WebPageContract.WebPageEntry.COLUMN_NAME_STATUS_TEXT, webPage.getStatusText());
        intent.putExtra(WebPageContract.WebPageEntry.COLUMN_NAME_TITLE, webPage.getTitle());
        intent.putExtra(WebPageContract.WebPageEntry.COLUMN_NAME_URL, webPage.getUrl());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_webPage) {
            sendToActivity(AddWebPageActivity.class);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<List<WebPage>> onCreateLoader(int id, Bundle args) {
        return new WebPageLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<WebPage>> loader, List<WebPage> data) {
        mWebPageAdapter.setWebPages(data);
    }

    @Override
    public void onLoaderReset(Loader<List<WebPage>> loader) {
        mWebPageAdapter.setWebPages(new ArrayList<WebPage>());
    }
}
