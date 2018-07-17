package com.johnnie.rncontainer_android.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.johnnie.rncontainer_android.utils.SingleToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Johnnie on 09/05/18.
 */

public abstract class BaseFragment extends Fragment{

    private Unbinder viewUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        viewUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        viewDidLoad(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (viewUnbinder != null) viewUnbinder.unbind();

        super.onDestroyView();
    }

    protected abstract boolean hasOwnMenu();
    protected abstract int getLayoutRes();
    protected abstract void viewDidLoad(View rootView, Bundle savedInstanceState);


    protected BaseActivity getBaseActivity(){
        return (BaseActivity)getActivity();
    }

    public void showErrorMessage(int stringRes) {
        SingleToast.makeText(getContext(), stringRes, Toast.LENGTH_SHORT).show();
    }

}
