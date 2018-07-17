package com.johnnie.rncontainer_android.utils;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.johnnie.rncontainer_android.BuildConfig;

/**
 * Created by Johnnie on 17/07/18.
 */

public class RNHelper {
    public final static String TEST_APP_BUNDLE_NAME = "test_app.jsbundle";
    public final static String TEST_APP_MODULE_NAME = "App";

    public final static String ANOTHER_TEST_APP_BUNDLE_NAME = "another_test_app.jsbundle";
    public final static String ANOTHER_TEST_APP_MODULE_NAME = "App";

    public static View getRNRootView(Context context, String bundleName, String moduleName, @Nullable Bundle initialProperties){
        ReactRootView mReactRootView = new ReactRootView(context);
        ReactInstanceManager mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication((Application) context.getApplicationContext())
                .setBundleAssetName(bundleName)
                .setJSMainModulePath("index")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();

        mReactRootView.startReactApplication(mReactInstanceManager, moduleName, initialProperties);

        return mReactRootView;
    }
}
