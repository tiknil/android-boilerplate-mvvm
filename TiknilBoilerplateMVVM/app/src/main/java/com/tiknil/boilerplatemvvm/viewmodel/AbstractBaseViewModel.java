package com.tiknil.boilerplatemvvm.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.StringRes;

import com.tiknil.boilerplatemvvm.services.activitynavigator.IActivityNavigator;
import com.tiknil.boilerplatemvvm.services.api.IApiService;
import com.tiknil.boilerplatemvvm.services.cache.ICacheService;
import com.tiknil.boilerplatemvvm.services.fragmentnavigator.IFragmentNavigator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Classe astratta di base di tutti i view model
 */
public abstract class AbstractBaseViewModel extends ViewModel {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields

    protected final Context context;
    protected final IActivityNavigator activityNavigator;
    protected final IFragmentNavigator fragmentNavigator;
    protected final IApiService apiService;
    protected final ICacheService cacheService;
    protected Activity activityReference;
    protected List<Disposable> disposables = new ArrayList<>();

    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    public AbstractBaseViewModel(Context context, IActivityNavigator activityNavigator, IFragmentNavigator fragmentNavigator, IApiService apiService, ICacheService cacheService) {
        this.context = context;
        this.activityNavigator = activityNavigator;
        this.fragmentNavigator = fragmentNavigator;
        this.apiService = apiService;
        this.cacheService = cacheService;
    }

    //endregion


    //region Custom accessors

    public IActivityNavigator getActivityNavigator() {
        return activityNavigator;
    }

    public Context getContext() {
        return context;
    }

    public String getString(@StringRes int resId) {
        return getContext().getString(resId);
    }

    public IFragmentNavigator getFragmentNavigator() {
        return fragmentNavigator;
    }

    public IApiService getApiService() {
        return apiService;
    }

    public ICacheService getCacheService() {
        return cacheService;
    }

    public Activity getActivityReference() {
        return activityReference;
    }

    public void setActivityReference(Activity activityReference) {
        this.activityReference = activityReference;
    }

    //endregion


    //region Public

    /**
     * Metodo chiamato quando la view esegue il metodo onCreate, se necessario va eseguito l'override nelle classe figlie
     */

    public void onCreated() {}

    /**
     * Metodo chiamato quando la view viene visualizzata, se necessario va eseguito l'override nelle classe figlie
     */

    public void onViewAppear() {}

    /**
     * Metodo chiamato quando la view scompare, se necessario va eseguito l'override nelle classe figlie
     */

    public void onViewDisappear() {
        disposeDisposables();
    }

    /**
     * Metodo chiamato alla chiamata onStart dell'activity/fragment del flusso standard, se necessario va eseguito l'override nelle classe figlie
     */

    public void onStart() {}

    /**
     * Metodo chiamato alla chiamata onStop dell'activity/fragment del flusso standard, se necessario va eseguito l'override nelle classe figlie
     */

    public void onStop() {}

    /**
     * Metodo chiamato alla chiamata onDestroy dell'activity/fragment del flusso standard, se necessario va eseguito l'override nelle classe figlie
     */

    public void onDestroy() {}

    /**
     * Consente di settare dei parametri da utilizzare nella view, se necessario va eseguito l'override nelle classe figlie
     * @param params parametri da utilizzare nella view
     */

    public void setParams(Object params) {}

    /**
     * Imposta il binding delle variabili RxJava, se necessario va eseguito l'override nelle classe figlie
     */

    public void setupBindingChains() {}

    /**
     * Imposta i dati iniziali della view, se necessario va eseguito l'override nelle classe figlie
     */
    public void initData() {}

    /**
     * Da sovrascrivere nei figli ritornando "false" se si vuole impedire
     * che non sia possibile usare il pulsante "back" di sistema nella schermata attuale
     *
     * @return true per permettere il back di sistema, false altrimenti
     */
    public boolean canBack() {
        return true;
    }

    //endregion

    //region Protected, without modifier

    /**
     * Esegue il dispose di tutti i disposables del view model
     */
    protected void disposeDisposables() {
        if (disposables != null) {
            for (Disposable disposable : disposables) {
                if (!disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
        }
    }

    //endregion

    //region Private
    //endregion


    //region Override methods and callbacks
    //endregion

    //region Inner classes or interfaces
    //endregion

}
