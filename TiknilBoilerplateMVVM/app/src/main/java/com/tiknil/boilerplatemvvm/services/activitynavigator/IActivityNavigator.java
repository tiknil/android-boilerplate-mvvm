package com.tiknil.boilerplatemvvm.services.activitynavigator;

import android.os.Bundle;

import com.tiknil.boilerplatemvvm.view.activities.AbstractBaseActivity;

/**
 * Interfaccia che definice i metodi per la navigazione tra activity
 */

public interface IActivityNavigator {

    void openActivity(AbstractBaseActivity activity, Class activityToOpen);

    void openActivity(AbstractBaseActivity activity, Class activityToOpen, Bundle data);
}
