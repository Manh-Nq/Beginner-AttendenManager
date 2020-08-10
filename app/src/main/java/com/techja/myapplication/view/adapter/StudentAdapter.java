package com.techja.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.model.StudentEntity;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    private List<StudentEntity> listData;
    private Context context;

    public StudentAdapter(List<StudentEntity> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_m007_list_hs, parent, false);
        return new StudentHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        StudentEntity entity = listData.get(position);
        holder.tvName.setText(entity.getName());
        holder.tvClassName.setText(entity.getClassName());
        holder.tvEmail.setText(entity.getEmail());
        holder.tvPhone.setText(entity.getPhone());
        holder.tvEmail.setTag(entity);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName, tvClassName, tvEmail, tvPhone;


        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_hs_007);
            tvClassName = itemView.findViewById(R.id.tv_class_name_007);
            tvEmail = itemView.findViewById(R.id.tv_email_007);
            tvPhone = itemView.findViewById(R.id.tv_phone_007);
             itemView.findViewById(R.id.iv_phone_007).setOnClickListener(this);

            tvName.setTypeface(App.getInstance().getRegularFont());
            tvClassName.setTypeface(App.getInstance().getRegularFont());
            tvEmail.setTypeface(App.getInstance().getRegularFont());
            tvPhone.setTypeface(App.getInstance().getRegularFont());

            itemView.findViewById(R.id.tv_chitiet).setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tv_chitiet) {
                callBack.showHistoryAttendanceStudent((StudentEntity) tvEmail.getTag());
            } else if (v.getId() == R.id.iv_phone_007) {
                callBack.callPhoneForStudent((StudentEntity) tvEmail.getTag());
            }
        }
    }

    private clickItemStudentListener callBack;

    public void setClickItemStudentListener(clickItemStudentListener event) {
        callBack = event;
    }

    public interface clickItemStudentListener {
        void showHistoryAttendanceStudent(StudentEntity data);

        void callPhoneForStudent(StudentEntity entity);
    }

    public void setListData(List<StudentEntity> listData) {
        this.listData = listData;
    }
}
