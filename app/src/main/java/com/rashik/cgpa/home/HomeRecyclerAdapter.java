package com.rashik.cgpa.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rashik.cgpa.R;
import com.rashik.cgpa.model.Semester;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.viewHolder> {

    List<Semester>mySemesterList;

    public HomeRecyclerAdapter(List<Semester> mySemesterList) {
        this.mySemesterList = mySemesterList;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView semesterNameTextView,semesterCreditTextView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            semesterNameTextView=itemView.findViewById(R.id.semesterName_Item_TextView);
            semesterCreditTextView=itemView.findViewById(R.id.semesterCredit_Item_TextView);

        }
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.semester_recycler_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Semester currentSemester=mySemesterList.get(position);

        holder.semesterNameTextView.setText("Semester: "+currentSemester.getSemesterName());
        holder.semesterCreditTextView.setText("Credit: "+currentSemester.getSemesterCredit()+"");

    }

    @Override
    public int getItemCount() {
        if (mySemesterList==null || mySemesterList.isEmpty())
        {
            return 0;
        }
        else
        {
            return mySemesterList.size();
        }

    }


}
