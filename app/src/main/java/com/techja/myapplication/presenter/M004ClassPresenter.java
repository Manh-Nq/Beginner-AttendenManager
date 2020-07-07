package com.techja.myapplication.presenter;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techja.myapplication.callback.OnM004ClassCallbackToView;

import java.util.Map;


public class M004ClassPresenter extends BasePresenter<OnM004ClassCallbackToView> {
    public M004ClassPresenter(OnM004ClassCallbackToView event) {
        super(event);
    }

    public void getListClass() {
        FirebaseFirestore.getInstance()
                .collection("class")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                getStorage().addClass((String) data.get("className"), (String) data.get("classCode"));
                            }

                            mListener.hideProgressLoadDataBar();
                        }
                    }
                });
    }
}
