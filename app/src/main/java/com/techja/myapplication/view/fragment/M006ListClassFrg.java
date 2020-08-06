package com.techja.myapplication.view.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.GridLayoutManager;
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
    private ProgressBar progressBar;
    private LinearLayout lnFrg;


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
        progressBar = findViewById(R.id.progress_bar_006);
        lnFrg = findViewById(R.id.ln_frg_006);
        rvClass = rootView.findViewById(R.id.rv_class_006);
        mPresenter.getListClass();
        ivBack = findViewById(R.id.iv_back_006, this);
        rvClass.setLayoutManager(new GridLayoutManager(mContext,1));
        findViewById(R.id.iv_back, this);
    }

    private void initData() {

        listData = new ArrayList<>();
        listData = getStorage().getListClass();
        adapter = new ClassM006Adapter(listData, mContext);
        adapter.setClicklistener(this);
        rvClass.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back_006) {
            mCallBack.showFragment(M003MenuFrg.TAG);
        }else if(v.getId()==R.id.iv_back){
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

    @Override
    public void updateData() {
        initData();
    }

    @Override
    public void showProgressbar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            lnFrg.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            lnFrg.setVisibility(View.VISIBLE);
        }
    }
}

