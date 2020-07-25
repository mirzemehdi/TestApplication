package com.mmk.testapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mmk.testapplication.R;

public class Constants {
    public static final String API_KEY = "ba61e07aa9ffa2da06c658a6483c32d2";
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String WEATHER_ICONS_BASE_URL = "http://openweathermap.org/img/wn/";
    public static final String BAKU_LAT = "40.409264";
    public static final String BAKU_LNG = "49.867092";
    public static final String PREF_CALCULATOR = "CalculatorPref";
    public static final String KEY_RESULT = "resultKey";
    public static final String KEY_EXPRESSION = "expression";


    //Gets DP size in px
    public static int getPixelSizeFromId(Fragment fragment, int resourceId) {
        Resources resources=fragment.getResources();
        int sizeInPx = (int) (resources.getDimension(resourceId) / resources.getDisplayMetrics().density);
        return sizeInPx;
    }

    //For transformating given resourceId to BitMapDescriptor to set up Google Map marker icon
    public static BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }




}
