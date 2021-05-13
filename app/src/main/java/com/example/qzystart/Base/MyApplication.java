package com.example.qzystart.Base;

import android.app.Application;
import android.content.Context;
import org.litepal.LitePal;

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate( );
        context = this;
        LitePal.initialize(this);
    }

    public static Context getContext(){
        return context;
    }

}