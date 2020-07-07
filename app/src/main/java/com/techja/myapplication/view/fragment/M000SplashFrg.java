package com.techja.myapplication.view.fragment;

import android.os.Handler;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM000SplashCallBackToView;
import com.techja.myapplication.presenter.M000SpashPresenter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM000SpashCallback;

public class M000SplashFrg extends BaseFragment<M000SpashPresenter, OnM000SpashCallback> implements OnM000SplashCallBackToView {
    public static final String TAG = M000SplashFrg.class.getName();

    @Override
    protected M000SpashPresenter getPresenter() {
        return new M000SpashPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m000_frg_splash;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCallBack.showFragment(M001LoginFrg.TAG);
            }
        }, 1000);
    }

    @Override
    public void showPreviousFrg() {
        throw new NullPointerException();
    }
}
