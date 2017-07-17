package com.example.emanu.boaviagem.expense;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emanu.boaviagem.R;
import com.example.emanu.boaviagem.adapter.ExpenseCursorAdapter;
import com.example.emanu.boaviagem.adapter.TravelCursorAdapter;
import com.example.emanu.boaviagem.database.DBHelper;
import com.example.emanu.boaviagem.provider.BoaViagemProvider;
import com.example.emanu.boaviagem.travel.ListTravelActivity;

/**
 * Created by emanu on 08/07/2017.
 */

public class DetailExpenseActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper databaseHelper;
    EditText editTextExpenseDetail;
    Button buttonSaveExpense;
    ExpenseCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_details);
        editTextExpenseDetail = (EditText) findViewById(R.id.input_expense_description);
        mAdapter = new ExpenseCursorAdapter(this, null);
        buttonSaveExpense = (Button) findViewById(R.id.button_save_expense);
        buttonSaveExpense.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            databaseHelper.close();}
        catch (Exception e){
            Log.d("Deu ruim", "no voltar");
        }
    }

    @Override
    public void onClick(View view) {
        String expenseDetail = editTextExpenseDetail.getText().toString();

        ContentValues values = new ContentValues();
        Bundle bundle = getIntent().getExtras();


        values.put(DBHelper.COLUMN_EXPENSE_DESCRIPTION, expenseDetail);
         try {
            getContentResolver().insert(
                    BoaViagemProvider.CONTENT_URI_EXPENSE,
                    values);
            Toast.makeText(this, "gasto salvo", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DetailExpenseActivity.this, ListTravelActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "gasto nao salva", Toast.LENGTH_LONG).show();
        }
    }
}
