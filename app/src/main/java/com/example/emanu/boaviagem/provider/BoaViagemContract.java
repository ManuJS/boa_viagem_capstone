package com.example.emanu.boaviagem.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public interface BoaViagemContract extends BaseColumns {

    public static final String AUTHORITY = "com.example.emanu.boaviagem";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    public static final String BASE_PATH_TRAVELS = "travels";
    public static final String BASE_PATH_EXPENSES = "expenses";

    public static final class Travel{
        public static final Uri CONTENT_URI_TRAVEL = Uri.parse(
                "content://" + AUTHORITY + "/" + BASE_PATH_TRAVELS);

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" +
                "vnd.br.com.casadocodigo.boaviagem.provider/viagem";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, BASE_PATH_TRAVELS);
        public static final String _ID = "_id";
        public static final String DESTINO = "destino";
        public static final String DATA_CHEGADA = "data_chegada";
        public static final String DATA_SAIDA = "data_saida";
        public static final String ORCAMENTO = "orcamento";
        public static final String QUANTIDADE_PESSOAS = "quantidade_pessoas";
    }

    public static final class Expense{
        public static final Uri CONTENT_URI_EXPENSE = Uri.parse(
                "content://" + AUTHORITY + "/" + BASE_PATH_EXPENSES);

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, BASE_PATH_EXPENSES);
        public static final String _ID = "_id";
        public static final String VIAGEM_ID = "viagem_id";
        public static final String CATEGORIA = "categoria";
        public static final String DATA = "data";
        public static final String DESCRICAO = "descricao";
        public static final String LOCAL = "local";
    }
}
