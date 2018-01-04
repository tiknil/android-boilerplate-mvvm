package com.tiknil.boilerplatemvvm;

import android.app.Activity;
import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.tiknil.boilerplatemvvm.di.AppComponent;
import com.tiknil.boilerplatemvvm.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.fabric.sdk.android.Fabric;

/**
 * Classe che identifica l'applicazione stessa.
 */

public class TiknilBoilerplateMVVMApp extends Application implements HasActivityInjector {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    private Activity currentActivity = null;

    private static TiknilBoilerplateMVVMApp app;

    AppComponent appComponent;



    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();

        // Inizializzazione di Crashlytics
        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }

        app = this;

        // Inizializzazione dell'AppComponent
        initDataComponent();

    }


    //endregion


    //region Custom accessors
    //endregion


    //region Public

    /**
     * Ritorna il riferimento all'app corrente
     * @return il riferimento all'app corrente
     */
    public static TiknilBoilerplateMVVMApp getApp() {
        return app;
    }

    /**
     * Ritorna l'activity corrente
     * @return l'activity corrente
     */
    public Activity getCurrentActivity() {
        return currentActivity;
    }

    /**
     * Setta l'activity corrente
     * @param currentActivity l'activity da settare come corrente
     */
    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    //endregion

    //region Protected, without modifier
    //endregion

    //region Private

    /**
     * Inizializza l'oggetto Dagger2 AppComponent
     */
    private void initDataComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();

        if (appComponent != null) {
            appComponent.inject(this);
        }
    }

    //endregion

    //region Override methods and callbacks

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    //endregion

    //region Inner classes or interfaces
    //endregion

}
