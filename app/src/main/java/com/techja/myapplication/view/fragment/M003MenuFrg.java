package com.techja.myapplication.view.fragment;

import android.view.View;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM003MenuCallbackToView;
import com.techja.myapplication.presenter.M003MenuPresenter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM003MenuCallBack;

public class M003MenuFrg extends BaseFragment<M003MenuPresenter, OnM003MenuCallBack> implements OnM003MenuCallbackToView {

    public static final String TAG = M003MenuFrg.class.getName();

    @Override
    protected M003MenuPresenter getPresenter() {
        return new M003MenuPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m003_frg_menu;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.ln_diem_danh, this);
        findViewById(R.id.ln_history, this);
        findViewById(R.id.ln_attendance, this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ln_diem_danh) {
            mCallBack.showFragment(M004ClassFrg.TAG);
        } else if (v.getId() == R.id.ln_history) {
            mCallBack.showFragment(M006ListClassFrg.TAG);
        } else if (v.getId() == R.id.ln_attendance) {
            mCallBack.showFragment(M002ProfileCompanyFrg.TAG);
        }
    }

    @Override
    public void showPreviousFrg() {
        throw new NullPointerException();
    }
}
