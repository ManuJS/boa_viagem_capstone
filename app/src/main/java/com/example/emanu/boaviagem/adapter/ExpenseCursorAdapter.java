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
                DBHelper.ALL_COLUMNS_EXPENSE, null, 0);
    }

    @Override
    public void bindView(View view, Context context,
                         Cursor cursor) {

        TextView txtId = view.findViewById(R.id.expense_detail);
        TextView txtExpenseDetail = view.findViewById(R.id.id_expense);

        txtId.setText(
                cursor.getString(
                        cursor.getColumnIndex(
                                DBHelper.COLUMN_ID_EXPENSE)));

        txtExpenseDetail.setText(
                cursor.getString(
                        cursor.getColumnIndex(
                                DBHelper.COLUMN_EXPENSE_DESCRIPTION)));
    }

    @Override
    public View newView(Context contex, Cursor cursor,
                        ViewGroup viewGroup) {

        return LayoutInflater.from(contex).inflate(
                LAYOUT, null);
    }
}