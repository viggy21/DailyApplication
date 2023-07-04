package com.fit2081.dailyapp;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.dailyapp.provider.Task;

import java.util.List;

public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.ViewHolder>{

    List<Task> data;

    public void setData(List<Task> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); // CardView inflated as a RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.taskTv.setText(data.get(position).getTask());
        holder.taskCb.setText(data.get(position).getTask());

        // set a listener
        holder.taskCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // if the checkbox button is checked, strike through the text
                    if (isChecked) {
                        //String checkBoxText = checkBox.getText().toString();
                        System.out.println("checkbox ticked");
                        holder.taskCb.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    else {
                        // make the text not strike through
                        //holder.taskCb.setPaintFlags();
                        holder.taskCb.setPaintFlags(holder.taskCb.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        else {
            return data.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView taskTv;
        public CheckBox taskCb;
        public ViewHolder(View itemView) {
            super(itemView);
            taskTv = itemView.findViewById(R.id.to_do_item);
            taskCb = itemView.findViewById(R.id.checkbox);
        }
    }
}
