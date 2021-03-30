package com.viet.exercise_javacore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> studentArrayList;

    public StudentAdapter(Context context, List<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }

    @Override
    public int getCount() {
        return studentArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView tvName,tvYear,tvNum,tvSpecialized;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.student,null);

            holder.tvName = view.findViewById(R.id.tetxviewName);
            holder.tvNum = view.findViewById(R.id.tetxviewNum);
            holder.tvYear = view.findViewById(R.id.tetxviewYear);
            holder.tvSpecialized = view.findViewById(R.id.tetxviewSpecialized);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        Student student = studentArrayList.get(i);
        if(student != null){
            holder.tvName.setText(student.getName());
            holder.tvNum.setText(student.getPhoneNumber());
            holder.tvYear.setText(student.getYearOfBird() + "");
            holder.tvSpecialized.setText(student.getSpecialized());
        }
        return view;
    }

}
