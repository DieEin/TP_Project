package com.example.tpproject.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomi on 17.4.2016 г..
 */
public class Achievments extends AppCompatActivity {
    public static List<String> achievmentsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.achievments_layout);

        TextView showPlayerName = (TextView) findViewById(R.id.achievmentsPlayerName);
        showPlayerName.setText(PlayerScreen.playerName + "'s achievments");

        ArrayAdapter<String> achievmentsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, achievmentsList);

        ListView achievmentsListView = (ListView) findViewById(R.id.achievmentsListView);
        achievmentsListView.setAdapter(achievmentsAdapter);
    }
}
