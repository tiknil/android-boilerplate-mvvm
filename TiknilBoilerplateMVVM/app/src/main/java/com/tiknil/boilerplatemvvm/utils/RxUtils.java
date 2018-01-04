package com.tiknil.boilerplatemvvm.utils;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static android.databinding.Observable.OnPropertyChangedCallback;

/**
 * Funzioni di utilità per la gestione degli oggetti reattivi
 */
public class RxUtils {

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

    public static <T> Observable<T> toObservableOnUIReset(@NonNull final ObservableField<T> observableField) {
        return toObservableReset(observableField).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<T> toObservableOnUI(@NonNull final ObservableField<T> observableField) {
        return toObservable(observableField).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<T> toObservableReset(@NonNull final ObservableField<T> observableField) {
        return toObs(observableField)
                .filter(observableField1 -> observableField1.get() != null)
                .doAfterNext(observableField1 -> observableField1.set(null))
                .map(ObservableField::get);
    }

    /**
     * Trasforma un oggetto ObservableField in un Observable RxJava
     *
     * @param observableField l'observableField
     * @param <T>             il genere di ObservableField
     * @return l'oggetto Observable RxJava generato
     */

    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> observableField) {
        return toObs(observableField)
                .filter(observableField1 -> observableField1.get() != null)
                .map(ObservableField::get);
    }

    private static <T> Observable<ObservableField<T>> toObs(@NonNull final ObservableField<T> observableField) {
        return Observable.create(emitter -> {
            final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableField) {
                        emitter.onNext(observableField);
                    }
                }
            };
            observableField.addOnPropertyChangedCallback(callback);
            emitter.setCancellable(() -> observableField.removeOnPropertyChangedCallback(callback));
        });
    }

    public static Observable<Boolean> toObservableOnUIReset(@NonNull final ObservableBoolean observableBoolean) {
        return toObservableReset(observableBoolean).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Boolean> toObservableOnUI(@NonNull final ObservableBoolean observableBoolean) {
        return toObservable(observableBoolean).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Boolean> toObservableReset(@NonNull final ObservableBoolean observableBoolean) {
        return toObs(observableBoolean)
                .filter(ObservableBoolean::get)
                .doAfterNext(observableBoolean1 -> observableBoolean1.set(false))
                .map(ObservableBoolean::get);
    }

    /**
     * Trasforma un oggetto ObservableBoolean in un Observable RxJava
     *
     * @param observableBoolean l'observableBoolean
     * @return l'oggetto Observable RxJava generato
     */

    public static Observable<Boolean> toObservable(@NonNull final ObservableBoolean observableBoolean) {
        return toObs(observableBoolean).map(ObservableBoolean::get);
    }

    private static Observable<ObservableBoolean> toObs(@NonNull final ObservableBoolean observableBoolean) {
        return Observable.create(emitter -> {
            final OnPropertyChangedCallback callback = new OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == observableBoolean) {
                        emitter.onNext(observableBoolean);
                    }
                }
            };
            observableBoolean.addOnPropertyChangedCallback(callback);
            emitter.setCancellable(() -> observableBoolean.removeOnPropertyChangedCallback(callback));
        });
    }

    public static <T> Observable<List<T>> toObservableOnUI(@NonNull final ObservableList<T> observableList) {
        return toObservable(observableList).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<List<T>> toObservable(@NonNull final ObservableList<T> observableList) {
        return Observable.create(emitter -> {
            final ObservableList.OnListChangedCallback<ObservableList<T>> callback =
                    new ObservableList.OnListChangedCallback<ObservableList<T>>() {
                        @Override
                        public void onChanged(ObservableList<T> ts) {

                        }

                        @Override
                        public void onItemRangeChanged(ObservableList<T> dataBindingObservableList, int i, int i1) {

                        }

                        @Override
                        public void onItemRangeInserted(ObservableList<T> dataBindingObservableList, int i, int i1) {
                            if (dataBindingObservableList == observableList) {
                                emitter.onNext(observableList);
                            }
                        }

                        @Override
                        public void onItemRangeMoved(ObservableList<T> dataBindingObservableList, int i, int i1, int i2) {

                        }

                        @Override
                        public void onItemRangeRemoved(ObservableList<T> dataBindingObservableList, int i, int i1) {
                            if (dataBindingObservableList == observableList) {
                                emitter.onNext(observableList);
                            }
                        }
                    };
            observableList.addOnListChangedCallback(callback);
            emitter.setCancellable(() -> observableList.removeOnListChangedCallback(callback));
        });
    }

    //endregion

    //region Protected, without modifier
    //endregion

    //region Private
    //endregion


    //region Override methods and callbacks
    //endregion

    //region Inner classes or interfaces
    //endregion
}
