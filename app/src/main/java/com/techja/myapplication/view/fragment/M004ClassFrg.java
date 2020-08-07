package com.techja.myapplication.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM004ClassCallbackToView;
import com.techja.myapplication.model.ClassEntity;
import com.techja.myapplication.presenter.M004ClassPresenter;
import com.techja.myapplication.view.adapter.ClassAdapter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.dialog.M004EditClassDialog;
import com.techja.myapplication.view.dialog.M004MoreClassDialog;
import com.techja.myapplication.view.event.OnM004ClassCallBack;
import com.techja.myapplication.view.event.OnM004EditClassCallBack;
import com.techja.myapplication.view.event.OnM004MoreClassCallBackToParent;

import java.util.ArrayList;
import java.util.List;

public class M004ClassFrg extends BaseFragment<M004ClassPresenter, OnM004ClassCallBack>
        implements OnM004ClassCallbackToView, ClassAdapter.adapterListener,
        OnM004MoreClassCallBackToParent, OnM004EditClassCallBack {
    public static final String TAG = M004ClassFrg.class.getName();
    private RecyclerView rvClass;
    private ClassAdapter adapter;
    private List<ClassEntity> listData;
    private Button btEdit;
    private ProgressBar progressBar;
    private LinearLayout lnFrg;
    private M004EditClassDialog m004EditClassDialog;

    @Override
    protected M004ClassPresenter getPresenter() {
        return new M004ClassPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m004_frg_class;
    }

    @Override
    protected void initViews() {
        this.listData = new ArrayList<>();
        progressBar = findViewById(R.id.progress_bar_004);
        lnFrg = findViewById(R.id.ln_m004_frg);
        btEdit = findViewById(R.id.bt_more_m004, this);
        mPresenter.getListClass();
        rvClass = rootView.findViewById(R.id.rv_class);
        rvClass.setLayoutManager(new GridLayoutManager(mContext,1));
        findViewById(R.id.iv_back, this);

    }


    private void initData(List<ClassEntity> listData) {

        this.listData = listData;
//        this.listData.add(new ClassEntity("hello", "dsdsd"));
        Log.d(TAG, "initViews: " + this.listData.toString());
        adapter = new ClassAdapter(this.listData, mContext);
        adapter.setCallBackItem(this);
        rvClass.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_more_m004) {
            showDialogMoreClass();
        } else if (v.getId() == R.id.iv_back) {
            mCallBack.showFragment(M003MenuFrg.TAG);
        }
    }

    private void showDialogMoreClass() {
        M004MoreClassDialog dialog = new M004MoreClassDialog(mContext);
        dialog.setOnCallBack(this);
        dialog.show();
    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M003MenuFrg.TAG);
    }


    @Override
    public void clickButtonTimetable(ClassEntity data) {
        getStorage().setClassEntity(data);
        mCallBack.showFragment(M005TimeTableFrg.TAG);
    }


    @Override
    public void updateData(List<ClassEntity> listData) {
        initData(listData);
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            lnFrg.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            lnFrg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void updateUIParent() {
        mPresenter.getListClass();
        initData(listData);
    }

    @Override
    public void showFragment(String tag) {

    }

    @Override
    public void showDiaLogEditClass(ClassEntity entity) {
        getStorage().setClassEntity(entity);
        m004EditClassDialog = new M004EditClassDialog(mContext);
        m004EditClassDialog.setOnCallBack(this);
        m004EditClassDialog.show();
    }


    @Override
    public void showDiaLogQuestions(ClassEntity entity) {
        m004EditClassDialog.dismiss();
        AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.setTitle("Bạn thật sự muốn xoá lớp học");
        dialog.setMessage(entity.getClassName());
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DocumentReference doc = FirebaseFirestore.getInstance().collection("class").document(entity.getClassCode());
                doc.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            showToast("delete success");
                            mPresenter.getListClass();
                        } else {
                            showToast("delete fail");
                        }
                    }
                });
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }
}
