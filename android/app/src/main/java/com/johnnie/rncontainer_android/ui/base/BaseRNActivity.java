package com.johnnie.rncontainer_android.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.johnnie.rncontainer_android.BuildConfig;
import com.johnnie.rncontainer_android.R;

/**
 * Created by Johnnie on 17/07/18.
 */

public abstract class BaseRNActivity extends BaseActivity implements DefaultHardwareBackBtnHandler {

    private final int OVERLAY_PERMISSION_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);

        if(BuildConfig.DEBUG
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(this))
        {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);

        }

        setupContentView();
    }

    /**
     * should call {@link #setContentView(View)} or {@link #setContentView(int)}}
     */
    protected abstract void setupContentView();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted
                    //TODO: show message
                }
            }
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

}
