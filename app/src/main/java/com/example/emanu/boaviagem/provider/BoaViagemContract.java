package com.example.emanu.boaviagem.provider;

import android.net.Uri;

/**
 * Created by emanuellemenali on 10/07/17.
 */

public class BoaViagemContract {

    // Deve estar igual ao Manifest
    private static final String
            AUTHORITY = "com.example.emanu.boaviagem";
    // Tipo de acesso que retorna todas as mensagens

    private static final String BASE_PATH_TRAVEL = "travels";
    private static final String BASE_PATH_EXPANSE = "expenses";

    public static final Uri CONTENT_URI_BASE = Uri.parse(
            "content://" + AUTHORITY + "/" );




}
