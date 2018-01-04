package com.tiknil.boilerplatemvvm.services.fragmentnavigator;

import android.content.Context;

import javax.inject.Inject;

/**
 * Implementa il protocollo definito in IFragmentNavigator
 * Gestisce la navigazione dell'app nei fragment
 */

public class FragmentNavigator implements IFragmentNavigator {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields

    private Context context;

    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    @Inject
    public FragmentNavigator(Context context) {
        this.context = context;
    }

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
