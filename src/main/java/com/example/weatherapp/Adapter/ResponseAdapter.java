package com.example.weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Model.weatherModel;
import com.example.weatherapp.R;

import java.util.List;

public class ResponseAdapter extends  RecyclerView.Adapter<ResponseAdapter.ResponseHolder> {
List<weatherModel> datalist;
Context context;

    public ResponseAdapter(List<weatherModel> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public ResponseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ouptput,null);
        return new ResponseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResponseHolder holder, int position) {
        weatherModel wd=datalist.get(position);
        holder.text1.setText("City "+wd.getCity());
        holder.text2.setText(wd.getDescription());
        holder.text3.setText("sunrise "+wd.getSunrise());
        holder.text4.setText("degree "+wd.getDegree());
        holder.text5.setText("latitude "+wd.getLatitude());
        holder.text6.setText("longitude "+wd.getLongitude());
        holder.text7.setText("humidity  "+wd.getHumidity());
        holder.text8.setText("sunset  "+wd.getSunset());
        holder.text9.setText("windspeed  "+wd.getWind_speed());
        holder.text10.setText("temp   "+wd.getTemperature());

    }
    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public  class ResponseHolder extends RecyclerView.ViewHolder{
        TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10;

        public ResponseHolder(@NonNull View itemView) {
            super(itemView);
            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
            text3=itemView.findViewById(R.id.text3);
            text4=itemView.findViewById(R.id.text4);
            text5=itemView.findViewById(R.id.text5);
            text6=itemView.findViewById(R.id.text6);
            text7=itemView.findViewById(R.id.text7);
            text8=itemView.findViewById(R.id.text8);
            text9=itemView.findViewById(R.id.text9);
            text10=itemView.findViewById(R.id.text10);


        }
    }

}
