package com.apnozhan.villa;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Y50-70 on 1/22/2018.
 */

public class Villa extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSans.ttf")
                .setFontAttrId(com.apnozhan.villa.R.attr.fontPath)
                .build()
        );


    }
}
