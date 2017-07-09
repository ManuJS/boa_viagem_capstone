package com.example.emanu.boaviagem.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.example.emanu.boaviagem.database.DBHelper;

/**
 * Created by emanu on 08/07/2017.
 */

public class TravelDataProvider extends ContentProvider {
    // Deve estar igual ao Manifest
    private static final String
            AUTHORITY = "com.example.emanu.boaviagem";
    // Tipo de acesso que retorna todas as mensagens
    private static final int TYPE_ALL_MESSAGES = 1;
    // Tipo de acesso que retorna apenas uma mensagem
    // usando o id da mesma
    private static final int TYPE_SINGLE_MESSAGE = 2;

    private static final String BASE_PATH = "messages";
    // É através dessa URI que acessamos nosso provider
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY + "/" + BASE_PATH);

    // Classe para checar se a Uri passada é valida
    private static final UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY,
                BASE_PATH, TYPE_ALL_MESSAGES);
        sUriMatcher.addURI(AUTHORITY,
                BASE_PATH + "/#", TYPE_SINGLE_MESSAGE);
    }

    private DBHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        // Ao criar o Provider, inicializamos o helper
        mOpenHelper = new DBHelper(getContext());
        return true; // success
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB =
                mOpenHelper.getWritableDatabase();
        long id = 0;

        switch (uriType) {
            case TYPE_ALL_MESSAGES:
                id = sqlDB.insert(
                        DBHelper.TABLE_NAME_TRAVELS,
                        null,
                        values);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown URI: " + uri);
        }

        getContext().getContentResolver()
                .notifyChange(uri, null);

        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {

        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB =
                mOpenHelper.getWritableDatabase();

        int rowsUpdated = 0;

        switch (uriType) {
            case TYPE_ALL_MESSAGES:
                rowsUpdated = sqlDB.update(
                        DBHelper.TABLE_NAME_TRAVELS,
                        values,
                        selection,
                        selectionArgs);
                break;

            case TYPE_SINGLE_MESSAGE:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(
                            DBHelper.TABLE_NAME_TRAVELS,
                            values,
                            DBHelper.COLUMN_ID_TRAVEL + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(
                            DBHelper.TABLE_NAME_TRAVELS,
                            values,
                            DBHelper.COLUMN_ID_TRAVEL +"="+ id +
                                    " and "+ selection,
                            selectionArgs);
                }
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown URI: " + uri);
        }

        getContext().getContentResolver()
                .notifyChange(uri, null);
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection,
                      String[] selectionArgs) {

        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB =
                mOpenHelper.getWritableDatabase();

        int rowsDeleted = 0;
        switch (uriType) {
            case TYPE_ALL_MESSAGES:
                rowsDeleted = sqlDB.delete(
                        DBHelper.TABLE_NAME_TRAVELS,
                        selection,
                        selectionArgs);
                break;

            case TYPE_SINGLE_MESSAGE:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(
                            DBHelper.TABLE_NAME_TRAVELS,
                            DBHelper.COLUMN_ID_TRAVEL + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(
                            DBHelper.TABLE_NAME_TRAVELS,
                            DBHelper.COLUMN_ID_TRAVEL +"="+ id +
                                    " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown URI: " + uri);
        }

        getContext().getContentResolver()
                .notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteQueryBuilder queryBuilder =
                new SQLiteQueryBuilder();

        queryBuilder.setTables(DBHelper.TABLE_NAME_TRAVELS);

        int uriType = sUriMatcher.match(uri);
        Cursor cursor = null;
        SQLiteDatabase db =
                mOpenHelper.getWritableDatabase();

        switch (uriType) {
            case TYPE_ALL_MESSAGES:
                cursor = queryBuilder.query(
                        db,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case TYPE_SINGLE_MESSAGE:
                queryBuilder.appendWhere(
                        DBHelper.COLUMN_ID_TRAVEL + "= ?");

                cursor = queryBuilder.query(
                        db,
                        projection,
                        selection,
                        new String[]{ uri.getLastPathSegment() },
                        null,
                        null,
                        null);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown URI: " + uri);
        }

        cursor.setNotificationUri(
                getContext().getContentResolver(), uri);

        return cursor;
    }
}