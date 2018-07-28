package com.codingtest.comcast.comcastct.utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by alexlm on 7/27/18.
 */

public class ConfigUtils {
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
