package no.avexis.allup.activity;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import no.avexis.allup.R;
import no.avexis.allup.db.WebPageAPI;
import no.avexis.allup.db.WebPageContract;
import no.avexis.allup.model.WebPage;
import no.avexis.allup.model.WebPageAdapter;

public class AddWebPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_web_page);

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebPage webPage = new WebPage();

                if (getCurrentFocus() != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                TextView nameView = (TextView) findViewById(R.id.webPage_title);
                TextView urlView = (TextView) findViewById(R.id.webPage_url);

                webPage.setTitle(nameView.getText().toString().trim());
                webPage.setTitle(urlView.getText().toString().trim());

                if (webPage.getTitle().length() > 0 && webPage.getTitle().length() > 0) {
                    WebPageAPI.storeWebPage(webPage, getApplicationContext());
                    nameView.setText(null);
                    urlView.setText(null);

                    Snackbar.make(view, getString(R.string.webPage_add_success), Snackbar.LENGTH_LONG);

                    nameView.requestFocus();
                } else if (webPage.getTitle().length() == 0){
                    Snackbar.make(view, getString(R.string.webPage_add_error_title), Snackbar.LENGTH_LONG).show();
                    nameView.requestFocus();
                } else if (webPage.getUrl().length() == 0){
                    Snackbar.make(view, getString(R.string.webPage_add_error_url), Snackbar.LENGTH_LONG).show();
                    urlView.requestFocus();
                }
            }
        });
    }
}
