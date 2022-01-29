package com.example.humors;

import android.app.Application;
import android.content.Context;

public class Humors extends Application {
    private static Humors instance;


    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
