package com.example.tpproject.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.logging.Level;

/**
 * Created by Tomi on 5.3.2016 г..
 */
public class PlayerScreen extends AppCompatActivity {
    private int level = 0;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.player_screen_layout);
        //Emo
        setContentView(R.layout.player_screen_layout);

        Intent getExtra = getIntent();
        int money = getExtra.getIntExtra("money", 100);
        int xp = getExtra.getIntExtra("xp", 100);

        TextView MoneyView = (TextView) findViewById(R.id.textViewMoney);
        MoneyView.setText("Money: " + money);
        TextView XpView = (TextView) findViewById(R.id.textViewXp);
        XpView.setText("Xp: " + xp);

        int exp;
        for(exp = 100; exp <= xp; exp= exp + 30){

            level++;
        }

        TextView LevelView = (TextView) findViewById(R.id.textViewLevel);
        LevelView.setText("You are level " + level);
    }
}
