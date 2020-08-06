package com.techja.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.model.ClassEntity;

import java.util.List;

public class ClassM006Adapter extends RecyclerView.Adapter<ClassM006Adapter.ClassM006Holder> {
    private List<ClassEntity> listData;
    private Context context;

    public ClassM006Adapter(List<ClassEntity> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ClassM006Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_class_006, parent, false);
        return new ClassM006Holder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull ClassM006Holder holder, int position) {
        ClassEntity entity = listData.get(position);
        holder.tvClassCode.setText(entity.getClassCode());
        holder.tvClassName.setText(entity.getClassName());
        holder.tvClassName.setTag(entity);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ClassM006Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvClassName, tvClassCode;
      LinearLayout lnTab;

        public ClassM006Holder(@NonNull View itemView) {
            super(itemView);
            tvClassName = itemView.findViewById(R.id.tv_name_class_m006);
            tvClassName.setTypeface(App.getInstance().getRegularFont());
            tvClassCode = itemView.findViewById(R.id.tv_class_code_m006);
            tvClassCode.setTypeface(App.getInstance().getRegularFont());
           itemView.findViewById(R.id.ln_item_class).setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.ln_item_class ) {
                ClassEntity data = (ClassEntity) tvClassName.getTag();
                callBack.clickButtonListHS(data);
            }
        }
    }

    private M006AdapterListener callBack;


    public void setClicklistener(M006AdapterListener event) {
        callBack = event;
    }

    public interface M006AdapterListener {
        void clickButtonListHS(ClassEntity data);
    }
}
