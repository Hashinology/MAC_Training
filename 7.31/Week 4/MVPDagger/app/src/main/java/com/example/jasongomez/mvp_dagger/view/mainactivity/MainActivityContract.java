package com.example.jasongomez.mvp_dagger.view.mainactivity;

import android.content.Context;

import com.example.jasongomez.mvp_dagger.BasePresenter;
import com.example.jasongomez.mvp_dagger.BaseView;

public interface MainActivityContract {

    interface View extends BaseView {

        void isPersonSaved(boolean isSaved);

        void sendPerson(String person);
    }

    interface Presenter extends BasePresenter<View> {

        void savePerson(String person);

        void getPerson();

        void setContext(Context context);
    }
}
