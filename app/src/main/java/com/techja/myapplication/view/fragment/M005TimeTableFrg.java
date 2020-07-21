package com.techja.myapplication.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM005TimetableCallBackToView;
import com.techja.myapplication.model.TimeTableEntity;
import com.techja.myapplication.presenter.M005TimeTablePresenter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.dialog.M005DialogMoreCoach;
import com.techja.myapplication.view.dialog.M005DialogMoreTimeTable;
import com.techja.myapplication.view.dialog.M005dialogEditTimeTable;
import com.techja.myapplication.view.event.OnM005TimetableCallBack;

public class M005TimeTableFrg extends BaseFragment<M005TimeTablePresenter, OnM005TimetableCallBack> implements OnM005TimetableCallBackToView {
    public static final String TAG = M005TimeTableFrg.class.getName();
    private LinearLayout lnTimetable;
    private Button btEditTable, btMoreCoach;
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

        btEditTable = findViewById(R.id.bt_more_table, this);
        btMoreCoach = findViewById(R.id.bt_more_coach, this);

        tvClassName = findViewById(R.id.tv_class_name_005, App.getInstance().getBoldFont());
        tvClassName.setText(getStorage().getClassEntity().getClassName().toUpperCase());
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
    public void addTimetable(String day, String time, String detail, String teacher, String note) {
        TimeTableEntity entity = new TimeTableEntity(day, time, detail, teacher, note);
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_day, null);
        TextView tvDay = item.findViewById(R.id.tv_day);
        TextView tvTime = item.findViewById(R.id.tv_time);
        TextView tvDetail = item.findViewById(R.id.tv_detail);
        TextView tvTeacher = item.findViewById(R.id.tv_teacher);
        TextView tvNote = item.findViewById(R.id.tv_note);

        tvDay.setTypeface(App.getInstance().getRegularFont());
        tvTime.setTypeface(App.getInstance().getRegularFont());
        tvDetail.setTypeface(App.getInstance().getRegularFont());
        tvTeacher.setTypeface(App.getInstance().getRegularFont());
        tvNote.setTypeface(App.getInstance().getRegularFont());

        tvDay.setText(day);
        tvTime.setText(time);
        tvDetail.setText(detail);
        tvTeacher.setText(teacher);
        tvNote.setText(note);
        tvNote.setTag(entity);

        item.findViewById(R.id.iv_edit).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimeTableEntity data = (TimeTableEntity) tvNote.getTag();
                getStorage().setTimeTableEntity(data);
                getStorage().setClassCode(classCode);
                M005dialogEditTimeTable dialog = new M005dialogEditTimeTable(mContext);
                dialog.show();

            }
        });
        lnTimetable.addView(item);
    }


}
