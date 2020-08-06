package com.techja.myapplication.presenter;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techja.myapplication.callback.OnM004ClassCallbackToView;
import com.techja.myapplication.model.ClassEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class M004ClassPresenter extends BasePresenter<OnM004ClassCallbackToView> {

    public M004ClassPresenter(OnM004ClassCallbackToView event) {
        super(event);

    }

    public void getListClass() {
        mListener.showProgressBar(true);
        FirebaseFirestore.getInstance()
                .collection("class")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<ClassEntity> listData = new ArrayList<>();
                        if (task.isSuccessful()) {
                            task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                listData.add(new ClassEntity((String) data.get("className"), (String) data.get("classCode")));

                            }
                            mListener.updateData(listData);
                            Log.d("TAG", "onComplete009: "+listData.size());

                        }
                        mListener.showProgressBar(false);
                    }
                });
    }


}
