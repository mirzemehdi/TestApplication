package com.mmk.testapplication.di;


import com.mmk.testapplication.BuildConfig;
import com.mmk.testapplication.network.ApiService;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import javax.inject.Singleton;

import static com.mmk.testapplication.utils.Constants.BASE_URL;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class NetworkModule {


    @Provides
    @Singleton
    public static HttpLoggingInterceptor provideLogging() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;

    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) builder.addInterceptor(interceptor);

        return builder.build();
    }

    @Provides
    @Singleton
    public static RxJava3CallAdapterFactory provideRxJavaCallAdapter() {
        return RxJava3CallAdapterFactory.create();
    }


    @Provides
    @Singleton
    public static MoshiConverterFactory provideMoshiConvertor() {
        Moshi moshi = new Moshi.Builder().build();
        return MoshiConverterFactory.create(moshi);
    }


    @Provides
    @Singleton
    public static Retrofit provideRetrofit(
            MoshiConverterFactory converterFactory,
            RxJava3CallAdapterFactory adapterFactory,
            OkHttpClient client
    ) {
        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    public static ApiService.WeatherApiService provideWeatherApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.WeatherApiService.class);
    }


}
