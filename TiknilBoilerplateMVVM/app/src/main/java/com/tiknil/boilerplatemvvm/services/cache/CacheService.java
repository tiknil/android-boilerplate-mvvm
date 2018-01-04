package com.tiknil.boilerplatemvvm.services.cache;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Implementa il protocollo definito in ICacheService
 * Gestisce il salvataggio dei dati su disco e ne gestisce il mantenimento in memoria durante il funzionamento dell'app.
 * Viene utilizzato: shared preferences, Android KeyStore e cache.
 */

public class CacheService implements ICacheService {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields
    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    @Inject
    public CacheService(Context context) {

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
