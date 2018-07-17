package com.johnnie.rncontainer_android.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.johnnie.rncontainer_android.R;

import butterknife.BindView;

/**
 * Created by Johnnie on 09/05/18.
 */

/**
 * Host fragment has a toolbar
 * equals to UINavigationController in iOS
 */
public class BaseHostFragment extends BaseFragment implements Backable{

    private static final String TAG_CURRENT_FRAGMENT = "tag_currentFragment";
    private static final String KEY_LANDING_FRAGMENT_CLASS_NAME = "key_landingFragmentClassName";

    @BindView(R.id.toolbar) Toolbar toolbar;

    String landingFragmentClassName;


    public static BaseHostFragment hostFragmentWithLandingFragment(Class c){
        BaseHostFragment hostFragment = new BaseHostFragment();
        Bundle args = new Bundle();
        args.putString(KEY_LANDING_FRAGMENT_CLASS_NAME, c.getName());
        hostFragment.setArguments(args);

        return hostFragment;
    }

    @Override
    protected int getLayoutRes(){
        return R.layout.base_host_fragment;
    }

    @Override
    protected boolean hasOwnMenu() {
        return false;
    }

    @Override
    protected void viewDidLoad(View rootView, Bundle savedInstanceState){
        getBaseActivity().setSupportActionBar(toolbar);

        initData(savedInstanceState);
        if(savedInstanceState == null){
            setupLandingFragment();
        }
    }

    private void initData(Bundle savedInstanceState) {
        Bundle bundle = savedInstanceState == null ? getArguments() : savedInstanceState;

        if(bundle != null){
            landingFragmentClassName = bundle.getString(KEY_LANDING_FRAGMENT_CLASS_NAME);
        }
    }

    protected void setupLandingFragment() {
        if(TextUtils.isEmpty(landingFragmentClassName)) return;

        Fragment fragment = Fragment.instantiate(getContext(), landingFragmentClassName);
        replace(fragment);
    }


    public void push(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out,
                        R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(R.id.feature_container, fragment, TAG_CURRENT_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    public void replace(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.feature_container, fragment, TAG_CURRENT_FRAGMENT)
                .commit();
    }

    public boolean pop(){
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            getChildFragmentManager().popBackStackImmediate();
            return true;
        }
        return false;
    }

    @Override
    public boolean onBackClicked() {
        boolean hasHandle = false;

        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            Fragment currentFragment = getChildFragmentManager().findFragmentByTag(TAG_CURRENT_FRAGMENT);
            if(currentFragment instanceof Backable){
                hasHandle =((Backable)currentFragment).onBackClicked();
            }

            if(!hasHandle){
                hasHandle = pop();
            }
        }


        return hasHandle;
    }
}
