package com.techja.myapplication.view.activity;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnMainCallBackToView;
import com.techja.myapplication.presenter.MainPresenter;
import com.techja.myapplication.view.base.BaseActivity;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM000SpashCallback;
import com.techja.myapplication.view.event.OnM001LoginCallback;
import com.techja.myapplication.view.event.OnM003MenuCallBack;
import com.techja.myapplication.view.event.OnM004ClassCallBack;
import com.techja.myapplication.view.fragment.M000SplashFrg;
import com.techja.myapplication.view.fragment.M001LoginFrg;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements OnMainCallBackToView,
        OnM000SpashCallback, OnM001LoginCallback, OnM003MenuCallBack, OnM004ClassCallBack {

    public static final int REQUEST_CODE = 1996;

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initViews() {

        showFragment(M000SplashFrg.TAG);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        String currentTag = getStorage().getCurrentFrgTag();
        if (currentTag == null) {
            super.onBackPressed();
            return;
        }
        BaseFragment frg = (BaseFragment) getSupportFragmentManager().findFragmentByTag(currentTag);

        try {
            frg.showPreviousFrg();
        } catch (Exception e) {
            super.onBackPressed();
        }
    }


    @Override
    public void updateUserInfoUI(List<String> string) {

    }

    @Override
    public void showToast(String text) {
    }
}

