package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techja.myapplication.callback.OnM004MoreClassCallbacktoView;
import com.techja.myapplication.model.ClassEntity;
import com.techja.myapplication.model.InfoClassEntity;

public class M004MoreClassPresenter extends BasePresenter<OnM004MoreClassCallbacktoView> {
    public M004MoreClassPresenter(OnM004MoreClassCallbacktoView event) {
        super(event);
    }

    public void setClass(String className, String classCode, String coach, String des, String duration, String modul, String supporter) {
        mListener.showProgressBar(true);
        ClassEntity entity = new ClassEntity(className, classCode);
        InfoClassEntity infoClass = new InfoClassEntity(className, coach, des, duration, modul, supporter);
        DocumentReference doc = FirebaseFirestore.getInstance().collection("class").document(classCode);
        DocumentReference doc1 = FirebaseFirestore.getInstance().collection("class").document(classCode)
                .collection("info").document(className);
        doc1.set(infoClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mListener.notificationSuccess();

                } else {
                    mListener.showToast("update fail");
                }
                mListener.showProgressBar(false);
            }
        });
    }
}
