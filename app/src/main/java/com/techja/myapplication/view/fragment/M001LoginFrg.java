package com.techja.myapplication.view.fragment;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM001LoginCallbackToView;
import com.techja.myapplication.presenter.M001LoginPresenter;
import com.techja.myapplication.utils.CommonUtils;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM001LoginCallback;

public class M001LoginFrg extends BaseFragment<M001LoginPresenter, OnM001LoginCallback> implements OnM001LoginCallbackToView {
    public static final String TAG = M001LoginFrg.class.getName();
    private ImageView ivShowhide;
    private EditText edtMail, edtPass;
    private TextView tvRes;
    private static final int LEVEL_SHOW_PASS = 0;
    private static final int LEVEL_HIDE_PASS = 1;
    private Button btLogin;
    private CheckBox cbSaveAcc;
    private ProgressBar progressBar;


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
        tvRes = findViewById(R.id.tv_register, this);
        progressBar = findViewById(R.id.progress_bar);
        cbSaveAcc = findViewById(R.id.cb_save_account);
        ivShowhide = findViewById(R.id.iv_show_pass, this);
        edtPass = findViewById(R.id.edt_password);
        edtMail = findViewById(R.id.edt_email);
        btLogin = findViewById(R.id.bt_login, this);

        String[] arrDataAccount = CommonUtils.getInstance().getAccount();
        String email = arrDataAccount[0];
        String pass = arrDataAccount[1];
        if (email == null || pass == null || email.equals("") && pass.equals("")) {
            Toast.makeText(mContext, "Mời bạn đăng nhập lại", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email != null && pass != null && !email.equals("") && !pass.equals("")) {
            edtMail.setText(email);
            edtPass.setText(pass);
            mPresenter.login(email, pass, true);
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

        mPresenter.login(textOf(edtMail), textOf(edtPass), cbSaveAcc.isChecked());
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
    public void showPreviousFrg() {
        throw new NullPointerException();

    }

    @Override
    public void showMenuFragment() {
        mCallBack.showFragment(M003MenuFrg.TAG);
    }

}