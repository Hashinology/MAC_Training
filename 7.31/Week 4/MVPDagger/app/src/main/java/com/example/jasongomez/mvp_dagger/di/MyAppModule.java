package com.example.jasongomez.mvp_dagger.di;

import android.content.Context;

import com.example.jasongomez.mvp_dagger.di.scopes.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class MyAppModule {

    private Context context;

    public MyAppModule(Context context) {
        this.context = context;
    }

    @Application
    @Provides
    public Context providesContext() {
        return context;
    }
}
