package com.johnnie.rncontainer_android.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.johnnie.rncontainer_android.R;
import com.johnnie.rncontainer_android.ui.base.BaseRNActivity;
import com.johnnie.rncontainer_android.utils.RNHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Johnnie on 17/07/18.
 */

public class MultiRNAppActivity extends BaseRNActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MultiRNAppActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.top_container) FrameLayout topContainer;
    @BindView(R.id.bottom_container) FrameLayout bottomContainer;

    @Override
    protected void setupContentView() {
        setContentView(R.layout.activity_multi_rn_app);
        ButterKnife.bind(this);
        setupView();
    }

    private void setupView() {
        View topRNRootView = RNHelper.getRNRootView(this,
                RNHelper.TEST_APP_BUNDLE_NAME,
                RNHelper.TEST_APP_MODULE_NAME,
                null);

        View bottomRNRootView = RNHelper.getRNRootView(this,
                RNHelper.ANOTHER_TEST_APP_BUNDLE_NAME,
                RNHelper.ANOTHER_TEST_APP_MODULE_NAME,
                null);

        //TODO: does it really need
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        topRNRootView.setLayoutParams(lp);
        bottomRNRootView.setLayoutParams(lp);

        topContainer.addView(topRNRootView);
        bottomContainer.addView(bottomRNRootView);
    }
}
