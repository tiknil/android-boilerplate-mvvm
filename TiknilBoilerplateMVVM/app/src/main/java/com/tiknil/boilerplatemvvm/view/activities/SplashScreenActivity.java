package com.tiknil.boilerplatemvvm.view.activities;

import com.tiknil.boilerplatemvvm.BR;
import com.tiknil.boilerplatemvvm.R;
import com.tiknil.boilerplatemvvm.viewmodel.SplashScreenViewModel;

import javax.inject.Inject;

/**
 * Activity che implementa la splashscreen
 */

public class SplashScreenActivity extends AbstractBaseActivity {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields

    @Inject
    SplashScreenViewModel viewModel;

    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle
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

    @Override
    public SplashScreenViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splashscreen;
    }

    //endregion

    //region Inner classes or interfaces
    //endregion
}
