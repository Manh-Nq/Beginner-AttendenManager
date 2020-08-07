package com.techja.myapplication.view.fragment;

import android.view.View;
import android.widget.TextView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM009ShowTBCallBackToView;
import com.techja.myapplication.model.TimeTableEntity;
import com.techja.myapplication.presenter.M009ShowTBPresenter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM009ShowTBCallBack;

public class M009ShowTBFrg extends BaseFragment<M009ShowTBPresenter, OnM009ShowTBCallBack> implements OnM009ShowTBCallBackToView {
    public static final String TAG = M009ShowTBFrg.class.getName();
    private TextView tvDay, tvHours, tvDetail, tvcoach, tvNote;

    @Override
    protected M009ShowTBPresenter getPresenter() {
        return new M009ShowTBPresenter(this);
    }


    @Override
    protected void initViews() {
        TimeTableEntity entity = getStorage().getTimeTableEntity();
        if (entity == null) return;


        tvDay = findViewById(R.id.tv_day_dialog, App.getInstance().getRegularFont());
        tvHours = findViewById(R.id.tv_time_dialog, App.getInstance().getRegularFont());
        tvDetail = findViewById(R.id.tv_detail_dialog, App.getInstance().getRegularFont());
        tvcoach = findViewById(R.id.tv_coach_dialog, App.getInstance().getRegularFont());
        tvNote = findViewById(R.id.tv_note_dialog, App.getInstance().getRegularFont());

        tvDay.setText(entity.getDay());
        tvHours.setText(entity.getTime());
        tvDetail.setText(entity.getDetail());
        tvcoach.setText(entity.getTeacher());
        tvNote.setText(entity.getNote());

        findViewById(R.id.iv_back, this);
    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M005TimeTableFrg.TAG);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            showPreviousFrg();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m009_frg_showtkb;
    }


}
