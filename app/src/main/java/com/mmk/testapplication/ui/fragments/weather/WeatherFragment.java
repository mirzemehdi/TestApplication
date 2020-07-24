package com.mmk.testapplication.ui.fragments.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mmk.testapplication.R;
import com.mmk.testapplication.databinding.FragmentWeatherBinding;
import com.mmk.testapplication.model.WeatherData;
import com.mmk.testapplication.ui.fragments.map.MapViewModel;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private FragmentWeatherBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherViewModel=new ViewModelProvider(this).get(WeatherViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentWeatherBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherViewModel.getWeather().observe(getViewLifecycleOwner(), weatherData -> {
            binding.setWeather(weatherData);
        });
    }
}