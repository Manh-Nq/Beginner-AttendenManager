package com.techja.myapplication.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM007ListStudentCallBackToView;
import com.techja.myapplication.model.StudentEntity;
import com.techja.myapplication.presenter.M007ListStudentPresenter;
import com.techja.myapplication.utils.CommonUtils;
import com.techja.myapplication.view.adapter.StudentAdapter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.dialog.M007DialogCallPhone;
import com.techja.myapplication.view.event.OnM007DialogCallPhoneCallbackToParent;
import com.techja.myapplication.view.event.OnM007ListStudentCallBack;

import java.util.ArrayList;
import java.util.List;

public class M007ListStudentFrg extends BaseFragment<M007ListStudentPresenter, OnM007ListStudentCallBack> implements OnM007ListStudentCallBackToView, StudentAdapter.clickItemStudentListener, OnM007DialogCallPhoneCallbackToParent {
    public static final String TAG = M007ListStudentFrg.class.getName();
    private RecyclerView rvStudent;
    private StudentAdapter adapter;
    private List<StudentEntity> listData;
    private List<StudentEntity> listTmp;
    private List<StudentEntity> tmp;
    private LinearLayout lnFrg;
    private ProgressBar progressBar;
    private EditText edtSeach;
    private ImageView ivDelete;

    @Override
    protected M007ListStudentPresenter getPresenter() {
        return new M007ListStudentPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m007_frg_list_student;
    }


    @Override
    protected void initViews() {

        edtSeach = findViewById(R.id.edt_seach, App.getInstance().getRegularFont());
        listTmp = getStorage().getListStudent();

        progressBar = findViewById(R.id.progress_bar_007);
        lnFrg = findViewById(R.id.ln_frg_007);
        findViewById(R.id.iv_back, this);
        rvStudent = findViewById(R.id.rv_class_007);
        mPresenter.getUserInfoStudent();
        ivDelete = findViewById(R.id.iv_delete, this);

        rvStudent.setLayoutManager(new LinearLayoutManager(mContext));
        seachStudent();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            mCallBack.showFragment(M006ListClassFrg.TAG);
        } else if (v.getId() == R.id.iv_delete) {
            edtSeach.setText("");
        }
    }


    private void seachStudent() {
        edtSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textOf(edtSeach).length() > 0) {
                    ivDelete.setVisibility(View.VISIBLE);
                }else {
                    ivDelete.setVisibility(View.GONE);
                }
                if (textOf(edtSeach).isEmpty()) {
                    tmp = listTmp;
                } else if (CommonUtils.getInstance().isCheckSeahch(textOf(edtSeach), listTmp)) {
                    tmp = CommonUtils.getInstance().getListRs();
                }
                initData(tmp);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initData(List<StudentEntity> listTmp) {
        String classCode = getStorage().getClassCode();
        try {
            listData = new ArrayList<>();
            if (listTmp.size() < 0) {
                return;
            }
            for (int i = 0; i < listTmp.size(); i++) {
                if (listTmp.get(i).getClassName().equals(classCode)) {
                    listData.add(new StudentEntity(listTmp.get(i).getName(), listTmp.get(i).getClassName(), listTmp.get(i).getEmail(), listTmp.get(i).getPhone()));
                }
            }
            adapter = new StudentAdapter(listData, mContext);
            adapter.setClickItemStudentListener(this);
            rvStudent.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M006ListClassFrg.TAG);
    }


    @Override
    public void showHistoryAttendanceStudent(StudentEntity data) {
        getStorage().setStudentEntity(data);
        showToast(data.getEmail());
        mCallBack.showFragment(M008StudentAttendanceFrg.TAG);
    }


    @Override
    public void callPhoneForStudent(StudentEntity entity) {
        getStorage().setNumberCallPhone(entity.getPhone());
        M007DialogCallPhone dialog = new M007DialogCallPhone(mContext);
        dialog.setOnCallBack(this);
        dialog.show();
    }

    @Override
    public void callPhoneForStudent(String phone) {
        if (phone.isEmpty() || phone == null) return;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    @Override
    public void showFragment(String tag) {

    }

    @Override
    public void updateData() {
        initData(listTmp);
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
