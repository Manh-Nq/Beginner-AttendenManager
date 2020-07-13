package com.techja.myapplication.view.fragment;

import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM001LoginCallbackToView;
import com.techja.myapplication.presenter.M001LoginPresenter;
import com.techja.myapplication.utils.CommonUtils;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM001LoginCallback;

import java.util.List;

public class M001LoginFrg extends BaseFragment<M001LoginPresenter, OnM001LoginCallback> implements OnM001LoginCallbackToView {
    public static final String TAG = M001LoginFrg.class.getName();
    private ImageView ivShowhide;
    private EditText edtMail, edtPass, edtCodeAdmin;

    private static final int LEVEL_SHOW_PASS = 0;
    private static final int LEVEL_HIDE_PASS = 1;
    private Button btLogin;
    private CheckBox cbSaveAcc;
    private ProgressBar progressBar;
    private String codeAdmin;


    @Override
    protected M001LoginPresenter getPresenter() {
        return new M001LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m001_frg_login;
    }

    @Override
    protected void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        cbSaveAcc = findViewById(R.id.cb_save_account);
        ivShowhide = findViewById(R.id.iv_show_pass, this);
        edtPass = findViewById(R.id.edt_password);
        edtMail = findViewById(R.id.edt_email);
        btLogin = findViewById(R.id.bt_login, this);
        edtCodeAdmin = findViewById(R.id.edt_code_admin);


        mPresenter.getCodeAdmin(new String[]{"codemanager"});

        String[] arrDataAccount = CommonUtils.getInstance().getAccount();
        String email = arrDataAccount[0];
        String pass = arrDataAccount[1];
        String codeAdmin = arrDataAccount[2];
        if (email == null || pass == null || codeAdmin == null || email.equals("") && pass.equals("") && codeAdmin.equals("")) {
            Toast.makeText(mContext, "Mời bạn đăng nhập lại", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email != null && pass != null && codeAdmin != null && !email.equals("") && !pass.equals("") && !codeAdmin.equals("")) {
            edtMail.setText(email);
            edtPass.setText(pass);
            edtCodeAdmin.setText(codeAdmin);
            mPresenter.login(email, pass, true, codeAdmin);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_show_pass) {
            showHidePass();
        } else if (v.getId() == R.id.bt_login) {
            login();
        }
    }

    private void login() {

        if (textOf(edtMail).isEmpty()) {
            edtMail.setError("Enter your email");
            return;
        }
        if (textOf(edtPass).isEmpty()) {
            edtPass.setError("Enter your password");
            return;
        }
        if (!textOf(edtCodeAdmin).equals(codeAdmin)) {
            showToast("Admin code does not exist");
        } else {
            mPresenter.login(textOf(edtMail), textOf(edtPass), cbSaveAcc.isChecked(), textOf(edtCodeAdmin));
        }
    }

    private void showHidePass() {

        if (ivShowhide.getDrawable().getLevel() == LEVEL_SHOW_PASS) {
            ivShowhide.getDrawable().setLevel(LEVEL_HIDE_PASS);
            edtPass.setTransformationMethod(null);
        } else {
            ivShowhide.getDrawable().setLevel(LEVEL_SHOW_PASS);
            edtPass.setTransformationMethod(new PasswordTransformationMethod());
        }
    }


    @Override
    public void showProgressbar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void getCodeAdmin(List<String> listData) {
        this.codeAdmin = listData.get(0);
        Log.d(TAG, "getCodeAdmin: " + codeAdmin);
    }

    @Override
    public void showPreviousFrg() {
        throw new NullPointerException();

    }

    @Override
    public void showMenuFragment() {
        mCallBack.showFragment(M003MenuFrg.TAG);
    }

}