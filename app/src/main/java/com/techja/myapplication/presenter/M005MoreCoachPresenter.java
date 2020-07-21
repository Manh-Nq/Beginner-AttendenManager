package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techja.myapplication.callback.OnM005MoreCoachCallbackToView;
import com.techja.myapplication.model.CoachEntity;

public class M005MoreCoachPresenter extends BasePresenter<OnM005MoreCoachCallbackToView> {
    public M005MoreCoachPresenter(OnM005MoreCoachCallbackToView event) {
        super(event);
    }

    public void saveDataToServer(String chucVu, String name, String company, String phoneNo, String state, String classCode) {
        mListener.showProgressBar(true);
        CoachEntity entity = new CoachEntity(chucVu, name, company, phoneNo, state);
        DocumentReference doc = FirebaseFirestore.getInstance().collection("coach").document(classCode);

        doc.set(entity).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mListener.showToast("update info coach success");
                } else {
                    mListener.showToast("update info coach fail!!!!");
                }
            }
        });
        mListener.showProgressBar(false);
    }
}
