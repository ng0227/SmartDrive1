package com.techhive.smartdrive.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techhive.smartdrive.R;

import java.util.ArrayList;

/**
 * Created by naman on 30/03/18.
 */




public class TrackProblemAdapter extends RecyclerView.Adapter<TrackProblemAdapter.TrackProblemViewHolder> {

    Context context;
    ArrayList<String> problems;

    public TrackProblemAdapter(Context context, ArrayList<String> problems) {
        this.context = context;
        this.problems = problems;
    }

    public void setProblems(ArrayList<String> problems){
        this.problems.clear();
        this.problems.addAll(problems);
        notifyDataSetChanged();
    }

    @Override
    public TrackProblemAdapter.TrackProblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.track_problems_item, parent, false);
        return new TrackProblemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackProblemAdapter.TrackProblemViewHolder holder, int position) {
      //  StudentBatch batch=studentBatches.get(position);
     /*   if(position==getItemCount()-1){
            holder.viewLine.setVisibility(View.GONE);
        }
        holder.batchName.setText(batch.getBatchName());
        holder.courseName.setText(batch.getCourseName());
        holder.subjectName.setText(batch.getSubjectName());*/
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }

    public class TrackProblemViewHolder extends RecyclerView.ViewHolder {

        //fvb

        public TrackProblemViewHolder(View itemView) {
            super(itemView);
         //   ButterKnife.bind(this, itemView);
        }
    }
}
