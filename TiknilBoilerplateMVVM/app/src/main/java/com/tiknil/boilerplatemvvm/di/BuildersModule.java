package com.tiknil.boilerplatemvvm.di;

import com.tiknil.boilerplatemvvm.view.activities.SplashScreenActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Modulo Dagger2 astratto che definisce un metodo per ciascun viewmodel che può essere injettato nell'app
 */

@Module
public abstract class BuildersModule {

    // Activity

    @ContributesAndroidInjector(modules = {ViewModelModule.class})
    abstract SplashScreenActivity contributeSplashScreenActivityInjector();

}
