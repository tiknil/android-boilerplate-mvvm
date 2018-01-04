package com.tiknil.boilerplatemvvm.services.api;

import android.content.Context;

import com.tiknil.boilerplatemvvm.TiknilBoilerplateMVVMApp;
import com.tiknil.boilerplatemvvm.services.cache.ICacheService;

import javax.inject.Inject;

/**
 * Implementa le chiamate API definite nell'interfaccia IApiService
 */

public class ApiService implements IApiService {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields

    private TiknilBoilerplateMVVMApi intercheckSMApi;
    private ICacheService cacheService;
    private Context context;

    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    @Inject
    public ApiService(Context context, ICacheService cacheService) {
        this.context = context;
        this.cacheService = cacheService;
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
