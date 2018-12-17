package com.tiknil.boilerplatemvvm.view.activities;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.tiknil.boilerplatemvvm.TiknilBoilerplateMVVMApp;
import com.tiknil.boilerplatemvvm.utils.NetworkUtils;
import com.tiknil.boilerplatemvvm.viewmodel.AbstractBaseViewModel;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Classe astratta di base ereditata da tutte le activity che raggruppa le funzionalità comuni
 * e implementa l'impostazione di base con i view model e il binding con i componenti della view
 */
public abstract class AbstractBaseActivity<T extends ViewDataBinding, V extends AbstractBaseViewModel> extends RxFragmentActivity {

    //region Inner enums
    //endregion


    //region Constants
    //endregion


    //region Instance Fields

    //UIFields

    private T mViewDataBinding;
    private V mViewModel;

    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
        ((TiknilBoilerplateMVVMApp) getApplicationContext()).setCurrentActivity(this);
        getViewModel().onCreated();
        getViewModel().setActivityNavigator(this);
        getViewModel().setActivityReference(this);
    }

    @Override
    protected void onDestroy() {
        getViewModel().onDestroy();
        clearActivityReferences();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        ((TiknilBoilerplateMVVMApp) getApplicationContext()).setCurrentActivity(this);
        getViewModel().onViewAppear();
        super.onResume();
    }

    @Override
    protected void onPause() {
        getViewModel().onViewDisappear();
        clearActivityReferences();
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        getViewModel().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        getViewModel().onStop();
    }

    //endregion


    //region Custom accessors
    //endregion


    //region Public

    /**
     * Nasconde la tastiera
     */
    public void hideKeyboard() {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * Verifica la presenza della connessione ad internet
     * @return true se c'è connessione, false altrimenti
     */
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    /**
     * Registra l'evento generico da gestire con l'EventBus
     * @param object oggetto generico
     */
    @Subscribe
    public void onEvent(Object object) {
    }

    /**
     * Ritorna l'oggetto databinding
     * @return l'oggetto databinding
     */
    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    //endregion

    //region Protected, without modifier

    /**
     * Recupera il valore intero di un colore dalle risorse
     *
     * @param resourceId id del colore
     * @return valore intero del colore
     */
    protected int getColorFromResources(@ColorRes int resourceId) {
        return ResourcesCompat.getColor(getResources(), resourceId, null);
    }

    /**
     * Esegue il binding tra gli observables custom della view, se necessario va eseguito l'override nelle classe figlie
     */
    protected void setupBindings() {}

    //endregion

    //region Private

    /**
     * Esegue il binding tra il layout della view e il viewmodel. Chiama il metodoo setupBinding per gli ulteriori binding custom
     */
    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
        setupBindings();
    }

    /**
     * Resetta il riferimento all'activity nell'applicazione
     */
    private void clearActivityReferences() {
        Activity currActivity = ((TiknilBoilerplateMVVMApp) getApplicationContext()).getCurrentActivity();
        if (this.equals(currActivity))
            ((TiknilBoilerplateMVVMApp) getApplicationContext()).setCurrentActivity(null);
    }

    //endregion


    //region Override methods and callbacks

    //endregion

    //region Inner classes or interfaces
    //endregion


}
