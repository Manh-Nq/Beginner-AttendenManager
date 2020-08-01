package com.techja.myapplication.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM004EditClassCallBackToView;
import com.techja.myapplication.model.ClassEntity;
import com.techja.myapplication.presenter.M004EditClassPresenter;
import com.techja.myapplication.view.base.BaseDialog;
import com.techja.myapplication.view.event.OnM004EditClassCallBack;

public class M004EditClassDialog extends BaseDialog<M004EditClassPresenter, OnM004EditClassCallBack> implements OnM004EditClassCallBackToView {
    private ClassEntity mEntity;
    private TextView tvClassName, tvClassCode;


    public M004EditClassDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected M004EditClassPresenter getPresenter() {
        return new M004EditClassPresenter(this);
    }

    @Override
    protected void initViews() {
        mEntity = getStorage().getClassEntity();
        tvClassCode = findViewById(R.id.tv_classCode_004);
        tvClassName = findViewById(R.id.tv_classname_004);

        tvClassName.setText(mEntity.getClassName());
        tvClassCode.setText(mEntity.getClassCode());
        findViewById(R.id.tv_ok_edit_class, this);
        findViewById(R.id.tv_delete_edit_class, this);
        findViewById(R.id.tv_cancel_edit_class, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ok_edit_class:
                dismiss();
                break;
            case R.id.tv_cancel_edit_class:
                dismiss();
                break;
            case R.id.tv_delete_edit_class:
                mCallBack.showDiaLogQuestions(mEntity);
                dismiss();
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_edit_class;
    }




}
