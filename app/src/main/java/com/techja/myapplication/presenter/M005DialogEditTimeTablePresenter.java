package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techja.myapplication.callback.OnM005DialogEditTimeTableCallBackToView;
import com.techja.myapplication.model.TimeTableEntity;

public class M005DialogEditTimeTablePresenter extends BasePresenter<OnM005DialogEditTimeTableCallBackToView> {

    public M005DialogEditTimeTablePresenter(OnM005DialogEditTimeTableCallBackToView event) {
        super(event);
    }

    public void saveTimeTableToSever(String day, String time, String detail, String teacher, String note, String classCode) {
        try {
            DocumentReference doc = FirebaseFirestore.getInstance().collection("class")
                    .document(classCode).collection("timetable").document(day);

            TimeTableEntity entity = new TimeTableEntity(day, time, detail, teacher, note);
            doc.set(entity).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        mListener.showToast("upload edit time table to server success fully");
                        mListener.loadDone();
                    } else {
                        mListener.showToast("upload edit time table to server fail");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
