package com.techja.myapplication.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM003MenuCallbackToView;
import com.techja.myapplication.presenter.M003MenuPresenter;
import com.techja.myapplication.utils.CommonUtils;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM003MenuCallBack;

public class M003MenuFrg extends BaseFragment<M003MenuPresenter, OnM003MenuCallBack> implements OnM003MenuCallbackToView {

    public static final String TAG = M003MenuFrg.class.getName();
    private ImageView ivSignout;

    @Override
    protected M003MenuPresenter getPresenter() {
        return new M003MenuPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m003_frg_menu;
    }

    @Override
    protected void initViews() {
        ivSignout = findViewById(R.id.iv_signout, this);
        findViewById(R.id.ln_diem_danh, this);
        findViewById(R.id.ln_history, this);
        findViewById(R.id.ln_attendance, this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ln_diem_danh) {
            mCallBack.showFragment(M004ClassFrg.TAG);
        } else if (v.getId() == R.id.ln_history) {
            mCallBack.showFragment(M006ListClassFrg.TAG);
        } else if (v.getId() == R.id.ln_attendance) {
            mCallBack.showFragment(M002ProfileCompanyFrg.TAG);
        } else if (v.getId() == R.id.iv_signout) {
            showDialogSignOut();
        }
    }

    private void showDialogSignOut() {
        AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.setTitle("Bạn có muốn thoát không?");
        dialog.setMessage("click cancel để trở lại");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                mCallBack.showFragment(M001LoginFrg.TAG);
                CommonUtils.getInstance().saveAccount("","","");
                dialog.dismiss();
            }
        });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    @Override
    public void showPreviousFrg() {
        throw new NullPointerException();
    }
}
