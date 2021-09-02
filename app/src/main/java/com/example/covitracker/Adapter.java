package com.example.covitracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<Model> mData;

    public Adapter(Context mContext, List<Model> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.state.setText(mData.get(position).getState());
        holder.stateActive.setText(mData.get(position).getTotalActive());
        holder.stateConfirmed.setText(mData.get(position).getTotalConfirmed());
        holder.stateDeceased.setText(mData.get(position).getTotalDeceased());
        holder.stateRecovered.setText(mData.get(position).getTotalRecovered());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView state, stateActive, stateConfirmed, stateDeceased, stateRecovered;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            state = itemView.findViewById(R.id.state);
            stateActive = itemView.findViewById(R.id.stateActive);
            stateConfirmed = itemView.findViewById(R.id.stateConfirmed);
            stateDeceased = itemView.findViewById(R.id.stateDeceased);
            stateRecovered = itemView.findViewById(R.id.stateRecovered);
        }
    }
}
