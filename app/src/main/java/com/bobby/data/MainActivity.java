package com.bobby.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Download download = new Download();
        download.execute("http://api.openweathermap.org/data/2.5/weather?" +
                "q=London");
    }
}
