package com.example.tpproject.project.zones;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.tpproject.project.Map;
import com.example.tpproject.project.PlayerScreen;
import com.example.tpproject.project.ProgressTask;
import com.example.tpproject.project.R;

/**
 * Created by Tomi on 7.3.2016 г..
 */
public class FirstZone extends Map {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_zone_layout);
    }
}
