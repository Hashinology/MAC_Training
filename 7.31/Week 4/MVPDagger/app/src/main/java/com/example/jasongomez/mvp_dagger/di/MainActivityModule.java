package com.example.jasongomez.mvp_dagger.di;

import android.content.Context;

import com.example.jasongomez.mvp_dagger.di.scopes.Activity;
import com.example.jasongomez.mvp_dagger.view.mainactivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Activity
    @Provides
    MainActivityPresenter providesMainActivityPresenter(Context context) {
        return new MainActivityPresenter();
    }
}
