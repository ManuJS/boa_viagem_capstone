package com.example.emanu.boaviagem.expense;

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
import com.example.emanu.boaviagem.adapter.ExpenseCursorAdapter;
import com.example.emanu.boaviagem.database.DBHelper;
import com.example.emanu.boaviagem.provider.BoaViagemProvider;
import com.facebook.stetho.Stetho;

public class ListExpenseActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    ExpenseCursorAdapter expenseCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_list);
        Stetho.initializeWithDefaults(this);
        expenseCursorAdapter = new ExpenseCursorAdapter(this, null);
        getSupportLoaderManager().initLoader(0, null, this);

        ListView listView = (ListView) findViewById(R.id.expense_list);
        listView.setAdapter(expenseCursorAdapter);
        FloatingActionButton fab_add_new_expense = (FloatingActionButton) findViewById(R.id.fab_add_new_expense);

        fab_add_new_expense.setOnClickListener(new View.OnClickListener() {
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
                BoaViagemProvider.CONTENT_URI_EXPENSE,
                DBHelper.ALL_COLUMNS_EXPENSE,
                null,
                null,
                DBHelper.COLUMN_ID_EXPENSE);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        expenseCursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        expenseCursorAdapter.swapCursor(null);
    }
}
