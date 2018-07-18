package com.johnnie.rncontainer_android.ui;

import android.os.Bundle;

import com.johnnie.rncontainer_android.R;
import com.johnnie.rncontainer_android.ui.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by Johnnie on 18/07/18.
 */

public class LauncherActivity extends BaseActivity {

    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    @Override
    public void onResume(){
        super.onResume();
        disposable = Single.just(true)
                .delay(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ignore -> {
                    MainActivity.startActivity(this);
                    overridePendingTransition(R.anim.slide_up_in, R.anim.stay);
                    finish();
                });
    }

    @Override
    public void onPause(){
        super.onPause();
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

}
