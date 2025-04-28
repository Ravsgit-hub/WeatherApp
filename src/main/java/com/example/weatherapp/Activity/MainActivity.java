package com.example.weatherapp.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Adapter.StateAdapter;
import com.example.weatherapp.Interface.OnItemClickListener;
import com.example.weatherapp.Model.ModelData;
import com.example.weatherapp.Model.weatherModel;
import com.example.weatherapp.R;
import com.example.weatherapp.Wrapper.HttpWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    RecyclerView recyclerview;
     EditText searchedt;
    Button filter;

   static Context ct;
    List<ModelData> citylist=new ArrayList<>();
    ArrayList<ModelData> filterarraylist=new ArrayList<>();
    static List<weatherModel> datalist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview=findViewById(R.id.recyclerview);
        searchedt=findViewById(R.id.searchedt);

        StateAdapter stateAdapter=new StateAdapter(MainActivity.this,citylist, this::onItemClick);
        recyclerview.setAdapter(stateAdapter);

        citylist=getallCity();
        searchedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filter=searchedt.getText().toString().trim();
                filterarraylist=Filterlist(filter);
                StateAdapter st=new StateAdapter(getApplicationContext(),filterarraylist);
                recyclerview.setAdapter(st);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public   List<ModelData> getallCity()
    {
        try {
            String msg = "{'cities': [{\"name\": \"Mumbai\"},{\"name\": \"Pune\"},{\"name\": \"Nagpur\"}, {\"name\": \"Nashik\"},  {\"name\": \"Aurangabad\"},  {\"name\": \"Solapur\"},  {\"name\": \"Thane\"}, {\"name\": \"Amravati\"},{\"name\":\"Kolhapur\"}, {\"name\": \"Sangli\"},  {\"name\": \"Navi Mumbai\"},  {\"name\": \"Satara\"},   {\"name\": \"Ahmednagar\"},   {\"name\": \"Jalgaon\"},  {\"name\": \"Latur\"},   {\"name\": \"Akola\"}, {\"name\": \"Dhule\"},   {\"name\": \"Chandrapur\"},  {\"name\": \"Parbhani\"}, {\"name\": \"Jalna\"},   {\"name\": \"Raigad\"}, {\"name\": \"Osmanabad\"},{\"name\": \"Ratnagiri\"},   {\"name\": \"Wardha\"},   {\"name\": \"Hingoli\"},  {\"name\": \"Yavatmal\"}, {\"name\": \"Sindhudurg\"},  {\"name\": \"Buldhana\"}, {\"name\": \"Gondia\"},  {\"name\": \"Washim\"}]}";
            JSONObject jsonobj = new JSONObject(msg);
            JSONArray jsonarray = jsonobj.getJSONArray("cities");
//        JSONObject obj = jsonarray.getJSONObject(0);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject object = jsonarray.getJSONObject(i);
                String Name=object.getString("name");
                ModelData modelData=new ModelData(Name);
                citylist.add(modelData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        return citylist;
    }
    ArrayList<ModelData> Filterlist(String filter){
        ArrayList<ModelData> filteredlist=new ArrayList<>();

        for(ModelData city:citylist) {
            Log.e("city", city.getName());
            if (city.getName().startsWith(filter.toUpperCase())) {
                filteredlist.add(city);
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(MainActivity.this, "match not found", Toast.LENGTH_SHORT).show();
        }
        return filteredlist;
    }



    public class Weatherasync extends AsyncTask<String,String,String> {
        String city;
        ProgressDialog pd;

        public Weatherasync(String city) {
            this.city = city;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("Loading in progress");
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=6e98e3c83b621f1cabb1b8ba15044c5e";

            HttpWrapper httpWrapper=new HttpWrapper();
            String output=httpWrapper.HttpGet(url);

            try {
                JSONObject jsonobject = new JSONObject(output);
                JSONObject j1 = jsonobject.getJSONObject("coord");
                String longitude = j1.getString("lon");
                String latitude = j1.getString("lat");
                JSONArray jarray1 = jsonobject.getJSONArray("weather");
                JSONObject J2 = jarray1.getJSONObject(0);
                String description = J2.getString("description");
                JSONObject J3 = jsonobject.getJSONObject("main");
                String temperature = J3.getString("temp");
                String humidity = J3.getString("humidity");
                JSONObject J4 = jsonobject.getJSONObject("wind");
                String wind_speed = J4.getString("speed");
                String Degree = J4.getString("deg");
                JSONObject J5 = jsonobject.getJSONObject("sys");
                String sunrise = J5.getString("sunrise");
                String sunset = J5.getString("sunset");
                String city = jsonobject.getString("name");
                weatherModel wd=new weatherModel(city,longitude,latitude,description,temperature,humidity,wind_speed,Degree,sunrise,sunset);
                datalist.add(wd);
            }catch( Exception e)
            {
                e.printStackTrace();

            }
            return datalist.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent ib=new Intent(MainActivity.this, ResponseActivity.class);
            ib.putExtra("result", (Serializable) datalist);
            startActivity(ib);
        }
    }
    @Override
    public void onItemClick(int position, ModelData md) {

        Toast.makeText(MainActivity.this, "output===="+md.getName(), Toast.LENGTH_SHORT).show();
        String city=md.getName();
        Weatherasync weatherasync=new Weatherasync(city);
        weatherasync.execute();

    }


}