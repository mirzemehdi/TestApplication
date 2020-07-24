package com.mmk.testapplication.utils;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindUtils {
    @BindingAdapter("isVisible")
    public static void setVisibility(View view,Boolean isVisible){
        if (isVisible) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }
}
