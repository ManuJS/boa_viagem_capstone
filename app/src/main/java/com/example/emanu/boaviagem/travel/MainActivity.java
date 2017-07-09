package com.example.emanu.boaviagem.travel;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emanu.boaviagem.R;
import com.example.emanu.boaviagem.adapter.TravelCursorAdapter;
import com.example.emanu.boaviagem.database.DBHelper;
import com.example.emanu.boaviagem.provider.TravelDataProvider;


/**
 * Created by emanu on 08/07/2017.
 */

public class MainActivity extends AppCompatActivity
        implements
        LoaderManager.LoaderCallbacks<Cursor>,
        OnClickListener,
        OnItemClickListener {

    TravelCursorAdapter mAdapter;

    EditText tripDetour;

    Button btnAddTrip;
    boolean isEditing;
    long currentMessageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_list);

        tripDetour = (EditText)
                findViewById(R.id.input_trip_detour);
//        btnAddTrip = (Button) findViewById(R.id.f);
//        btnAddTrip.setOnClickListener(this);


        mAdapter = new TravelCursorAdapter(this, null);
        getSupportLoaderManager().initLoader(0, null, this);

        ListView listView = (ListView)
                findViewById(R.id.travel_list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(
            int id, Bundle args) {

        return new CursorLoader(
                this,
                TravelDataProvider.CONTENT_URI,
                DBHelper.ALL_COLUMNS,
                null,
                null,
                DBHelper.COLUMN_ID_TRAVEL);
    }

    @Override
    public void onLoadFinished(
            Loader<Cursor> loader, Cursor cursor) {

        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onClick(View v) {
        String message = tripDetour.getText().toString();

        if (!isEditing && TextUtils.isEmpty(message)) {
            Toast.makeText(this, "Preencha a mensagem",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        tripDetour.getText().clear();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_DETOUR, message);

        if (isEditing) {
            String whereClause = DBHelper.COLUMN_ID_TRAVEL + " = ?";
            String[] whereArgs = new String[]{
                    String.valueOf(currentMessageId)};

            if (TextUtils.isEmpty(message)) {
                getContentResolver().delete(
                        TravelDataProvider.CONTENT_URI,
                        whereClause,
                        whereArgs);

            } else {
                getContentResolver().update(
                        TravelDataProvider.CONTENT_URI,
                        values,
                        whereClause,
                        whereArgs);
            }

        } else {
            getContentResolver().insert(
                    TravelDataProvider.CONTENT_URI,
                    values);
        }
        isEditing = false;
    }

    @Override
    public void onItemClick(AdapterView<?> adaptView,
                            View view, int position, long id) {

        Cursor cursor = mAdapter.getCursor();
        cursor.moveToPosition(position);

        long messageId = cursor.getLong(
                cursor.getColumnIndex(DBHelper.COLUMN_ID_TRAVEL));

        String messageText = cursor.getString(
                cursor.getColumnIndex(DBHelper.COLUMN_DETOUR));

        currentMessageId = messageId;
        tripDetour.setText(messageText);
        isEditing = true;
    }
}