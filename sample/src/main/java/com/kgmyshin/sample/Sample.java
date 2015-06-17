package com.kgmyshin.sample;

import android.app.Application;

import com.kgmyshin.goreinu.Goreinu;

/**
 * Created by kgmyshin on 15/06/18.
 */
public class Sample extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Goreinu.install(this);
    }
}
