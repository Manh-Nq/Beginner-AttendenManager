package com.techja.myapplication.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.techja.myapplication.App;
import com.techja.myapplication.callback.OnCallBackToView;
import com.techja.myapplication.utils.StorageCommon;

import java.util.ArrayList;
import java.util.List;


public abstract class BasePresenter<T extends OnCallBackToView> {
    protected T mListener;
    protected List<String> myData;


    public BasePresenter(T event) {
        mListener = event;
    }

    protected StorageCommon getStorage() {
        return App.getStorage();
    }

    public void getUserInfo(String[] categoryInfo) {
        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("TAG", "getUserInf112o: " + uID);
        if (uID == null) {
            return;
        }
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();


        DocumentReference doc = fStore.collection("user").document("yiaWd5pZOyR0OdTLePXCtoywm2");
        doc.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    mListener.showToast("un load link");
                }
                List<String> listData = new ArrayList<>();
                if (documentSnapshot == null) {
                    return;
                }
                for (int i = 0; i < categoryInfo.length; i++) {
                    listData.add(documentSnapshot.getString(categoryInfo[i]));
                    Log.d("TAG", "getUserInfo: " + documentSnapshot.getString(categoryInfo[i]));
                }
                mListener.updateUserInfoUI(listData);
                myData = listData;
            }
        });


    }
}
