package com.techja.myapplication.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM005EditTimeTableCallBackToView;
import com.techja.myapplication.presenter.M005EditTimeTablePresenter;
import com.techja.myapplication.view.base.BaseDialog;
import com.techja.myapplication.view.event.OnM005EditTimeTableCallBackToParent;

public class M005DialogEditTimeTable extends BaseDialog<M005EditTimeTablePresenter, OnM005EditTimeTableCallBackToParent> implements OnM005EditTimeTableCallBackToView {
    private static final String KEY_LOADING = "KEY_LOADING";
    private TextView tvCancel, tvOk;
    private String classCode;
    private EditText edtDay, edtTime, edtDetail, edtCoach, edtNote;
    private String day, time, detail, coach, note;


    public M005DialogEditTimeTable(@NonNull Context context, String classCode) {
        super(context);
        this.classCode = classCode;

    }

    @Override
    protected M005EditTimeTablePresenter getPresenter() {
        return new M005EditTimeTablePresenter(this);
    }

    @Override
    protected void initViews() {

        tvCancel = findViewById(R.id.tv_cancel_edit_tb, this);
        tvOk = findViewById(R.id.tv_ok_edit_tb, this);
        edtDay = findViewById(R.id.edt_day_dialog_005);
        edtTime = findViewById(R.id.edt_time_dialog_005);
        edtDetail = findViewById(R.id.edt_detail_dialog_005);
        edtCoach = findViewById(R.id.edt_coach_dialog_005);
        edtNote = findViewById(R.id.edt_note_dialog_005);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel_edit_tb) {
            dismiss();
        } else if (v.getId() == R.id.tv_ok_edit_tb) {
            uploadTime();
        }
    }




    private void uploadTime() {
        day = textOf(edtDay);
        time = textOf(edtTime);
        detail = textOf(edtDetail);
        coach = textOf(edtCoach);
        note = textOf(edtNote);

        if (checkValid(day, time, detail, coach, note)) {
            mPresenter.uploadTimeTable(this.classCode, day, time, detail, coach, note);
            dismiss();
        }
    }

    private boolean checkValid(String day, String time, String detail, String coach, String note) {
        if (day.isEmpty()) {
            edtDay.setError("Please enter day");
            edtDay.requestFocus();
            return false;
        }
        if (time.isEmpty()) {
            edtTime.setError("Please enter time");
            edtTime.requestFocus();
            return false;
        }
        if (detail.isEmpty()) {
            edtDetail.setError("Please enter detail");
            edtDetail.requestFocus();
            return false;
        }

        if (coach.isEmpty()) {
            edtCoach.setError("Please enter your description");
            edtCoach.requestFocus();
            return false;
        }
        if (note.isEmpty()) {
            edtNote.setError("Please enter your duration");
            edtNote.requestFocus();
            return false;
        }

        return true;


    }


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_edit_time_table;
    }
}
