package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techja.myapplication.callback.OnM005TimetableCallBackToView;
import com.techja.myapplication.utils.MTask;

import java.util.Map;

public class M005TimeTablePresenter extends BasePresenter<OnM005TimetableCallBackToView> {
    private static final String KEY_DOWNLOAD = "KEY_DOWNLOAD";

    public M005TimeTablePresenter(OnM005TimetableCallBackToView event) {
        super(event);

    }

    public void getClassTimetable(String classCode) {
        if (classCode == null) {
            return;
        }
        FirebaseFirestore.getInstance()
                .collection("class")
                .document(classCode)
                .collection("timetable")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                mListener.addTimetable((String) data.get("day"), (String) data.get("time"), (String) data.get("detail"), (String) data.get("teacher"), (String) data.get("note"));
                            }
                        }
                    }
                });
    }

}
