package com.johnnie.rncontainer_android.ui;

import com.johnnie.rncontainer_android.R;
import com.johnnie.rncontainer_android.ui.base.BaseActivity;
import com.johnnie.rncontainer_android.ui.base.BaseFeatureFragment;

import butterknife.OnClick;

/**
 * Created by Johnnie on 17/07/18.
 */

public class LandingFragment extends BaseFeatureFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_landing;
    }


    @OnClick(R.id.btn_show_test_app)
    public void showTestApp(){
        RNTestAppActivity.startActivity(getContext());
    }

    @OnClick(R.id.btn_show_anther_app)
    public void showAnotherTestApp(){
        //use a fragment instead of a dialog to avoid dialog dismiss after rotation.
        //did not use DialogFragment, because I still want the fragment be extended from BaseFragment.
        ((BaseActivity)getActivity()).presentFragment(AnotherRNAppFragment.newInstance(), true);
    }

    @OnClick(R.id.btn_show_both_app)
    public void showBothApp(){
        MultiRNAppActivity.startActivity(getContext());
    }
}
