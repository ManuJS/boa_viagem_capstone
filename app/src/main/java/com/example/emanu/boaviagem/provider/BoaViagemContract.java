package com.example.emanu.boaviagem.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public interface BoaViagemContract extends BaseColumns {

    String AUTHORITY = "com.example.emanu.boaviagem";
    Uri BASE_URI = Uri.parse("content://"+ AUTHORITY);
    Uri URI_TRAVELS = Uri.withAppendedPath(BASE_URI, "travels");
    Uri URI_EXPENSES = Uri.withAppendedPath(BASE_URI, "expenses");

    String TABLE_TRAVEL = "Travel";
    String TABLE_EXPENSES = "Expenses";


    String TITULO = "titulo";
    String DESCRICAO = "descricao";
}
