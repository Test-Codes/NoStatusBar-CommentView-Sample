package com.thefinestartist.myapplication;

import android.app.Application;

import com.thefinestartist.Base;

/**
 * Created by TheFinestArtist on 3/21/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Base.initialize(this);
    }
}
