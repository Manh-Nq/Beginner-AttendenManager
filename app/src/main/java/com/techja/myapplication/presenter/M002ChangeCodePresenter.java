package com.techja.myapplication.presenter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techja.myapplication.callback.OnM002ChangeCodeCallBackToView;
import com.techja.myapplication.model.CompanyEntity;

public class M002ChangeCodePresenter extends BasePresenter<OnM002ChangeCodeCallBackToView> {
    private OnCompleteListener<AuthResult> authListenner;

    public M002ChangeCodePresenter(OnM002ChangeCodeCallBackToView event) {
        super(event);
    }

    public void sendToServer(String code, String lat, String longt, String range) {
        CompanyEntity entity = new CompanyEntity(code, lat, longt, range);
        DocumentReference doc = FirebaseFirestore.getInstance().collection("codeAttendance").document("TECHJA");
        doc.set(entity);
        mListener.showToast("change code attendance success fully");
    }
}
