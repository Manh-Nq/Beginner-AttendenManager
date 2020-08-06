package com.techja.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.model.ClassEntity;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassHolder> {
    private List<ClassEntity> listData;
    private Context context;

    public ClassAdapter(List<ClassEntity> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_class, parent, false);
        return new ClassHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        ClassEntity entity = listData.get(position);
        holder.tvClassName.setText(entity.getClassName().toUpperCase());
        holder.tvClassCode.setText(entity.getClassCode().toUpperCase());
        holder.tvClassCode.setTag(entity);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ClassHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvClassName, tvClassCode;
        ImageView ivEdit;


        public ClassHolder(@NonNull View itemView) {
            super(itemView);
            tvClassCode = itemView.findViewById(R.id.tv_class_code_m004);
            tvClassCode.setTypeface(App.getInstance().getRegularFont());
            tvClassName = itemView.findViewById(R.id.tv_name_class_m004);
            tvClassName.setTypeface(App.getInstance().getRegularFont());
            ivEdit = itemView.findViewById(R.id.iv_edit_class);
            ivEdit.setOnClickListener(this);
            itemView.findViewById(R.id.ln_class_code).setOnClickListener(this);
            itemView.findViewById(R.id.ln_class_name).setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            ClassEntity entity = (ClassEntity) tvClassCode.getTag();
            if (v.getId() == R.id.ln_class_name || v.getId() == R.id.ln_class_code) {
                callBack.clickButtonTimetable(entity);
            } else if (v.getId() == R.id.iv_edit_class) {
                callBack.showDiaLogEditClass(entity);
            }
        }
    }

    private adapterListener callBack;

    public void setCallBackItem(adapterListener event) {
        callBack = event;
    }

    public interface adapterListener {

        void showDiaLogEditClass(ClassEntity entity);

        void clickButtonTimetable(ClassEntity data);

    }
}
