package com.johnnie.rncontainer_android.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.johnnie.rncontainer_android.ui.base.BaseRNActivity;
import com.johnnie.rncontainer_android.utils.RNHelper;

/**
 * Created by Johnnie on 17/07/18.
 */

public class RNTestAppActivity extends BaseRNActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, RNTestAppActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setupContentView() {
        View rnRootView = RNHelper.getRNRootView(this,
                RNHelper.TEST_APP_BUNDLE_NAME,
                RNHelper.TEST_APP_MODULE_NAME,
                null);

        setContentView(rnRootView);
    }
}
