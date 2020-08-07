package com.techja.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.model.TimeTableEntity;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableHolder> {
    private Context context;
    private List<TimeTableEntity> listData;

    public TableAdapter(Context context, List<TimeTableEntity> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public TableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_day, parent, false);

        return new TableHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull TableHolder holder, int position) {
        TimeTableEntity entity = listData.get(position);
        holder.tvDay.setText(entity.getDay());
        holder.tvTime.setText(entity.getTime());
        holder.tvDetail.setText(entity.getDetail());
        holder.tvTeacher.setText(entity.getTeacher());

        holder.tvDay.setTag(entity);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class TableHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDay, tvTime, tvDetail, tvTeacher, tvNote;

        public TableHolder(@NonNull View itemView) {

            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            tvTeacher = itemView.findViewById(R.id.tv_teacher);


            tvDay.setTypeface(App.getInstance().getRegularFont());
            tvTime.setTypeface(App.getInstance().getRegularFont());
            tvDetail.setTypeface(App.getInstance().getRegularFont());
            tvTeacher.setTypeface(App.getInstance().getRegularFont());

            itemView.findViewById(R.id.iv_edit).setOnClickListener(this);
            itemView.findViewById(R.id.ln_item_time).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.iv_edit) {
                callBack.editTable((TimeTableEntity) tvDay.getTag());
            }else if(v.getId()==R.id.ln_item_time){
                callBack.showM009TKB((TimeTableEntity) tvDay.getTag());
            }
        }
    }

    private clickItemTimeTableListener callBack;

    public void setClickItemTimeTableListener(clickItemTimeTableListener event) {
        callBack = event;
    }

    public interface clickItemTimeTableListener {
        void editTable(TimeTableEntity data);
        void showM009TKB(TimeTableEntity data);
    }
}
