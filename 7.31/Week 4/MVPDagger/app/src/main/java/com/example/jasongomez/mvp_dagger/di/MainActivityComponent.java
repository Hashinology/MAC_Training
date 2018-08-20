package com.example.jasongomez.mvp_dagger.di;

import com.example.jasongomez.mvp_dagger.di.scopes.Activity;
import com.example.jasongomez.mvp_dagger.view.mainactivity.MainActivity;

import dagger.Subcomponent;

@Activity
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
