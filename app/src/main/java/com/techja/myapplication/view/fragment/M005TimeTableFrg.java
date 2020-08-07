package com.techja.myapplication.view.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM005TimetableCallBackToView;
import com.techja.myapplication.model.TimeTableEntity;
import com.techja.myapplication.presenter.M005TimeTablePresenter;
import com.techja.myapplication.view.adapter.TableAdapter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.dialog.M005DialogMoreCoach;
import com.techja.myapplication.view.dialog.M005DialogMoreTimeTable;
import com.techja.myapplication.view.dialog.M005dialogEditTimeTable;
import com.techja.myapplication.view.event.OnM005DialogEditTimeTableCallBackToParent;
import com.techja.myapplication.view.event.OnM005MoreTimeTableCallBackToParent;
import com.techja.myapplication.view.event.OnM005TimetableCallBack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M005TimeTableFrg extends BaseFragment<M005TimeTablePresenter, OnM005TimetableCallBack>
        implements OnM005TimetableCallBackToView, TableAdapter.clickItemTimeTableListener,
        OnM005DialogEditTimeTableCallBackToParent, OnM005MoreTimeTableCallBackToParent {
    public static final String TAG = M005TimeTableFrg.class.getName();
    private RecyclerView rvTimetable;
    private Button btEditTable, btMoreCoach;
    private String classCode;
    private TextView tvClassName;
    private ProgressBar progressBar;
    private LinearLayout lnFrg;
    private List<TimeTableEntity> listData = new ArrayList<>();
    private TableAdapter adapter;


    @Override
    protected M005TimeTablePresenter getPresenter() {
        return new M005TimeTablePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m005_timetable_class;
    }

    @Override
    protected void initViews() {

        lnFrg = findViewById(R.id.ln_m005_frg);
        progressBar = findViewById(R.id.progress_load_data);
        this.classCode = getStorage().getClassEntity().getClassCode();
        mPresenter.getClassTimetable(this.classCode);
        rvTimetable = findViewById(R.id.rv_time_table);

        findViewById(R.id.iv_back, this);
        btEditTable = findViewById(R.id.bt_more_table, this);
        btMoreCoach = findViewById(R.id.bt_more_coach, this);

        tvClassName = findViewById(R.id.tv_class_name_005, App.getInstance().getBoldFont());
        tvClassName.setText(getStorage().getClassEntity().getClassName().toUpperCase());

        rvTimetable.setLayoutManager(new LinearLayoutManager(mContext));

    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M004ClassFrg.TAG);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_more_table:
                showDialogMoretimeTable();
                break;
            case R.id.bt_more_coach:
                showDialogMoreCoach();
                break;
            case R.id.iv_back:
                mCallBack.showFragment(M004ClassFrg.TAG);
                break;
        }

    }

    private void showDialogMoreCoach() {
        getStorage().setClassCode(classCode);
        M005DialogMoreCoach dialog = new M005DialogMoreCoach(mContext);
        dialog.show();
    }


    private void showDialogMoretimeTable() {
        getStorage().setClassCode(this.classCode);
        M005DialogMoreTimeTable dialog = new M005DialogMoreTimeTable(mContext, classCode);
        dialog.setOnCallBack(this);
        dialog.show();
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
    public void loadDone(List<TimeTableEntity> listData) {
        initData(listData);
    }

    private void initData(List<TimeTableEntity> listData) {
        if (listData.size() < 0) return;
        this.listData = listData;
        Collections.sort(listData,TimeTableEntity::compareTo);
        adapter = new TableAdapter(mContext, listData);
        adapter.setClickItemTimeTableListener(this);
        rvTimetable.setAdapter(adapter);
    }


    @Override
    public void editTable(TimeTableEntity data) {
        getStorage().setTimeTableEntity(data);
        getStorage().setClassCode(classCode);
        M005dialogEditTimeTable dialog = new M005dialogEditTimeTable(mContext);
        dialog.setOnCallBack(this);
        dialog.show();
    }

    @Override
    public void showM009TKB(TimeTableEntity data) {
        getStorage().setTimeTableEntity(data);
        mCallBack.showFragment(M009ShowTBFrg.TAG);
    }

    @Override
    public void showFragment(String tag) {

    }

    @Override
    public void editDoneLoadToServer() {
        mPresenter.getClassTimetable(this.classCode);
        initData(listData);
    }

    @Override
    public void uploadMoreTimeTableDone() {
        mPresenter.getClassTimetable(this.classCode);
        initData(listData);
    }
}
