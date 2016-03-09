package com.example.tpproject.project;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_screen_layout);
        //Emo
        setContentView(R.layout.player_screen_layout);
        TextView MoneyView = (TextView) findViewById(R.id.textViewMoney);
        MoneyView.setText("Money: " + MainActivity.money);
        TextView XpView = (TextView) findViewById(R.id.textViewXp);
        XpView.setText("Xp: " + MainActivity.xp);

        int xp_;
        for(xp_ = 100; xp_ <= MainActivity.xp; xp_= xp_ + 20){
            level++;
        }
        TextView LevelView = (TextView) findViewById(R.id.textViewLevel);
        LevelView.setText("You are level " + level);
    }
}
