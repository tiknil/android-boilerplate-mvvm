package com.tiknil.boilerplatemvvm.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

/**
 /**
 * Classe di utilità per recuperare da ovunque i font dell'app
 */

public class TiknilBoilerplateMVVMFonts {

    /**
     * @return il font dell'app di tipo "Regular"
     */
    public static Typeface getRegularTypeface(@NonNull Context context) {
        // TODO: settare il file del font nella variabile type
        String type = "fonts/";
        return Typeface.createFromAsset(context.getAssets(), type);
    }


    /**
     * @return il font dell'app di tipo "Bold"
     */
    public static Typeface getBoldTypeface(@NonNull Context context) {
        // TODO: settare il file del font nella variabile type
        String type = "fonts/";
        return Typeface.createFromAsset(context.getAssets(), type);
    }

}
