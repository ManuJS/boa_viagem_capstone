package com.example.emanu.boaviagem.travel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.emanu.boaviagem.R;
import com.example.emanu.boaviagem.adapter.TravelCursorAdapter;
import com.example.emanu.boaviagem.database.DBHelper;
import com.example.emanu.boaviagem.expense.DetailExpenseActivity;
import com.example.emanu.boaviagem.expense.ListExpenseActivity;
import com.example.emanu.boaviagem.provider.BoaViagemProvider;
import com.facebook.stetho.Stetho;


/**
 * Created by emanu on 08/07/2017.
 */

public class ListTravelActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    TravelCursorAdapter travelCursorAdapter;
    ListView listView;
    final Context context = this;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_list);
        Stetho.initializeWithDefaults(this);
        travelCursorAdapter = new TravelCursorAdapter(this, null);
        getSupportLoaderManager().initLoader(0, null, this);

        listView = (ListView) findViewById(R.id.travel_list);
        listView.setAdapter(travelCursorAdapter);
        FloatingActionButton fab_add_new_travel = (FloatingActionButton) findViewById(R.id.fab_add_new_travel);

        fab_add_new_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTravelActivity.this, DetailTravelActivity.class);
                startActivity(intent);

            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getApplicationContext(), "bh "+ position, Toast.LENGTH_SHORT).show();

                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(ListTravelActivity.this);

                builder.setTitle("")
                        .setItems(R.array.options_array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                Intent intent;
                                switch (item){
                                    case 0:
                                        intent = new Intent(ListTravelActivity.this, ListExpenseActivity.class);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        intent = new Intent(ListTravelActivity.this, DetailExpenseActivity.class);
                                        startActivity(intent);
                                        break;
                                }
                                // The 'which' argument contains the index position
                                // of the selected item
                            }
                        });
                AlertDialog dialog =
                        builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(
            int id, Bundle args) {

        return new CursorLoader(
                this,
                BoaViagemProvider.CONTENT_URI_TRAVEL,
                DBHelper.ALL_COLUMNS_TRAVEL,
                null,
                null,
                DBHelper.COLUMN_ID_TRAVEL);
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
