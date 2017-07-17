package com.example.emanu.boaviagem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emanu on 08/07/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME_TRAVELS = "travels";
    public static final String COLUMN_ID_TRAVEL = "_id";
    public static final String COLUMN_DETOUR = "detour";

    public static final String TABLE_NAME_EXPENSES = "expenses";
    public static final String COLUMN_ID_EXPENSE = "_id";
    public static final String COLUMN_ID_TRAVEL_EXPENSE = "_id";
    public static final String COLUMN_EXPENSE_DETAIL = "expense_description";


    public static final String[] ALL_COLUMNS_TRAVEL = {
            COLUMN_ID_TRAVEL, COLUMN_DETOUR
    };

    public static final String[] ALL_COLUMNS_EXPENSE = {
            COLUMN_ID_EXPENSE, COLUMN_EXPENSE_DETAIL
    };

    private static final String NOME_BANCO = "dbTravels";
    private static final int VERSAO_BANCO = 1;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME_TRAVELS + " (" +
                        COLUMN_ID_TRAVEL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_DETOUR + " TEXT )");

        db.execSQL(
                "CREATE TABLE " + TABLE_NAME_EXPENSES + " (" +
                        COLUMN_ID_EXPENSE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_EXPENSE_DETAIL + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
    }
}
