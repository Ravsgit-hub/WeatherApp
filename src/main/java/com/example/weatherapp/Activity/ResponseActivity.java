package com.example.weatherapp.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Adapter.ResponseAdapter;
import com.example.weatherapp.Model.weatherModel;
import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class ResponseActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<weatherModel> datalist=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        recyclerView=findViewById(R.id.recyclerview);
//        getSupportActionBar().setTitle("Response Activity");
        datalist= (List<weatherModel>) getIntent().getSerializableExtra("result");
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ResponseAdapter responseAdapter=new ResponseAdapter(datalist,this);

        recyclerView.setAdapter(responseAdapter);


    }
}