package com.techja.myapplication.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM008AttendanceCallbackToView;
import com.techja.myapplication.model.ItemDayEntity;
import com.techja.myapplication.presenter.M008StudentAttendancePresenter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM008StudentAttendanceCallback;

import java.util.List;

public class M008StudentAttendanceFrg extends BaseFragment<M008StudentAttendancePresenter, OnM008StudentAttendanceCallback> implements OnM008AttendanceCallbackToView {
    public static final String TAG = M008StudentAttendanceFrg.class.getName();
    private LinearLayout lnHistory;
    private ProgressBar progressBar;
    private LinearLayout lnFrg;
    private TextView tvToTalLate, tvTotalOT, tvToTal, tvCb;
    @Override
    protected M008StudentAttendancePresenter getPresenter() {
        return new M008StudentAttendancePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m008_frg_attendance_student;
    }

    @Override
    protected void initViews() {
        progressBar = findViewById(R.id.progress_008);
        lnFrg = findViewById(R.id.ln_frg_008);
        findViewById(R.id.iv_back,this);

        tvToTalLate = findViewById(R.id.tv_total_late);
        tvToTal = findViewById(R.id.tv_total);
        tvTotalOT = findViewById(R.id.tv_total_ot);
        tvCb = findViewById(R.id.tv_canhbao, App.getInstance().getmItalicFont());

        try {
            String classCode = getStorage().getStudentEntity().getClassName();
            String email = getStorage().getStudentEntity().getEmail();
            Log.d(TAG, "gokucheck: " + classCode + "\n" + email);
            mPresenter.getHistoryAttendanceStudent(email, classCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lnHistory = findViewById(R.id.ln_history_008);
        lnHistory.removeAllViews();
    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M007ListStudentFrg.TAG);
    }

    @Override
    public void addAttendanceToView(List<ItemDayEntity>listData) {
        try {
            int totalLate = 0;
            for (int i = 0; i < listData.size(); i++) {
                View itemView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_attendance, null);
                TextView tvDay = itemView.findViewById(R.id.tv_day);
                TextView tvToDay = itemView.findViewById(R.id.tv_today);
                TextView tvTime = itemView.findViewById(R.id.tv_time);
                TextView tvState = itemView.findViewById(R.id.tv_state);
                ItemDayEntity entity = listData.get(i);

                tvDay.setTypeface(App.getInstance().getRegularFont());
                tvTime.setTypeface(App.getInstance().getRegularFont());
                tvState.setTypeface(App.getInstance().getmMediumFont());
                tvDay.setText(entity.getDay().substring(0, entity.getDay().indexOf("/")));
                tvToDay.setText(entity.getDay().substring(entity.getDay().indexOf("/") + 1, entity.getDay().length()));
                tvTime.setText(entity.getTime());
                tvState.setText(entity.getState());
                if (entity.getState().equals("late")) {
                    tvState.setBackgroundResource(R.drawable.bg_time_history_late);
                    tvTime.setTextColor(getResources().getColor(R.color.colorred));
                    totalLate++;
                } else {
                    tvState.setBackgroundResource(R.drawable.bg_time_history_ontime);
                    tvState.setTextColor(getResources().getColor(R.color.colorViolet));
                    tvTime.setTextColor(getResources().getColor(R.color.colorBlue));
                }


                lnHistory.addView(itemView);
            }

            tvToTalLate.setText("Total late:  " + totalLate);
            tvTotalOT.setText("Total ontime: " + (listData.size() - totalLate));
            tvToTal.setText("Total: " + listData.size());
            if (totalLate>5) {
                tvCb.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {

        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.iv_back){
            mCallBack.showFragment(M007ListStudentFrg.TAG);
        }
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
