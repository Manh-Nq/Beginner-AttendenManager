package com.techja.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvClassName, tvEmail;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_hs_007);
            tvClassName = itemView.findViewById(R.id.tv_class_name_007);
            tvEmail = itemView.findViewById(R.id.tv_email_007);
        }
    }
}
