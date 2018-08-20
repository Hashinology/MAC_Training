package com.example.jasongomez.mvp_dagger.di;

import com.example.jasongomez.mvp_dagger.MyApp;
import com.example.jasongomez.mvp_dagger.di.scopes.Application;

import dagger.Component;

@Application
@Component(modules = MyAppModule.class)
public interface MyAppComponent {

    MainActivityComponent newMainActivityComponent(MainActivityModule module);

    void inject(MyApp myApp);
}
