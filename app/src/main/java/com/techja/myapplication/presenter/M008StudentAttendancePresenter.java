package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techja.myapplication.callback.OnM008AttendanceCallbackToView;

import java.util.Map;

public class M008StudentAttendancePresenter extends BasePresenter<OnM008AttendanceCallbackToView> {
    public M008StudentAttendancePresenter(OnM008AttendanceCallbackToView event) {
        super(event);
    }

    public void getHistoryAttendanceStudent(String email, String classCode) {
        try {
            FirebaseFirestore.getInstance().collection("diemdanh").document(classCode)
                    .collection(email)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> data = document.getData();
                                    mListener.addAttendanceToView((String) data.get("day"), (String) data.get("time"), (String) data.get("state"));
                                }
                            }
                        }
                    });
        } catch (Exception e) {

        }
    }
}
