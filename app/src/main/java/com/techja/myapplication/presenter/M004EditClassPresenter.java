package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techja.myapplication.callback.OnM004EditClassCallBackToView;
import com.techja.myapplication.model.ClassEntity;

public class M004EditClassPresenter extends BasePresenter<OnM004EditClassCallBackToView> {
    public M004EditClassPresenter(OnM004EditClassCallBackToView event) {
        super(event);
    }

//    public void saveDataToServer(String className, String classCode) {
//        DocumentReference doc = FirebaseFirestore.getInstance().collection("class").document(classCode);
//        ClassEntity entity = new ClassEntity(className, classCode);
//        doc.set(entity).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    mListener.showToast("upload success");
//                    mListener.uploadSuccess();
//                } else {
//                    mListener.showToast("upload fail");
//                }
//            }
//        });
//    }

}
