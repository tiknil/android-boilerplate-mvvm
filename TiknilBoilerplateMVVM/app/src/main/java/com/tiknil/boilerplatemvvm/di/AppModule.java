package com.tiknil.boilerplatemvvm.di;

import android.content.Context;

import com.tiknil.boilerplatemvvm.TiknilBoilerplateMVVMApp;
import com.tiknil.boilerplatemvvm.services.activitynavigator.ActivityNavigator;
import com.tiknil.boilerplatemvvm.services.activitynavigator.IActivityNavigator;
import com.tiknil.boilerplatemvvm.services.api.ApiService;
import com.tiknil.boilerplatemvvm.services.api.IApiService;
import com.tiknil.boilerplatemvvm.services.cache.CacheService;
import com.tiknil.boilerplatemvvm.services.cache.ICacheService;
import com.tiknil.boilerplatemvvm.services.fragmentnavigator.FragmentNavigator;
import com.tiknil.boilerplatemvvm.services.fragmentnavigator.IFragmentNavigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo Dagger2 dell'app che definisce come gestire e creare le istanze che vengono injectate all'interno del progetto
 */

@Module
public class AppModule {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields
    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle
    //endregion


    //region Custom accessors
    //endregion


    //region Public

    @Provides
    @Singleton
    Context provideContext() {
        return TiknilBoilerplateMVVMApp.getApp().getApplicationContext();
    }

    @Provides
    @Singleton
    ICacheService provideCacheService(Context context) {
        return new CacheService(context);
    }

    @Provides
    @Singleton
    IFragmentNavigator provideFragmentNavigator(Context context) {
        return new FragmentNavigator(context);
    }

    @Provides
    @Singleton
    IActivityNavigator provideActivityNavigator(Context context) {
        return new ActivityNavigator(context);
    }

    @Provides
    IApiService provideApiService(Context context, ICacheService cacheService) {
        return new ApiService(context, cacheService);
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
