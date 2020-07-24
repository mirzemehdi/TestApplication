package com.mmk.testapplication.utils;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindUtils {
    @BindingAdapter("isVisible")
    public static void setVisibility(View view,Boolean isVisible){
        if (isVisible) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView,String url){
        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);

    }



}
