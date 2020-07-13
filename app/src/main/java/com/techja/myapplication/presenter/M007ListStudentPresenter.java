package com.techja.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techja.myapplication.callback.OnM007ListStudentCallBackToView;

import java.util.Map;

public class M007ListStudentPresenter extends BasePresenter<OnM007ListStudentCallBackToView> {
    public M007ListStudentPresenter(OnM007ListStudentCallBackToView event) {
        super(event);
    }

    public void getUserInfoStudent() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseFirestore.getInstance()
                .collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                getStorage().addStudent((String) (data.get("firstName") + " " + data.get("lastName")), (String) data.get("classCode"), (String) data.get("email"));
                            }
                        }
                    }
                });
    }
}
