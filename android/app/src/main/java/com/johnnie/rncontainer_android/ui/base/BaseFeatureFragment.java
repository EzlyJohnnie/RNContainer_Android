package com.johnnie.rncontainer_android.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.johnnie.rncontainer_android.R;

/**
 * Created by Johnnie on 09/05/18.
 */

/**
 * Feature fragment without a toolbar
 * it should normally put inside a host fragment
 * equals to UIViewController in iOS
 */
public abstract class BaseFeatureFragment extends BaseFragment {

    protected BaseHostFragment getHostFragment(){
        BaseHostFragment hostFragment = null;

        Fragment parentFragment = getParentFragment();

        if(parentFragment != null){
            if(parentFragment instanceof BaseHostFragment) {
                hostFragment = (BaseHostFragment)parentFragment;
            }
            else if(parentFragment instanceof BaseFeatureFragment){
                hostFragment = ((BaseFeatureFragment)parentFragment).getHostFragment();
            }
        }

        return hostFragment;
    }

    @Override
    protected boolean hasOwnMenu(){
        return false;
    }

    @Override
    public void onResume(){
        super.onResume();
        setTitle(R.string.default_toolbar_title);
        onToolbarReady(getToolbar());
    }

    @Override
    @CallSuper
    protected void viewDidLoad(View rootView, Bundle savedInstanceState){
        setHasOptionsMenu(hasOwnMenu());
    }

    protected void onToolbarReady(@Nullable Toolbar toolbar){}

    protected Toolbar getToolbar(){
        Toolbar toolbar = null;
        if(getHostFragment() != null){
            toolbar = getHostFragment().toolbar;
        }

        return toolbar;
    }

    /**
     * Should call after {@link Fragment#onStart()}
     * @param res
     */
    protected void setTitle(int res){
        Toolbar toolbar = getToolbar();
        if(toolbar != null){
            toolbar.setTitle(res);
        }
    }

    protected void setTitle(String title){
        Toolbar toolbar = getToolbar();
        if(toolbar != null){
            toolbar.setTitle(title);
        }
    }

    protected void setSubtitle(String title){
        Toolbar toolbar = getToolbar();
        if(toolbar != null){
            toolbar.setSubtitle(title);
        }
    }
}
