package com.techja.myapplication.view.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM006ListClassCallBackToView;
import com.techja.myapplication.model.ClassEntity;
import com.techja.myapplication.presenter.M006ListClassPresenter;
import com.techja.myapplication.view.adapter.ClassM006Adapter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM006ListClassCallback;

import java.util.ArrayList;
import java.util.List;

public class M006ListClassFrg extends BaseFragment<M006ListClassPresenter, OnM006ListClassCallback> implements OnM006ListClassCallBackToView, ClassM006Adapter.M006AdapterListener {
    public static final String TAG = M006ListClassFrg.class.getName();
    private ImageView ivBack;
    private RecyclerView rvClass;
    private ClassM006Adapter adapter;
    private List<ClassEntity> listData;


    @Override
    protected M006ListClassPresenter getPresenter() {
        return new M006ListClassPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m006_frg_history;
    }

    @Override
    protected void initViews() {
        rvClass = rootView.findViewById(R.id.rv_class_006);
        mPresenter.getListClass();
        initData();
        ivBack = findViewById(R.id.iv_back_006, this);
        rvClass.setAdapter(adapter);
        rvClass.setLayoutManager(new LinearLayoutManager(mContext));

    }

    private void initData() {

        listData = new ArrayList<>();
        listData = getStorage().getListClass();
        adapter = new ClassM006Adapter(listData, mContext);
        adapter.setClicklistener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back_006) {
            mCallBack.showFragment(M003MenuFrg.TAG);
        }
    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M003MenuFrg.TAG);
    }


    @Override
    public void clickButtonListHS(ClassEntity data) {
        getStorage().setClassCode(data.getClassCode());
        mCallBack.showFragment(M007ListStudentFrg.TAG);

    }
}

