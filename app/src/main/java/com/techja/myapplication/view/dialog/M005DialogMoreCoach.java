package com.techja.myapplication.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM005MoreCoachCallbackToView;
import com.techja.myapplication.presenter.M005MoreCoachPresenter;
import com.techja.myapplication.utils.CommonUtils;
import com.techja.myapplication.view.base.BaseDialog;
import com.techja.myapplication.view.event.OnM005MoreCoachCallBackToParent;

public class M005DialogMoreCoach extends BaseDialog<M005MoreCoachPresenter, OnM005MoreCoachCallBackToParent> implements OnM005MoreCoachCallbackToView {

    private EditText edtChucvu, edtCoach, edtCompany, edtPhone, edtState;
    private ProgressBar progressBar;


    public M005DialogMoreCoach(@NonNull Context context) {
        super(context);
    }

    @Override
    protected M005MoreCoachPresenter getPresenter() {
        return new M005MoreCoachPresenter(this);
    }

    @Override
    protected void initViews() {
        progressBar = findViewById(R.id.progress_bar_morecoach);
        edtChucvu = findViewById(R.id.edt_chucvu);
        edtCoach = findViewById(R.id.edt_more_coach);
        edtPhone = findViewById(R.id.edt_phonenumber);
        edtCompany = findViewById(R.id.edt_company);
        edtState = findViewById(R.id.edt_state);

        findViewById(R.id.tv_cancel_more_coach_005, this);
        findViewById(R.id.tv_ok_more_coach_005, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel_more_coach_005:
                dismiss();
                break;
            case R.id.tv_ok_more_coach_005:
                sendDataCoachToServer();
                break;
        }
    }

    private void sendDataCoachToServer() {
        String chucVu = textOf(edtChucvu),
                name = textOf(edtCoach),
                company = textOf(edtCompany),
                phoneNo = textOf(edtPhone),
                state = textOf(edtState),
                classCode = getStorage().getClassCode();
        if (chechValid(chucVu, name, company, phoneNo, state)) {
            mPresenter.saveDataToServer(chucVu, name, company, phoneNo, state, classCode);
            dismiss();
        }

    }

    private boolean chechValid(String chucVu, String name, String company, String phoneNo, String state) {
        if (chucVu.isEmpty()) {
            edtChucvu.setError("Fill in the information position");
            edtChucvu.requestFocus();
            return false;
        }
        if (name.isEmpty()) {
            edtCoach.setError("Fill in the information name");
            edtCoach.requestFocus();
            return false;
        }
        if (company.isEmpty()) {
            edtCompany.setError("Fill in the information company");
            edtCompany.requestFocus();
            return false;
        }

        if (phoneNo.isEmpty()) {
            edtPhone.setError("Fill in the information phonenumber");
            edtPhone.requestFocus();
            return false;
        }
        if (!CommonUtils.isPhone(phoneNo)) {
            edtPhone.setError("Not in the correct format!!!!");
            showToast("số điện thoại không đúng định dạng");
            edtPhone.requestFocus();
            return false;
        }
        if (state.isEmpty()) {
            edtState.setError("Fill in the information state");
            edtState.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_more_coach;
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
