package com.example.mvvmtest;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvvmtest.api.Api;
import com.example.mvvmtest.databinding.ActivityAdvancedBinding;
import com.example.mvvmtest.model.BigData;
import com.example.mvvmtest.viewmodel.ViewModel;
import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;

public class AdvancedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdvancedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_advanced);

        // init screen handler
        ScreenHandler screenHandler = new ScreenHandler();
        binding.setScreenHandler(screenHandler);

        // create big data provider observable
        Observable<BigData> bigDataObservable = RxView.clicks(binding.btnLoadBigData)
                .doOnNext(aVoid -> screenHandler.loading.set(true))
                .flatMap(aVoid -> Api.getInstance().getBigData())
                .doOnNext(bigData -> screenHandler.buttonText.set("Reload big data"))
                .doOnNext(bigData -> screenHandler.loading.set(false));

        // create and bind big data ViewModel
        ViewModel<BigData> bigDataViewModel = new ViewModel<>(bigDataObservable);
        bigDataViewModel.bind(binding, com.example.mvvmtest.BR.bigData);
    }

    public static class ScreenHandler {

        public ObservableBoolean loading = new ObservableBoolean();
        public ObservableField<String> buttonText = new ObservableField<>("load big data");

    }
}
