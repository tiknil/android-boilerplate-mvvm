package com.tiknil.boilerplatemvvm.services.activitynavigator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tiknil.boilerplatemvvm.view.activities.AbstractBaseActivity;

import javax.inject.Inject;

/**
 * Implementa il protocollo definito in IActivityNavigator
 * Gestisce la navigazione dell'app tra activity
 */

public class ActivityNavigator implements IActivityNavigator {

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
    public ActivityNavigator(Context context) {
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

    /**
     * Avvia l'activity passata come classe
     *
     * @param activity          activity da cui lanciare l'intent
     * @param activityToOpen    classe da avviare
     */
    @Override
    public void openActivity(AbstractBaseActivity activity, Class activityToOpen) {
        openActivity(activity, activityToOpen, null);
    }

    /**
     * Avvia l'activity passata come classe con l'opzionale oggetto di parametri
     *
     * @param activity          activity da cui lanciare l'intent
     * @param activityToOpen    classe da avviare
     * @param data              parametri opzionali da inviare all'activity
     */

    @Override
    public void openActivity(AbstractBaseActivity activity, Class activityToOpen, Bundle data) {
        Intent i = new Intent(activity, activityToOpen);
        if (data != null) {
            i.putExtras(data);
        }
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); //deve rimanere sempre una sola activity
        activity.startActivity(i);
        activity.finish();
    }

    //endregion

    //region Inner classes or interfaces
    //endregion

}
