package com.johnnie.rncontainer_android.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.johnnie.rncontainer_android.R;
import com.johnnie.rncontainer_android.ui.base.BaseActivity;
import com.johnnie.rncontainer_android.ui.base.BaseHostFragment;

import butterknife.ButterKnife;

/**
 * Created by Johnnie on 17/07/18.
 */

public class MainActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(savedInstanceState == null) {
            setupLandingFragment();
        }
    }

    private void setupLandingFragment() {
        replace(BaseHostFragment.hostFragmentWithLandingFragment(LandingFragment.class));
    }

}
