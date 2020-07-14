package com.techja.myapplication.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM002ChangeCodeCallBackToView;
import com.techja.myapplication.presenter.M002ChangeCodePresenter;
import com.techja.myapplication.view.base.BaseDialog;
import com.techja.myapplication.view.event.OnM002ChangeCodeCallBackToParent;

public class M002ChangeCodeDialog extends BaseDialog<M002ChangeCodePresenter, OnM002ChangeCodeCallBackToParent> implements OnM002ChangeCodeCallBackToView {
    private EditText edtCode, edtLat, edtLong, edtRange;

    public M002ChangeCodeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected M002ChangeCodePresenter getPresenter() {
        return new M002ChangeCodePresenter(this);
    }

    @Override
    protected void initViews() {
        findViewById(R.id.tv_cancel_002, this);
        findViewById(R.id.tv_ok_002, this);
        edtCode = findViewById(R.id.edt_code_attendance_002);
        edtLat = findViewById(R.id.edt_latitude);
        edtLong = findViewById(R.id.edt_longitude);
        edtRange = findViewById(R.id.edt_range_002);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel_002) {
            dismiss();
        } else if (v.getId() == R.id.tv_ok_002) {
            saveNewCodeToServer();
        }
    }

    private void saveNewCodeToServer() {
        String code = textOf(edtCode),
                lat = textOf(edtLat),
                longt = textOf(edtLong),
                range = textOf(edtRange);
        if (checkValid(code, lat, longt, range)) {
            mPresenter.sendToServer(code, lat, longt, range);
            dismiss();
        }
    }

    private boolean checkValid(String code, String lat, String longt, String range) {
        if (code.isEmpty()) {
            edtCode.setError("Please enter your code attendance");
            edtCode.requestFocus();
            return false;
        }

        if (lat.isEmpty()) {
            edtLat.setError("Please enter your latitude");
            edtLat.requestFocus();
            return false;
        }
        if (longt.isEmpty()) {
            edtLong.setError("Please enter your longitude");
            edtLong.requestFocus();
            return false;
        }
        if (range.isEmpty()) {
            edtRange.setError("Please enter range");
            edtRange.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_edit_code_company;
    }
}
