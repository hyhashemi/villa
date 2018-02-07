package com.apnozhan.villa.views.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Y50-70 on 1/22/2018.
 */

public class FontHelper {
    private static FontHelper instance;
    private static Typeface persianTypeface;
    private static Typeface persianBoldTypeface;
    private static Typeface persianLightTypeface;

    private FontHelper(Context context) {
        persianTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSans.ttf");
        persianBoldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSans_Bold.ttf");
        persianLightTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSans_Light.ttf");
    }

    public static synchronized FontHelper getInstance(Context context) {
        if (instance == null)
            instance = new FontHelper(context);
        return instance;
    }

    public Typeface getPersianTextTypeface(int style) {
        if (style == 0) {
            return persianTypeface;
        }
        if (style == 1) {
            return persianBoldTypeface;
        } else return persianLightTypeface;
    }

    public static void setBold(TextView textView) {
        textView.setTypeface(getInstance(textView.getContext()).getPersianTextTypeface(Typeface.BOLD));
    }

}
