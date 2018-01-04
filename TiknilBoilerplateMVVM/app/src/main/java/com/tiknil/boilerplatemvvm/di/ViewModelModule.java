package com.tiknil.boilerplatemvvm.di;

import android.content.Context;

import com.tiknil.boilerplatemvvm.services.activitynavigator.IActivityNavigator;
import com.tiknil.boilerplatemvvm.services.api.IApiService;
import com.tiknil.boilerplatemvvm.services.cache.ICacheService;
import com.tiknil.boilerplatemvvm.services.fragmentnavigator.IFragmentNavigator;
import com.tiknil.boilerplatemvvm.viewmodel.SplashScreenViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo Dagger2 di che indica come injectare i view model con le dipendenze di ciascuno
 */

@Module
public class ViewModelModule {

    // Activity

    @Provides
    SplashScreenViewModel provideSplashScreenViewModel(Context context, IActivityNavigator activityNavigator, IFragmentNavigator fragmentNavigator, IApiService apiService, ICacheService cacheService) {
        return new SplashScreenViewModel(context, activityNavigator, fragmentNavigator, apiService, cacheService);
    }

}
