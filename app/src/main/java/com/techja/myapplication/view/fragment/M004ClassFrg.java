package com.techja.myapplication.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM004ClassCallbackToView;
import com.techja.myapplication.model.ClassEntity;
import com.techja.myapplication.presenter.M004ClassPresenter;
import com.techja.myapplication.view.adapter.ClassAdapter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.dialog.M004MoreClassDialog;
import com.techja.myapplication.view.event.OnM004ClassCallBack;

import java.util.ArrayList;
import java.util.List;

public class M004ClassFrg extends BaseFragment<M004ClassPresenter, OnM004ClassCallBack> implements OnM004ClassCallbackToView, ClassAdapter.adapterListener {
    public static final String TAG = M004ClassFrg.class.getName();
    private RecyclerView rvClass;
    private ClassAdapter adapter;
    private List<ClassEntity> listData;
    private Button btEdit;
    private ProgressBar progressBar;
    private LinearLayout lnFrg;

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
        progressBar = findViewById(R.id.progress_bar_004);
        lnFrg = findViewById(R.id.ln_m004_frg);
        btEdit = findViewById(R.id.bt_more_m004, this);
        mPresenter.getListClass();
        rvClass = rootView.findViewById(R.id.rv_class);
        rvClass.setLayoutManager(new LinearLayoutManager(mContext));


    }


    private void initData() {

        listData = new ArrayList<>();
        listData = getStorage().getListClass();
        Log.d(TAG, "initViews: " + listData.size());
        adapter = new ClassAdapter(listData, mContext);
        adapter.setCallBackItem(this);
        rvClass.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_more_m004) {
            showDialogMoreClass();
        }
    }

    private void showDialogMoreClass() {
        M004MoreClassDialog dialog = new M004MoreClassDialog(mContext);
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
    public void updateData() {
        initData();
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
}
