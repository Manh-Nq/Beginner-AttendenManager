package com.techja.myapplication.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnMainCallBackToView;
import com.techja.myapplication.presenter.MainPresenter;
import com.techja.myapplication.view.base.BaseActivity;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM000SpashCallback;
import com.techja.myapplication.view.event.OnM001LoginCallback;
import com.techja.myapplication.view.event.OnM002ProifleCompanyCallBack;
import com.techja.myapplication.view.event.OnM003MenuCallBack;
import com.techja.myapplication.view.event.OnM004ClassCallBack;
import com.techja.myapplication.view.event.OnM005TimetableCallBack;
import com.techja.myapplication.view.event.OnM006ListClassCallback;
import com.techja.myapplication.view.event.OnM007ListStudentCallBack;
import com.techja.myapplication.view.event.OnM008StudentAttendanceCallback;
import com.techja.myapplication.view.fragment.M000SplashFrg;
import com.techja.myapplication.view.fragment.M001LoginFrg;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements OnMainCallBackToView,
        OnM000SpashCallback, OnM001LoginCallback, OnM002ProifleCompanyCallBack, OnM003MenuCallBack, OnM004ClassCallBack,
        OnM005TimetableCallBack, OnM006ListClassCallback, OnM007ListStudentCallBack, OnM008StudentAttendanceCallback {

    public static final int REQUEST_CODE = 1996;

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initViews() {
        if(checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.INTERNET},REQUEST_CODE);

        }
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

