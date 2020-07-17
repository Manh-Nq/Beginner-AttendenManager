package com.techja.myapplication.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM005DialogChangeTimeTableCallBackToView;
import com.techja.myapplication.model.TimeTableEntity;
import com.techja.myapplication.presenter.M005dialogChangeTimeTablePresenter;
import com.techja.myapplication.view.base.BaseDialog;
import com.techja.myapplication.view.event.OnM005DialogChangeTimeTableCallBackToParent;

public class M005dialogChangeTimeTable extends BaseDialog<M005dialogChangeTimeTablePresenter, OnM005DialogChangeTimeTableCallBackToParent> implements OnM005DialogChangeTimeTableCallBackToView {
    private EditText edtDay, edtTime, edtDetail, edtTeacher, edtNote;
    private TimeTableEntity data;

    public M005dialogChangeTimeTable(@NonNull Context context) {
        super(context);
    }

    @Override
    protected M005dialogChangeTimeTablePresenter getPresenter() {
        return new M005dialogChangeTimeTablePresenter(this);
    }

    @Override
    protected void initViews() {
        data = getStorage().getTimeTableEntity();

        findViewById(R.id.tv_cancel_edit_change_tb005, this);
        findViewById(R.id.tv_ok_edit_change_tb005, this);

        edtDay = findViewById(R.id.edt_day_dialog_change_tb005);
        edtTime = findViewById(R.id.edt_time_dialog_change_tb005);
        edtDetail = findViewById(R.id.edt_detail_dialog_change_tb005);
        edtTeacher = findViewById(R.id.edt_coach_dialog_change_tb005);
        edtNote = findViewById(R.id.edt_note_dialog_change_tb005);

        edtDay.setText(data.getDay());
        edtTime.setText(data.getTime());
        edtDetail.setText(data.getDetail());
        edtTeacher.setText(data.getTeacher());
        edtNote.setText(data.getNote());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel_edit_change_tb005:
                dismiss();
                break;
            case R.id.tv_ok_edit_change_tb005:
                saveToServer();

                break;

        }
    }

    private void saveToServer() {
        String day = textOf(edtDay),
                time = textOf(edtTime),
                detail = textOf(edtDetail),
                teacher = textOf(edtTeacher),
                note = textOf(edtNote);
        if (checkValid(day, time, detail, teacher, note)) {
            String classCode = getStorage().getClassCode();
            mPresenter.saveTimeTableToSever(day, time, detail, teacher, note, classCode);
            dismiss();
        }

    }

    private boolean checkValid(String day, String time, String detail, String teacher, String note) {
        if (day.isEmpty()) {
            edtDay.setError("Please enter your day");
            edtDay.requestFocus();
            return false;

        }
        if (time.isEmpty()) {
            edtTime.setError("Please enter class time");
            edtTime.requestFocus();
            return false;
        }

        if (detail.isEmpty()) {
            edtDetail.setError("Please enter class detail");
            edtDetail.requestFocus();
            return false;
        }
        if (teacher.isEmpty()) {
            edtTeacher.setError("Please enter name teacher");
            edtTeacher.requestFocus();
            return false;
        }
        if (note.isEmpty()) {
            edtNote.setError("Please enter class note");
            edtNote.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_m005_change_timetable;
    }



}
