package com.techja.myapplication.view.base;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techja.myapplication.App;
import com.techja.myapplication.callback.OnCallBackToView;
import com.techja.myapplication.presenter.BasePresenter;
import com.techja.myapplication.utils.StorageCommon;
import com.techja.myapplication.view.event.OnActionCallBack;

public abstract class BaseFragment<K extends BasePresenter, C extends OnActionCallBack> extends Fragment implements View.OnClickListener, OnCallBackToView {

    protected Context mContext;
    protected View rootView;
    protected C mCallBack;
    protected K mPresenter;

    public final void setOnCallBack(C event) {
        this.mCallBack = event;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mPresenter = getPresenter();
    }

    protected abstract K getPresenter();

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initViews();
        return rootView;
    }

    protected final <T extends View> T findViewById(int id) {
        return rootView.findViewById(id);
    }
    protected final <T extends View> T findViewById(int id, View.OnClickListener event,Typeface typeFace) {
        T view = rootView.findViewById(id);
        view.setOnClickListener(event);
        if (typeFace != null && view instanceof TextView || view instanceof EditText) {
            ((TextView) view).setTypeface(typeFace);
            ((EditText) view).setTypeface(typeFace);
        }
        return view;
    }
    protected final <T extends View> T findViewById(int id,Typeface typeFace) {
        T view = rootView.findViewById(id);
        if (typeFace != null && view instanceof TextView || view instanceof EditText) {
            ((TextView) view).setTypeface(typeFace);
        }
        return view;
    }

    protected final <T extends View> T findViewById(int id, View.OnClickListener event) {
        T v = rootView.findViewById(id);
        if (v != null && event != null) {
            v.setOnClickListener(event);
        }
        return v;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    @Override
    public void onClick(View v) {
        // do nothing
    }

    @Override
    public void onDestroy() {
        rootView = null;
        mContext = null;
        super.onDestroy();
    }

    protected String textOf(EditText edt) {
        if (edt != null) {
            return edt.getText().toString().trim();
        }
        return null;

    }

    public abstract void showPreviousFrg();


    public StorageCommon getStorage() {
        return App.getInstance().getStorage();
    }

    @Override
    public void showToast(String yourString) {
        if (mContext != null) {
            Toast.makeText(mContext, yourString, Toast.LENGTH_SHORT).show();
        }
    }
}
