package com.tiknil.boilerplatemvvm.di;

import com.tiknil.boilerplatemvvm.TiknilBoilerplateMVVMApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Compomente Dagger2 dell'app che fa da punto di riferimento per la dependency injection dell'app
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, BuildersModule.class})
public interface AppComponent {

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

    /**
     * Esegue l'injection dell'app
     * @param intercheckSMApplication
     */
    void inject(TiknilBoilerplateMVVMApp intercheckSMApplication);

    //endregion

    //region Protected, without modifier
    //endregion

    //region Private
    //endregion


    //region Override methods and callbacks
    //endregion

    //region Inner classes or interfaces

    /**
     * Interfaccia per il builder di AppComponent
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(TiknilBoilerplateMVVMApp intercheckSMApplication);

        AppComponent build();
    }

    //endregion

}
