package com.tiknil.boilerplatemvvm.utils;

import android.content.Context;
import android.content.res.Resources;

import timber.log.Timber;

/**
 * Utilità per le stringhe
 */

public class StringUtils {

    /**
     * @param value valore da controllare
     * @return true se la stringa è nulla o vuota
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * @param line valore da capitalizzare
     * @return valore con la prima lettera maiuscola
     */
    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public static String truncate(String input, int maxLen, String trailing) {
        int trailingLength = trailing.length();
        if (input == null)
            return null;
        if ((input.length() < maxLen) || (maxLen < trailingLength))
            return input;
        return input.substring(0, maxLen - trailingLength) + trailing;
    }

    /**
     * Recupera il testo localizzato dalle risorse dell'app in base alla sua chiave passata come parametro
     *
     * @param context contesto dell'app da cui recuperare le risorse
     * @param aString chiave del testo localizzato da recuperare
     * @return testo localizzato
     */
    public static String getStringByName(Context context, String aString) {
        String result = "";
        try {
            String packageName = context.getPackageName();
            int resId = context.getResources().getIdentifier(aString, "string", packageName);
            result = context.getString
                    (resId);
        } catch (Resources.NotFoundException notFoundException) {
            Timber.w("Risorsa non trovata", notFoundException);
        }
        return result;
    }
}
