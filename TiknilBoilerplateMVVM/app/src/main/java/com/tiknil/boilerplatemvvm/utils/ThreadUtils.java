package com.tiknil.boilerplatemvvm.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Tribbia Riccardo on 13/06/16.
 *
 * @TiKnil
 */
public class ThreadUtils {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields

    public static void runOnUiThread(Runnable runnable) {
        if (runnable != null) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle
    //endregion


    //region Custom accessors
    //endregion


    //region Public
    //endregion

    //region Protected, without modifier
    //endregion

    //region Private
    //endregion


    //region Override methods and callbacks
    //endregion

    //region Inner classes or interfaces
    //endregion
}
