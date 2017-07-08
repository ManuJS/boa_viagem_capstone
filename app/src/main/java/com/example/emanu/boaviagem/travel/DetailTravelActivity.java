package com.example.emanu.boaviagem.travel;

import android.app.Application;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emanu.boaviagem.R;
import com.example.emanu.boaviagem.adapter.TravelCursorAdapter;
import com.example.emanu.boaviagem.database.DBHelper;
import com.example.emanu.boaviagem.provider.TravelProvider;

import java.text.SimpleDateFormat;

/**
 * Created by emanu on 08/07/2017.
 */

public class DetailTravelActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper databaseHelper;
    EditText editTextTravelpDetour;
    Button buttonSaveTravel;
    TravelCursorAdapter mAdapter;
    boolean isEditing;
    long currentMessageId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_details);
        editTextTravelpDetour = (EditText) findViewById(R.id.input_trip_detour);
        mAdapter = new TravelCursorAdapter(this, null);
        buttonSaveTravel = (Button) findViewById(R.id.button_save_travel);
        buttonSaveTravel.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }

    @Override
    public void onClick(View view) {
        String travelDetour = editTextTravelpDetour.getText().toString();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_DETOUR, travelDetour);

        try {
            getContentResolver().insert(
                    TravelProvider.CONTENT_URI,
                    values);
            Toast.makeText(this, "viagem salva", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DetailTravelActivity.this, ListTravelActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "viagem nao salva", Toast.LENGTH_LONG).show();
        }


    }

}
