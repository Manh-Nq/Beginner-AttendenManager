package com.techja.myapplication.view.dialog;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM007DialogCallPhoneCallBackToView;
import com.techja.myapplication.presenter.M007DialogCallphonePresenter;
import com.techja.myapplication.utils.StorageCommon;
import com.techja.myapplication.view.base.BaseDialog;
import com.techja.myapplication.view.event.OnM007DialogCallPhoneCallbackToParent;

public class M007DialogCallPhone extends BaseDialog<M007DialogCallphonePresenter, OnM007DialogCallPhoneCallbackToParent> implements OnM007DialogCallPhoneCallBackToView {
    private String phone;
    private TextView tvPhone;

    public M007DialogCallPhone(@NonNull Context context) {
        super(context);
    }

    @Override
    protected M007DialogCallphonePresenter getPresenter() {
        return new M007DialogCallphonePresenter(this);
    }

    @Override
    protected void initViews() {
        this.phone = getStorage().getNumberCallPhone();
        findViewById(R.id.tv_cancel_call_phone_007, this);
        findViewById(R.id.tv_ok_call_phone_007, this);
        tvPhone = findViewById(R.id.tv_number_phone_dialogm007);
        tvPhone.setText(phone);
        Log.d("TAG", "initVieeeews: " + phone);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel_call_phone_007) {
            dismiss();
        } else if (v.getId() == R.id.tv_ok_call_phone_007) {
            mCallBack.callPhoneForStudent(phone);
            dismiss();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialong_callphone_007;
    }

    public StorageCommon getStorage() {
        return App.getInstance().getStorage();
    }
}
