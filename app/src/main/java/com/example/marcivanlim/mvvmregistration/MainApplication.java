package com.example.marcivanlim.mvvmregistration;

import android.app.Application;

import com.example.marcivanlim.mvvmregistration.DI.AppComponent;
import com.example.marcivanlim.mvvmregistration.DI.DaggerAppComponent;

public class MainApplication extends Application {

    private AppComponent appComponent;


    @Override
    public void onCreate()
    {
        super.onCreate();
        appComponent = createAppComponent();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent
                .builder()
                .build();
    }

}
