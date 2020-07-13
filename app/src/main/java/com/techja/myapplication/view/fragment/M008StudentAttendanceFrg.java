package com.techja.myapplication.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM008AttendanceCallbackToView;
import com.techja.myapplication.presenter.M008StudentAttendancePresenter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.event.OnM008StudentAttendanceCallback;

public class M008StudentAttendanceFrg extends BaseFragment<M008StudentAttendancePresenter, OnM008StudentAttendanceCallback> implements OnM008AttendanceCallbackToView {
    public static final String TAG = M008StudentAttendanceFrg.class.getName();
    private LinearLayout lnHistory;

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
    public void addAttendanceToView(String day, String time, String state) {
        try {

            View itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_attendance, null);
            TextView tvDay = itemView.findViewById(R.id.tv_day_008);
            TextView tvTime = itemView.findViewById(R.id.tv_time_008);
            TextView tvState = itemView.findViewById(R.id.tv_state_008);

            tvDay.setText(day);
            tvTime.setText(time);
            tvState.setText(state);
            if (state.equals("late")) {
                tvState.setBackgroundColor(getResources().getColor(R.color.colorRed));
            } else {
                tvState.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }

            lnHistory.addView(itemView);
        } catch (Exception e) {

        }
    }
}
