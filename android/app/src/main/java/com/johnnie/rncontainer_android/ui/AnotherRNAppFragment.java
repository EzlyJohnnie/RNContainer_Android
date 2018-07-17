package com.johnnie.rncontainer_android.ui;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.johnnie.rncontainer_android.R;
import com.johnnie.rncontainer_android.ui.base.BaseActivity;
import com.johnnie.rncontainer_android.ui.base.BaseFeatureFragment;
import com.johnnie.rncontainer_android.utils.RNHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Johnnie on 17/07/18.
 */

public class AnotherRNAppFragment extends BaseFeatureFragment {

    @BindView(R.id.content_view) FrameLayout contentView;

    public static AnotherRNAppFragment newInstance(){
        AnotherRNAppFragment fragment = new AnotherRNAppFragment();
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_another_rn_app;
    }

    @Override
    public void onStart(){
        super.onStart();
        ((BaseActivity)getActivity()).showTopFragmentBackgroundView(true);
    }

    @Override
    public void onStop(){
        super.onStop();
        ((BaseActivity)getActivity()).showTopFragmentBackgroundView(false);
    }

    @Override
    @CallSuper
    protected void viewDidLoad(View rootView, Bundle savedInstanceState){
        super.viewDidLoad(rootView, savedInstanceState);
        setupView();
    }

    private void setupView() {
        View rnRootView = RNHelper.getRNRootView(getContext(),
                RNHelper.ANOTHER_TEST_APP_BUNDLE_NAME,
                RNHelper.ANOTHER_TEST_APP_MODULE_NAME,
                null);

        //TODO: does it really need
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rnRootView.setLayoutParams(lp);

        contentView.addView(rnRootView);
        contentView.setOnClickListener(v -> {/*consume event only*/});
    }

    @OnClick(R.id.container)
    public void onContainerClicked(){
        getActivity().onBackPressed();
    }
}
