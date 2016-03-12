package com.example.tpproject.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.logging.Level;

/**
 * Created by Tomi on 5.3.2016 г..
 */
public class PlayerScreen extends AppCompatActivity {
    public static int level = 1;
    public static int money = 0;
    public static int xp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.player_screen_layout);
        //Emo
        setContentView(R.layout.player_screen_layout);

        //Intent getExtra = getIntent();
        //int money = getExtra.getIntExtra("money", 100);
        //int xp = getExtra.getIntExtra("xp", 100);

        TextView MoneyView = (TextView) findViewById(R.id.textViewMoney);
        MoneyView.setText("Money: " + money);
        TextView XpView = (TextView) findViewById(R.id.textViewXp);
        XpView.setText("Xp: " + xp);

        /*
        int exp;
        for(exp = 1; exp <= xp; exp += 30){

            level++;
        }*/

        TextView LevelView = (TextView) findViewById(R.id.textViewLevel);
        LevelView.setText("You are level " + level);
    }
}
