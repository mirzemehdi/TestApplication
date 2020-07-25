package com.mmk.testapplication.ui.fragments.weather;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mmk.testapplication.R;
import com.mmk.testapplication.model.ResponseWeatherTemp;
import com.mmk.testapplication.model.WeatherData;
import com.mmk.testapplication.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class WeatherViewModel extends AndroidViewModel {
    private final WeatherRepository weatherRepository;
    private CompositeDisposable disposable;
    private final MutableLiveData<WeatherData> weather = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);


    @ViewModelInject
    public WeatherViewModel(@NonNull Application application, WeatherRepository weatherRepository) {
        super(application);
        this.weatherRepository = weatherRepository;
        disposable = new CompositeDisposable();
        fetchWeatherData();
    }

    public LiveData<WeatherData> getWeather() {
        return weather;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }


    //Fetchs weatherData from API using RxJava
    private void fetchWeatherData() {
        isLoading.setValue(true);
        disposable.add(weatherRepository.getWeatherInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseWeatherTemp>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ResponseWeatherTemp responseWeatherTemp) {
                        WeatherData weatherData = new WeatherData(
                                responseWeatherTemp.getCurrent().getTemp(),
                                responseWeatherTemp.getCurrent().getWeather().get(0).getDescription(),
                                responseWeatherTemp.getCurrent().getWind_speed(),
                                responseWeatherTemp.getCurrent().getHumidity(),
                                getApplication().getString(R.string.text_baku),
                                responseWeatherTemp.getCurrent().getWeather().get(0).getIcon()
                        );
                        weather.setValue(weatherData);
                        isLoading.setValue(false);

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Timber.e(e);
                        isLoading.setValue(false);
                        Toast.makeText(getApplication(), getApplication().getString(R.string.error_server), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }


    }
}
