package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techja.myapplication.callback.OnM005EditTimeTableCallBackToView;
import com.techja.myapplication.model.TimeTableEntity;

public class M005EditTimeTablePresenter extends BasePresenter<OnM005EditTimeTableCallBackToView> {

    public M005EditTimeTablePresenter(OnM005EditTimeTableCallBackToView event) {
        super(event);
    }


    public void uploadTimeTable(String classCode, String day, String time, String detail, String coach, String note) {
        try {

            DocumentReference doc = FirebaseFirestore.getInstance().collection("class")
                    .document(classCode).collection("timetable").document(day);

            TimeTableEntity entity = new TimeTableEntity(day, time, detail, coach, note);

            doc.set(entity).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        mListener.showToast("upload time table success fully");
                    } else {
                        mListener.showToast("upload time table fail");
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
