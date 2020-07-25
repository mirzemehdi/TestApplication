package com.mmk.testapplication.ui.fragments.calculator;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mmk.testapplication.R;
import com.mmk.testapplication.databinding.FragmentCalculatorBinding;
import com.mmk.testapplication.utils.Constants;

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
        calculatorViewModel.getIsOperationCompleted().observe(getViewLifecycleOwner(), isCompleted -> {

            int bigTextSize = Constants.getPixelSizeFromId(this,R.dimen.expressionTextViewSize);
            int smallTextSize = Constants.getPixelSizeFromId(this,R.dimen.resultTextViewSize);
            Typeface typefaceBold = ResourcesCompat.getFont(getContext(),R.font.tthoves_bold);
            Typeface typefaceMedium = ResourcesCompat.getFont(getContext(),R.font.tthoves_medium);

            //If equal button is clicked then resultTextSize and font will be changed accordingly
            if (isCompleted){
                binding.textViewCalculatorResult.setTextSize(bigTextSize);
                binding.textViewCalculatorExpression.setTextSize(smallTextSize);

                binding.textViewCalculatorExpression.setTypeface(typefaceMedium);
                binding.textViewCalculatorResult.setTypeface(typefaceBold);

            }else {
                binding.textViewCalculatorResult.setTextSize(smallTextSize);
                binding.textViewCalculatorExpression.setTextSize(bigTextSize);

                binding.textViewCalculatorExpression.setTypeface(typefaceBold);
                binding.textViewCalculatorResult.setTypeface(typefaceMedium);
            }
        });
    }
}