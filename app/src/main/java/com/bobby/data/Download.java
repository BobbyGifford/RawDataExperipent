package com.bobby.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bobby on 9/7/17.
 */

public class Download extends AsyncTask<String, Void, String> {
    private static final String TAG = "Download";
    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();

            while (data != -1){
                char current = (char) data;
                result += current;
                data = inputStreamReader.read();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i(TAG, s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String weather = jsonObject.getString("weather");
            Log.i(TAG, "Weather: " + weather);
            JSONArray array = new JSONArray(weather);

            double temp = jsonObject.getJSONObject("main").getDouble("temp");
            Log.i(TAG, "Temp is " + Double.toString(temp));

            for (int i = 0; i<array.length(); i++){
                JSONObject object = array.getJSONObject(i);

                String main = object.getString("main");
                String description = object.getString("description");
                Log.i(TAG, "Main: " + main);
                Log.i(TAG, "Description: " + description);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
