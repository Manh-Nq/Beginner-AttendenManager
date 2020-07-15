package com.techja.myapplication.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM005TimetableCallBackToView;
import com.techja.myapplication.presenter.M005TimeTablePresenter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.dialog.M005DialogEditTimeTable;
import com.techja.myapplication.view.event.OnM005TimetableCallBack;

public class M005TimeTableFrg extends BaseFragment<M005TimeTablePresenter, OnM005TimetableCallBack> implements OnM005TimetableCallBackToView {
    public static final String TAG = M005TimeTableFrg.class.getName();
    private LinearLayout lnTimetable;
    private Button btEditTable;
    private String classCode;
    private TextView tvClassName;
    private ProgressBar progressBar;
    private LinearLayout lnFrg;


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
        lnTimetable = findViewById(R.id.ln_time_table);
        lnTimetable.removeAllViews();
        btEditTable = findViewById(R.id.bt_edit_table, this);
        tvClassName = findViewById(R.id.tv_class_name_005);
        tvClassName.setText(getStorage().getClassEntity().getClassName());
    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M004ClassFrg.TAG);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_edit_table) {
            getStorage().setClassCode(this.classCode);
            M005DialogEditTimeTable dialog = new M005DialogEditTimeTable(mContext, classCode);
            dialog.show();
        }
    }

    @Override
    public void addTimetable(String day, String time, String detail, String teacher, String note) {
        try {
            View item = LayoutInflater.from(mContext).inflate(R.layout.item_day, null);
            TextView tvDay = item.findViewById(R.id.tv_day);
            TextView tvTime = item.findViewById(R.id.tv_time);
            TextView tvDetail = item.findViewById(R.id.tv_detail);
            TextView tvTeacher = item.findViewById(R.id.tv_teacher);
            TextView tvNote = item.findViewById(R.id.tv_note);
            tvDay.setText(day);
            tvTime.setText(time);
            tvDetail.setText(detail);
            tvTeacher.setText(teacher);
            tvNote.setText(note);
            lnTimetable.addView(item);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void showProgressBar(boolean b) {
        if(b){
            progressBar.setVisibility(View.VISIBLE);
            lnFrg.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            lnFrg.setVisibility(View.VISIBLE);
        }
    }
}
