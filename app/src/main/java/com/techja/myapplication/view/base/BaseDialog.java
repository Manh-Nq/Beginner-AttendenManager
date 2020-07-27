package com.techja.myapplication.view.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.techja.myapplication.App;
import com.techja.myapplication.callback.OnCallBackToView;
import com.techja.myapplication.presenter.BasePresenter;
import com.techja.myapplication.utils.StorageCommon;
import com.techja.myapplication.view.event.OnActionCallBack;


public abstract class BaseDialog<P extends BasePresenter, C extends OnActionCallBack> extends Dialog implements View.OnClickListener, OnCallBackToView {
    protected P mPresenter;
    protected C mCallBack;
    private Context mContext;


    public BaseDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        setContentView(getLayoutId());
        mPresenter = getPresenter();
        initViews();
        setCancelable(false);
        setCanceledOnTouchOutside(false);

    }

    public void setOnCallBack(C event) {
        this.mCallBack = event;
    }

    protected abstract P getPresenter();

    protected abstract void initViews();

    protected abstract int getLayoutId();

    @Override
    public void onClick(View v) {
        // do nothing
    }

    public String textOf(EditText edt) {
        if (edt != null) {
            return edt.getText().toString().trim();
        }
        return null;
    }


    public <T extends View> T findViewById(int id, View.OnClickListener event) {
        T view = findViewById(id);
        if (view == null) {
            return null;
        }
        view.setOnClickListener(event);
        return view;
    }

    public <T extends View> T findViewById(int id, Typeface typeface) {
        T view = findViewById(id);
        if (view == null) {
            return null;
        }
        if (view instanceof EditText)
            ((EditText) view).setTypeface(typeface);
        return view;
    }

    public void showToast(String yourString) {
        if (mContext != null) {
            Toast.makeText(mContext, yourString, Toast.LENGTH_SHORT).show();
        }
    }

    public StorageCommon getStorage() {
        return App.getStorage();
    }
}

