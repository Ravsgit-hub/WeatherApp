package com.example.weatherapp.Wrapper;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpWrapper {
    InputStream inputStream;
    HttpURLConnection connection;
    public String HttpGet(String url) {
        try{
        URL ur = new URL(url);
        HttpsURLConnection urlConnection;
        urlConnection = (HttpsURLConnection) ur.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);//POST or GET
        urlConnection.connect();
            int status = urlConnection.getResponseCode();
            Log.e("STatus",Integer.toString(status) );

            StringBuilder sb = new StringBuilder();
            inputStream=urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String response="";
            while ((line = bufferedReader.readLine())!=null){
                sb.append(line).append("\n");
                response = sb.toString().substring(0, sb.toString().length()-1);
                Log.e("line", String.valueOf(sb));
            }

            inputStream.close();
            urlConnection.disconnect();

            return response;
    } catch(Exception e)
        {
            e.printStackTrace();

        }
        return "";
}


}
