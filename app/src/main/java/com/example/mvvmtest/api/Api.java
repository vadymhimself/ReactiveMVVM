package com.example.mvvmtest.api;

import android.os.Looper;
import android.support.annotation.MainThread;

import com.example.mvvmtest.model.BigData;
import com.example.mvvmtest.model.Time;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by Vadim Ovcharenko
 * 11.05.16.
 */
public class Api {
    public static Api getInstance() {
        return new Api();
    }

    public Observable<Time> getTime() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .map(aLong -> System.currentTimeMillis())
                .map(Date::new)
                .map(Date::toString)
                .map(Time::new);
    }

    public Observable<BigData> getBigData() {
        return Observable.timer(5, TimeUnit.SECONDS)
                .map(aLong -> new BigData("Some really big data..."));
    }
}
