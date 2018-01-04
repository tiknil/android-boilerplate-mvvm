package com.tiknil.boilerplatemvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableBoolean;

import com.tiknil.boilerplatemvvm.services.activitynavigator.IActivityNavigator;
import com.tiknil.boilerplatemvvm.services.api.IApiService;
import com.tiknil.boilerplatemvvm.services.cache.ICacheService;
import com.tiknil.boilerplatemvvm.services.fragmentnavigator.IFragmentNavigator;
import com.tiknil.boilerplatemvvm.utils.RxUtils;

/**
 * View model relativo alla splash screen
 */

public class SplashScreenViewModel extends BaseViewModel {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields
    public ObservableBoolean isGooglePlayServicesChecked = new ObservableBoolean(false);
    private boolean alreadyGone = false;
    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    public SplashScreenViewModel(Context context, IActivityNavigator activityNavigator, IFragmentNavigator fragmentNavigator, IApiService restServices, ICacheService cacheService) {
        super(context, activityNavigator, fragmentNavigator, restServices, cacheService);
    }

    //endregion


    //region Custom accessors
    //endregion


    //region Public

    /**
     * Esegue le logiche per visualizzare la pagina successiva
     */
    public void showNextPage() {}

    //endregion

    //region Protected, without modifier
    //endregion

    //region Private
    //endregion


    //region Override methods and callbacks

    @Override
    public void onStart() {
        super.onStart();
        if (isGooglePlayServicesChecked.get() && !alreadyGone) {
            showNextPage();
        }
    }

    @Override
    public void setupBindingChains() {
        super.setupBindingChains();

        RxUtils.toObservable(isGooglePlayServicesChecked)
                .subscribe(isGooglePlayServicesChecked -> {
                    if (!alreadyGone && isGooglePlayServicesChecked) {
                        showNextPage();
                    }
                });
    }

    //endregion

    //region Inner classes or interfaces
    //endregion
}
