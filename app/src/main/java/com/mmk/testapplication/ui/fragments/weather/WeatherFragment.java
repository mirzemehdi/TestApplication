package com.mmk.testapplication.ui.fragments.weather;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private Animation moveLeftAnimation;
    private Animation bounceAnimation ;
    private Animation fadeAnimation;
    private Animation blinkAnimation;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherViewModel=new ViewModelProvider(this).get(WeatherViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentWeatherBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(weatherViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupAnimations();
        weatherViewModel.getWeather().observe(getViewLifecycleOwner(), weatherData -> {

            moveLeftAnimation.start();
            bounceAnimation.start();
            fadeAnimation.start();
            blinkAnimation.start();
        });
    }

    private void setupAnimations() {
         moveLeftAnimation= AnimationUtils.loadAnimation(getContext(),R.anim.move_left);
         bounceAnimation= AnimationUtils.loadAnimation(getContext(),R.anim.bounce);
         fadeAnimation=AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
         blinkAnimation=AnimationUtils.loadAnimation(getContext(),R.anim.blink);


        binding.cardViewWeatherHumidity.setAnimation(moveLeftAnimation);
        binding.cardViewWeatherSpeed.setAnimation(moveLeftAnimation);
        binding.textViewWeatherCountry.setAnimation(bounceAnimation);
        binding.imageViewWeatherIcon.setAnimation(fadeAnimation);
        binding.textViewWeatherValue.setAnimation(fadeAnimation);
        binding.textViewWeatherDescription.setAnimation(blinkAnimation);
        binding.imageViewWeatherLocation.setAnimation(bounceAnimation);
    }
}