package com.baseapplication.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

/**
 * Created by Keval on 20-Dec-16.
 * General utility functions.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public class Utils {

    public static boolean checkIfHasCamera(@NonNull Context context){
        PackageManager pm = context.getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}
