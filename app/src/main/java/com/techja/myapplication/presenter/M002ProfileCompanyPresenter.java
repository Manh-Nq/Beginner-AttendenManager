package com.techja.myapplication.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.techja.myapplication.callback.OnM002ProfileCallBackToView;

import java.util.ArrayList;
import java.util.List;

public class M002ProfileCompanyPresenter extends BasePresenter<OnM002ProfileCallBackToView> {
    public M002ProfileCompanyPresenter(OnM002ProfileCallBackToView event) {
        super(event);
    }

    public void getInfoCode(String[] strings) {
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        DocumentReference doc = fStore.collection("codeAttendance").document("TECHJA");
        doc.addSnapshotListener(new EventListener<DocumentSnapshot>() {


            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (!documentSnapshot.exists()) {
                    mListener.showToast("un load link");
                }
                List<String> listData = new ArrayList<>();
                if (documentSnapshot == null) {
                    return;
                }
                for (int i = 0; i < strings.length; i++) {
                    listData.add(documentSnapshot.getString(strings[i]));
                    Log.d("TAG", "getUserInfo: " + documentSnapshot.getString(strings[i]));
                }
                mListener.sendInfoCode(listData);
            }
        });

    }
}
