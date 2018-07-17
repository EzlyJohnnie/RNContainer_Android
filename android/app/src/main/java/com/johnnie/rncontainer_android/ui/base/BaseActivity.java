package com.johnnie.rncontainer_android.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.johnnie.rncontainer_android.R;


/**
 * Created by Johnnie on 09/05/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG_HOST_FRAGMENT = "tag_hostFragment";
    private static final String TAG_PRESENTED_FRAGMENT = "tag_presentedFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    protected void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, 0)
                .replace(R.id.host_container, fragment, TAG_HOST_FRAGMENT)
                .commit();
    }

    public void presentFragment(Fragment fragment, boolean animated){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(animated) transaction.setCustomAnimations(R.anim.slide_up_in, R.anim.stay, R.anim.stay, R.anim.slide_down_out);

        transaction.replace(R.id.top_host_container, fragment, TAG_PRESENTED_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    public void showTopFragmentBackgroundView(boolean isShow){
        View topFragmentBackgroundView = findViewById(R.id.top_fragment_background);
        if(topFragmentBackgroundView != null){
            topFragmentBackgroundView.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        boolean hasHandle = false;

        Fragment currentHostFragment = getSupportFragmentManager().findFragmentByTag(TAG_HOST_FRAGMENT);
        Fragment currentPresentedFragment = getSupportFragmentManager().findFragmentByTag(TAG_PRESENTED_FRAGMENT);

        if(currentPresentedFragment != null && currentPresentedFragment instanceof Backable){
            hasHandle = ((Backable)currentPresentedFragment).onBackClicked();
        }
        else if(currentHostFragment != null && currentHostFragment instanceof Backable){
            hasHandle = ((Backable)currentHostFragment).onBackClicked();
        }

        if(!hasHandle){
            super.onBackPressed();
        }
    }

}
