package com.techja.myapplication.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM004MoreClassCallbacktoView;
import com.techja.myapplication.presenter.M004MoreClassPresenter;
import com.techja.myapplication.view.base.BaseDialog;
import com.techja.myapplication.view.event.OnM004MoreClassCallBackToParent;

public class M004MoreClassDialog extends BaseDialog<M004MoreClassPresenter, OnM004MoreClassCallBackToParent> implements OnM004MoreClassCallbacktoView {
    private TextView tvCancel, tvOk;
    private EditText edtClassName, edtClassCode, edtCoach, edtDescription, edtDuration, edtModul, edtSupporter;

    public M004MoreClassDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected M004MoreClassPresenter getPresenter() {
        return new M004MoreClassPresenter(this);
    }

    @Override
    protected void initViews() {
        edtClassCode = findViewById(R.id.edt_classCode);
        edtClassName = findViewById(R.id.edt_className);
        edtCoach = findViewById(R.id.edt_coach);
        edtDescription = findViewById(R.id.edt_description);
        edtDuration = findViewById(R.id.edt_duration);
        edtModul = findViewById(R.id.edt_modul);
        edtSupporter = findViewById(R.id.edt_supporter);
        tvCancel = findViewById(R.id.tv_cancel_more_class_004, this);
        tvOk = findViewById(R.id.tv_ok_more_class_004, this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel_more_class_004) {
            dismiss();
        } else if (v.getId() == R.id.tv_ok_more_class_004) {

            String className = textOf(edtClassName),
                    classCode = textOf(edtClassCode),
                    coach = textOf(edtCoach),
                    description = textOf(edtDescription),
                    duration = textOf(edtDuration),
                    modul = textOf(edtModul),
                    supporter = textOf(edtSupporter);
            if (checkValid(className, classCode, coach, description, duration, modul, supporter)) {
                mPresenter.setClass(className, classCode, coach
                        , description, duration, modul, supporter);
                dismiss();
            }
        }
    }

    private boolean checkValid(String className, String classCode, String coach, String description, String duration, String modul, String supporter) {
        if (className.isEmpty()) {
            edtClassName.setError("Please enter your class name");
            return false;
        }
        if (classCode.isEmpty()) {
            edtClassCode.setError("Please enter your class code");
            return false;
        }
        if (coach.isEmpty()) {
            edtCoach.setError("Please enter your coach");
            return false;
        }

        if (description.isEmpty()) {
            edtDescription.setError("Please enter your description");
            return false;
        }
        if (duration.isEmpty()) {
            edtDuration.setError("Please enter your duration");
            return false;
        }

        if (modul.isEmpty()) {
            edtModul.setError("Please enter your modul");
            return false;
        }
        if (supporter.isEmpty()) {
            edtSupporter.setError("Please enter your supporter");
            return false;
        }

        return true;


    }


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_more_class;
    }
}
