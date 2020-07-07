package com.techja.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.R;
import com.techja.myapplication.model.ClassEntity;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassHolder> {
    private Context context;
    private List<ClassEntity> listData;

    public ClassAdapter(Context context, List<ClassEntity> listData) {
        this.context = context;
        this.listData = listData;
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
        holder.tvClassName.setText(entity.getClassName());
        holder.tvClassCode.setText(entity.getClassCode());
        holder.tvClassName.setTag(entity);
        holder.btInfo.setTag(entity);
        holder.btTimetable.setTag(entity);
        holder.btLishs.setTag(entity);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ClassHolder extends RecyclerView.ViewHolder {
        TextView tvClassName, tvClassCode;
        Button btInfo, btTimetable, btLishs;

        public ClassHolder(@NonNull View itemView) {
            super(itemView);
            tvClassCode = itemView.findViewById(R.id.tv_class_code_m004);
            tvClassName = itemView.findViewById(R.id.tv_name_class_m004);
            btInfo = itemView.findViewById(R.id.bt_info_dialog);
            btTimetable = itemView.findViewById(R.id.bt_timetable_dialog);
            btLishs = itemView.findViewById(R.id.bt_lisths_dialog);

            itemView.findViewById(R.id.tb_item_class).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClassEntity entity = (ClassEntity) v.getTag();
                    callBack.clickButtonInfo(entity);
                }
            });

        }


    }

    private OnItemClassClickListener callBack;

    public void setOnClickItemClassListener(OnItemClassClickListener event) {
        callBack = event;
    }

    public interface OnItemClassClickListener {
        void clickButtonInfo(ClassEntity data);

        void clickButtonTimeTable(ClassEntity data);

        void clickButtonlistHS(ClassEntity data);

    }
}
