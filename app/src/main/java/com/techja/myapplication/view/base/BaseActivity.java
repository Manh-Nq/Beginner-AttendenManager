package com.techja.myapplication.view.base;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.presenter.BasePresenter;
import com.techja.myapplication.utils.StorageCommon;
import com.techja.myapplication.view.event.OnActionCallBack;

import java.lang.reflect.Constructor;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements View.OnClickListener, OnActionCallBack {

    protected T mPresenter;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mPresenter = getPresenter();
        initViews();
    }

    protected abstract T getPresenter();

    protected abstract void initViews();

    public <T extends View> T findViewById(int id, View.OnClickListener event) {
        T view = findViewById(id);
        if (view == null) {
            return null;
        }
        view.setOnClickListener(event);
        return view;
    }

    @Override
    public void onClick(View v) {
        // do nothing
    }

    protected abstract int getLayoutID();

    public String textOf(EditText edt) {
        if (edt != null) {
            return edt.getText().toString();
        }
        return null;
    }

    @Override
    public void showFragment(String tag) {
        try {
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(tag);
            if (fragment == null) {
                Class<?> clazz = Class.forName(tag);
                Constructor<?> cons = clazz.getConstructor();
                fragment = (BaseFragment) cons.newInstance();
            }

            fragment.setOnCallBack(this);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ln_main, fragment, tag)
                    .commit();

            getStorage().setCurrentFrgTag(tag);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    protected StorageCommon getStorage() {
        return App.getStorage();
    }

    @Override
    public void callBack(String key, Object data) {

    }

}
