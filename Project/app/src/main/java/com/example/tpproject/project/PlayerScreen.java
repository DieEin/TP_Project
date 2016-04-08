package com.example.tpproject.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Tomi on 5.3.2016 г..
 */
public class PlayerScreen extends AppCompatActivity {

    public static List<String> itemsList = new ArrayList<String>();

    Bundle extras = getIntent().getExtras();
    public int xp = extras.getInt("xp");
    public int money = extras.getInt("money");
    public int level = extras.getInt("level");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Emo
        setContentView(R.layout.player_screen_layout);

        TextView MoneyView = (TextView) findViewById(R.id.textViewMoney);
        MoneyView.setText("Money: " + money);
        TextView XpView = (TextView) findViewById(R.id.textViewXp);
        XpView.setText("Xp: " + xp);

        TextView LevelView = (TextView) findViewById(R.id.textViewLevel);
        LevelView.setText("You are level " + level);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsList);

        ListView itemsListView = (ListView) findViewById(R.id.itemsList);
        itemsListView.setAdapter(itemsAdapter);
    }
}
