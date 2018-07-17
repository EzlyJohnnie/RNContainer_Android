package com.johnnie.rncontainer_android.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Johnnie on 09/05/18.
 */

public class SingleToast {
    private static Toast mToast;

    private SingleToast() {
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        if(mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(context, text, duration);
        return mToast;
    }

    public static Toast makeText(Context context, int resId, int duration) {
        if(mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(context, resId, duration);
        return mToast;
    }
}