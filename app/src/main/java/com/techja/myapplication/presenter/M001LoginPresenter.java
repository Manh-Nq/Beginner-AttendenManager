package com.techja.myapplication.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.techja.myapplication.callback.OnM001LoginCallbackToView;
import com.techja.myapplication.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class M001LoginPresenter extends BasePresenter<OnM001LoginCallbackToView> {
    private FirebaseAuth fAuth;
    private OnCompleteListener<AuthResult> loginListenner;
    private boolean isChecked;
    private String email, password, codeAdmin;

    public M001LoginPresenter(OnM001LoginCallbackToView event) {
        super(event);
        fAuth = FirebaseAuth.getInstance();
        loginListenner = new OnCompleteListener<AuthResult>() {
                @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mListener.showToast("Login successfully");
                    M001LoginPresenter.this.getStorage().setCurrentUId(fAuth.getCurrentUser().getUid());
                    if (isChecked) {
                        CommonUtils.getInstance().saveAccount(email, password,codeAdmin);
                    }
                    mListener.showMenuFragment();
                } else {
                    mListener.showToast("Invalid user name or password");
                }
                mListener.showProgressbar(false);
            }
        };
    }

    public void login(String email, String password, boolean checked, String codeAdmin) {
        this.email = email;
        this.password = password;
        this.codeAdmin = codeAdmin;
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginListenner);
        mListener.showProgressbar(true);
        this.isChecked = checked;
    }


    public void getCodeAdmin(String[] text) {
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();


        DocumentReference doc = fStore.collection("manager").document("uId");
        doc.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (!documentSnapshot.exists()) {
                    mListener.showToast("un load link");
                }
                List<String> listData = new ArrayList<>();
                if (documentSnapshot == null) {
                    return;
                }
                for (int i = 0; i < text.length; i++) {
                    listData.add(documentSnapshot.getString(text[i]));
                    Log.d("TAG", "getUserInfo: " + documentSnapshot.getString(text[i]));
                }
                mListener.getCodeAdmin(listData);
            }

        });
    }
}
