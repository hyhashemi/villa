package com.apnozhan.villa.views.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Y50-70 on 1/23/2018.
 */

public class ImageLoader {
    public static void loadImage(String url, ImageView imageView){
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
