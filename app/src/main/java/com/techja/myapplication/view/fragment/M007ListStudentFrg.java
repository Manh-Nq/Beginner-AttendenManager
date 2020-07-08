package com.techja.myapplication.view.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM007ListStudentCallBackToView;
import com.techja.myapplication.model.StudentEntity;
import com.techja.myapplication.presenter.M007ListStudentPresenter;
import com.techja.myapplication.view.adapter.StudentAdapter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM007ListStudentCallBack;

import java.util.ArrayList;
import java.util.List;

public class M007ListStudentFrg extends BaseFragment<M007ListStudentPresenter, OnM007ListStudentCallBack> implements OnM007ListStudentCallBackToView {
    public static final String TAG = M007ListStudentFrg.class.getName();
    private RecyclerView rvStudent;
    private StudentAdapter adapter;
    private List<StudentEntity> listData;

    @Override
    protected M007ListStudentPresenter getPresenter() {
        return new M007ListStudentPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m007_frg_list_tudent;
    }

    @Override
    protected void initViews() {


        rvStudent = findViewById(R.id.rv_class_007);
        mPresenter.getUserInfoStudent();
        List<StudentEntity> listTmp = getStorage().getListStudent();
        initData(listTmp);
        rvStudent.setAdapter(adapter);
        rvStudent.setLayoutManager(new LinearLayoutManager(mContext));

    }

    private void initData(List<StudentEntity> listTmp) {
        String classCode = getStorage().getClassCode();
        if (listTmp.size() < 0) {
            return;
        }
        listData = new ArrayList<>();
        for (int i = 0; i < listTmp.size(); i++) {
            if (listTmp.get(i).getClassName().equals(classCode)) {
                listData.add(new StudentEntity(listTmp.get(i).getName(), listTmp.get(i).getClassName(), listTmp.get(i).getEmail()));
            }
        }
        adapter = new StudentAdapter(listData, mContext);
    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M006ListClassFrg.TAG);
    }


}
