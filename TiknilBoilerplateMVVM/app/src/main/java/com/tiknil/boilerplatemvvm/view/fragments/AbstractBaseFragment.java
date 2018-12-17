package com.tiknil.boilerplatemvvm.view.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tiknil.boilerplatemvvm.utils.ThreadUtils;
import com.tiknil.boilerplatemvvm.view.activities.AbstractBaseActivity;
import com.tiknil.boilerplatemvvm.viewmodel.AbstractBaseViewModel;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import dagger.android.support.AndroidSupportInjection;

/**
 * Fragment di base che racchiude le funzionalità comuni a tutti i fragment e predispone il link con il view model relativo
 */

public abstract class AbstractBaseFragment<T extends ViewDataBinding, V extends AbstractBaseViewModel> extends RxFragment {

    //region Inner enums
    //endregion


    //region Constants

    //endregion


    //region Instance Fields

    private AbstractBaseActivity mActivity;
    private T mViewDataBinding;
    private V mViewModel;
    private View mRootView;

    protected boolean isViewAppeared = false;
    protected Object params;
    protected boolean keyboardModeResizingView = false;
    protected DateFormat defaultTimeFormat;

    //endregion


    //region Class methods
    //endregion


    //region Constructors / Lifecycle

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getViewModel();
        mViewModel.onCreated();
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();

        setupUI();
        setupBinding();

        getViewModel().initData();

    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        getViewModel().onDestroy();
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getViewModel().setActivityReference(getActivity());

        if (keyboardModeResizingView) {
            initKeyboardModeResizingView();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        getViewModel().onStart();
        if (!isViewAppeared) {
            if (isThisFragmentTheCurrentVisible()) {
                if (getViewModel() != null && params != null) {
                    getViewModel().setParams(params);
                    this.params = null;
                }
                ThreadUtils.runOnUiThread(() -> {
                    getViewModel().onViewAppear();
                });
                isViewAppeared = true;
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        getViewModel().onStop();
    }

    //endregion

    //region Custom accessors

    public void setKeyboardModeResizingView(boolean keyboardModeResizingView) {
        this.keyboardModeResizingView = keyboardModeResizingView;
    }

    //endregion


    //region Public

    /**
     * Funzione chiamata per impostare i parametri al viewmodel
     *
     * @param params parametri da passare al view model
     */
    public void setParams(Object params) {
        this.params = params;
        if (getViewModel() != null) {
            getViewModel().setParams(params);
            this.params = null;
        }
    }

    /**
     * Nasconde la tastiera se visualizzata
     */
    public void hideKeyboard() {
        if (getActivity() != null && getActivity() instanceof AbstractBaseActivity) {
            ((AbstractBaseActivity) getActivity()).hideKeyboard();
        }
    }

    /**
     * Imposta la UI, va eseguito l'override nelle classe figlie
     */
    public void setupUI() {

    }

    /**
     * Imposta il binding delle variabili RxJava, va eseguito l'override nelle classe figlie
     */
    public void setupBinding() {
    }

    public DateFormat getDefaultDateFormat() {
        return DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
    }

    public void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    public DateFormat getDefaultTimeFormat() {
        if (defaultTimeFormat != null) {
            return defaultTimeFormat;
        } else {
            return new SimpleDateFormat("HH:mm", Locale.getDefault());
        }
    }

    @Subscribe
    public void onEvent(Object object) {
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

    /**
     * Metodo chiamato quando il fragment viene visualizzato
     */
    public void onViewAppear() {
        ThreadUtils.runOnUiThread(() -> {
            if (keyboardModeResizingView) {
                initKeyboardModeResizingView();
            }
            hideKeyboard();
            if (getViewModel() != null) {
                getViewModel().onViewAppear();
                isViewAppeared = true;
            }
        });
    }

    /**
     * Metodo chiamato quando il fragment viene nascosto
     */
    public void onViewDisappear() {
        isViewAppeared = false;
        resetKeyboardToStandardMode();
        hideKeyboard();
        ThreadUtils.runOnUiThread(() -> {
            if (getViewModel() != null) {
                getViewModel().onViewDisappear();
            }
        });
    }

    //endregion

    //region Protected, without modifier

    /**
     * Imposta il comportamento della tastiera
     */
    protected void initKeyboardModeResizingView() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
    }

    /**
     * Reimposta la tastiera al funzionamento standard
     */
    protected void resetKeyboardToStandardMode() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
    }
    //endregion

    //region Private

    /**
     * @return true se il fragment corrente è quello in cima allo stack, false altrimenti
     */
    private boolean isThisFragmentTheCurrentVisible() {
        List<Fragment> fragments = getFragmentManager().getFragments();
        return fragments.get(fragments.size() - 1) == this;
    }
    //endregion


    //region Override methods and callbacks
    //endregion

    //region Inner classes or interfaces
    //endregion

}
