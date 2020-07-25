package com.mmk.testapplication.ui.fragments.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mmk.testapplication.R;
import com.mmk.testapplication.databinding.FragmentCalculatorBinding;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class CalculatorFragment extends Fragment {
    private FragmentCalculatorBinding binding;
    private CalculatorViewModel calculatorViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculatorViewModel= new ViewModelProvider(this).get(CalculatorViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentCalculatorBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(calculatorViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observe();
    }

    private void observe() {
        calculatorViewModel.getEqualButton().observe(getViewLifecycleOwner(), aVoid -> {

        });
    }
}