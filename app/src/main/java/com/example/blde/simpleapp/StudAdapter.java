package com.example.blde.simpleapp;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by BLDE on 7/28/2018.
 */

public class StudAdapter extends RecyclerView.Adapter <item>{
    List<student> studentList;

    public StudAdapter(List<student>studentList){
        this.studentList=studentList;
    }



    @Override
        public item onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,null,false);
        return  new item(view);
    }

    @Override
    public void onBindViewHolder(item holder, int position) {
       student student =studentList.get(holder.getAdapterPosition());
        holder.image.setImageResource(student.image);
        holder.name.setText(student.getName());
        holder.DOB.setText(student.getDOB());

    }

    @Override
    public int getItemCount() {
        if(studentList !=null){
            return  studentList.size();
        }else {

            return 0;
        }

    }
}
