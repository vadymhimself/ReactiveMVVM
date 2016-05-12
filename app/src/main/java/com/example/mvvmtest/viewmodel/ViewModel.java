package com.example.mvvmtest.viewmodel;

import rx.Observable;

import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import rx.Observable;

/**
 * Created by Vadim Ovcharenko
 * 11.05.16.
 */
public class ViewModel<T> extends BaseObservable {

    private ViewDataBinding dataBinding;
    private int variableId;
    private T model;

    public ViewModel(Observable<T> modelObservable) {
        modelObservable.subscribe(model -> bindInternal(model, dataBinding, variableId)); // TODO: onError, retries?
    }

    public final void bind(ViewDataBinding dataBinding, int variableId) {
        assert dataBinding != null : "cannot bind null";
        this.dataBinding = dataBinding;
        this.variableId = variableId;
        bindInternal(model, dataBinding, variableId);
    }

    private void bindInternal(T model, ViewDataBinding dataBinding, int variableId) {
        this.model = model;
        if (model == null) return; // nothing to bind
        dataBinding.setVariable(variableId, model);
    }
}
