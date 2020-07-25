package com.mmk.testapplication.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mmk.testapplication.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class AppModule {

    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreference(Application application) {
        SharedPreferences sharedPreferences=application
                .getSharedPreferences(Constants.PREF_CALCULATOR, Context.MODE_PRIVATE);
        return sharedPreferences;

    }
}
