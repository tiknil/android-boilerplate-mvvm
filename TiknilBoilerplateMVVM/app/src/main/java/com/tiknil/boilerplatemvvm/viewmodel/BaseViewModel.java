package com.tiknil.boilerplatemvvm.viewmodel;

import android.content.Context;

import com.tiknil.boilerplatemvvm.services.activitynavigator.IActivityNavigator;
import com.tiknil.boilerplatemvvm.services.api.IApiService;
import com.tiknil.boilerplatemvvm.services.cache.ICacheService;
import com.tiknil.boilerplatemvvm.services.fragmentnavigator.IFragmentNavigator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Classe di base che raggruppa le funzionalità generiche dei view model e instazia i metodi
 * della classe astratta padre
 */

public class BaseViewModel extends AbstractBaseViewModel {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields
    protected DateFormat defaultDateFormat;
    protected DateFormat defaultTimeFormat;
    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    public BaseViewModel(Context context, IActivityNavigator activityNavigator, IFragmentNavigator fragmentNavigator, IApiService apiService, ICacheService cacheService) {
        super(context, activityNavigator, fragmentNavigator, apiService, cacheService);
    }

    //endregion


    //region Custom accessors
    //endregion


    //region Public

    public DateFormat getDefaultDateFormat() {
        if (defaultDateFormat != null) {
            return defaultDateFormat;
        } else {
            return DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        }
    }

    public DateFormat getDefaultTimeFormat() {
        if (defaultTimeFormat != null) {
            return defaultTimeFormat;
        } else {
            return new SimpleDateFormat("HH:mm", Locale.getDefault());
        }
    }

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
