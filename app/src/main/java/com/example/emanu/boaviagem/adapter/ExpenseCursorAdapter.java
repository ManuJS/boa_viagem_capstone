package com.example.emanu.boaviagem.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emanu.boaviagem.R;
import com.example.emanu.boaviagem.database.DBHelper;

/**
 * Created by emanu on 08/07/2017.
 */

public class ExpenseCursorAdapter extends SimpleCursorAdapter {

    private static final int LAYOUT =
            R.layout.item_list_expense;

    public ExpenseCursorAdapter(
            Context context, Cursor cursor) {

        super(context, LAYOUT, cursor,
                DBHelper.ALL_COLUMNS, null, 0);
    }

    @Override
    public void bindView(View view, Context context,
                         Cursor cursor) {

        TextView txtMessage = (TextView)
                view.findViewById(R.id.travel_detour);
        TextView txtId = (TextView)
                view.findViewById(R.id.id_travel);

        txtId.setText(
                cursor.getString(
                        cursor.getColumnIndex(
                                DBHelper.COLUMN_ID)));
        txtMessage.setText(
                cursor.getString(
                        cursor.getColumnIndex(
                                DBHelper.COLUMN_DETOUR)));
    }

    @Override
    public View newView(Context contex, Cursor cursor,
                        ViewGroup viewGroup) {

        return LayoutInflater.from(contex).inflate(
                LAYOUT, null);
    }
}