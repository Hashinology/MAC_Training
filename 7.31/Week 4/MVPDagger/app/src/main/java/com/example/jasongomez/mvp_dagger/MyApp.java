package com.example.jasongomez.mvp_dagger;

import android.app.Application;

import com.example.jasongomez.mvp_dagger.di.DaggerMyAppComponent;
import com.example.jasongomez.mvp_dagger.di.MyAppComponent;
import com.example.jasongomez.mvp_dagger.di.MyAppModule;

public class MyApp extends Application {

    private MyAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createComponent();
        appComponent.inject(this);
    }

    public MyAppComponent getAppComponent() {

        if (appComponent == null) {
            appComponent = createComponent();
        }
        return appComponent;
    }

    public MyAppComponent createComponent() {
        appComponent = DaggerMyAppComponent.builder().myAppModule(new MyAppModule(this)).build();
        return appComponent;
    }
}
