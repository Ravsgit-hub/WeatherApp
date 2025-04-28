package com.example.weatherapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Activity.MainActivity;
import com.example.weatherapp.Interface.OnItemClickListener;
import com.example.weatherapp.Model.ModelData;
import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateDataHolder> {
    Context context;
    List<ModelData> citylist;
    OnItemClickListener onItemClickListener;


    public StateAdapter(Context context, List<ModelData> citylist, OnItemClickListener onItemClickListener ) {
        this.context = context;
        this.citylist = citylist;
        this.onItemClickListener=onItemClickListener;
    }

    public StateAdapter(Context context, List<ModelData> citylist) {
        this.context = context;
        this.citylist = citylist;
    }



    @NonNull
    @Override
    public StateDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.statelist, null);
        return new StateDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateDataHolder holder, @SuppressLint("RecyclerView") int position) {

        ModelData md = citylist.get(position);
        holder.textname.setText(md.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, ""+citylist.get(position).getName(), Toast.LENGTH_SHORT).show();if (onItemClickListener!=null) {
                    int position=holder.getAdapterPosition();
                    onItemClickListener.onItemClick(position,md);
            }
        });
    }

    @Override
    public int getItemCount() {
        return citylist.size();
    }



  public class StateDataHolder extends RecyclerView.ViewHolder {
        TextView textname;
        public StateDataHolder(@NonNull View itemView) {
            super(itemView);
            textname = itemView.findViewById(R.id.textname);
        }

    }
}
