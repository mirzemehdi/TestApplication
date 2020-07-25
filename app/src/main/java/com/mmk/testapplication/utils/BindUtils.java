package com.mmk.testapplication.utils;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

/**
 * This class is for additional bind functions for views
 */

public class BindUtils {

    //Change visibility from xml using boolean
    @BindingAdapter("isVisible")
    public static void setVisibility(View view,Boolean isVisible){
        if (isVisible) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }

    //Loading image from url using Picasso library, for xml use just type imageUrl="urlLink"
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView,String url){
        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);

    }



}
