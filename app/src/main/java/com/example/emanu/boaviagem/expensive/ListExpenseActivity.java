package com.example.emanu.boaviagem.expensive;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.emanu.boaviagem.R;
import com.example.emanu.boaviagem.adapter.TravelCursorAdapter;
import com.example.emanu.boaviagem.database.DBHelper;
import com.example.emanu.boaviagem.provider.BoaViagemProvider;
import com.facebook.stetho.Stetho;

/**
 * Created by emanu on 08/07/2017.
 */

public class ListExpenseActivity extends AppCompatActivity  implements
        LoaderManager.LoaderCallbacks<Cursor>{

    TravelCursorAdapter travelCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_list);
        Stetho.initializeWithDefaults(this);
        travelCursorAdapter = new TravelCursorAdapter(this, null);
        getSupportLoaderManager().initLoader(0, null, this);

        ListView listView = (ListView) findViewById(R.id.travel_list);
        listView.setAdapter(travelCursorAdapter);
        FloatingActionButton fab_add_new_travel = (FloatingActionButton) findViewById(R.id.fab_add_new_travel);

        fab_add_new_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExpenseActivity.this, DetailExpenseActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(
            int id, Bundle args) {

        return new CursorLoader(
                this,
                BoaViagemProvider.CONTENT_URI,
                DBHelper.ALL_COLUMNS,
                null,
                null,
                DBHelper.COLUMN_ID);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        travelCursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        travelCursorAdapter.swapCursor(null);
    }
}
