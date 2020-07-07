package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.techja.myapplication.callback.OnM001LoginCallbackToView;
import com.techja.myapplication.utils.CommonUtils;

public class M001LoginPresenter extends BasePresenter<OnM001LoginCallbackToView> {
    private FirebaseAuth fAuth;
    private OnCompleteListener<AuthResult> loginListenner;
    private boolean isChecked;
    private String email, password;

    public M001LoginPresenter(OnM001LoginCallbackToView event) {
        super(event);
        fAuth = FirebaseAuth.getInstance();
        loginListenner = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mListener.showToast("Login successfully: " + fAuth.getCurrentUser().getUid());
                    M001LoginPresenter.this.getStorage().setCurrentUId(fAuth.getCurrentUser().getUid());
                    if (isChecked) {
                        CommonUtils.getInstance().saveAccount(email, password);
                    }
                    mListener.showMenuFragment();
                } else {
                    mListener.showToast("Invalid user name or password");
                }
                mListener.showProgressbar(false);
            }
        };
    }

    public void login(String email, String password, boolean checked) {
        this.email = email;
        this.password = password;
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginListenner);
        mListener.showProgressbar(true);
        this.isChecked = checked;
    }


}
