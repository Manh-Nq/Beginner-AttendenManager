package com.techja.myapplication.presenter;

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

            if (!doc.set(entity).isSuccessful()) {

                mListener.showToast("upload time table fail");
                return;
            }
            doc.set(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
