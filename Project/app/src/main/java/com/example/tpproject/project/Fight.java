package com.example.tpproject.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpproject.project.zones.FirstZone;

import java.util.Random;

/**
 * Created by Emo on 15.5.2016 г..
 */
public class Fight extends AppCompatActivity {

    public int randomizeInteger(int minValue, int maxValue) {
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt((maxValue - minValue) + 1) + minValue;
        return randomNumber;
    }

    public static int Dragon_HP = 1000;
    //public static int Player_HP = 1000;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_layout);

        ImageView ManSwing = (ImageView)findViewById(R.id.warr);
        ImageView Hitting = (ImageView)findViewById(R.id.hits);

        ProgressBar PlayerHPBar = (ProgressBar) findViewById(R.id.playerhp);
        PlayerHPBar.setProgress(PlayerScreen.health);
        ProgressBar DragonHPBar = (ProgressBar) findViewById(R.id.dragonhp);
        DragonHPBar.setProgress(Dragon_HP);

        while(true){
            int k = 0;
            for(int i = 0; i < 10000; i++) { k = i; }
            ManSwing.setImageResource(R.drawable.warrior);
            Hitting.setImageResource(R.drawable.start);

            for(int i = 0; i < 1500000; i++) { k = i; }
            ManSwing.setImageResource(R.drawable.warrior2);
            Hitting.setImageResource(R.drawable.manhit);
            Dragon_HP = Dragon_HP - randomizeInteger(5, 10);
            DragonHPBar.setProgress(Dragon_HP);

            for(int i = 0; i < 1500000; i++) { k = i; }
            ManSwing.setImageResource(R.drawable.warrior);
            Hitting.setImageResource(R.drawable.draghit);
            PlayerScreen.health = PlayerScreen.health - randomizeInteger(5, 10);
            PlayerHPBar.setProgress(PlayerScreen.health);

            if(Dragon_HP <= 0 && PlayerScreen.health > 0){
                String PlayerWon = "You WON!";
                Toast.makeText(Fight.this, PlayerWon, Toast.LENGTH_SHORT).show();
                int gainedMoney = randomizeInteger(20, 150);
                int gainedXP = randomizeInteger(5, 35);
                PlayerScreen.money += gainedMoney;
                PlayerScreen.xp += gainedXP;

                Dragon_HP = 100;

                break;
            }else if(PlayerScreen.health <= 0 && Dragon_HP > 0){
                String DragonWon = "You LOST! You don't gain any money!";
                Toast.makeText(Fight.this, DragonWon, Toast.LENGTH_SHORT).show();
                int gainedXP = randomizeInteger(5, 35);
                PlayerScreen.xp += gainedXP / 5;

                Dragon_HP = 100;

                break;
            }else if (Dragon_HP <= 0 && PlayerScreen.health <= 0) {
                String FightDraw = "Draw!";
                Toast.makeText(Fight.this, FightDraw, Toast.LENGTH_SHORT).show();
                int gainedMoney = randomizeInteger(20, 150);
                int gainedXP = randomizeInteger(5, 35);
                PlayerScreen.money += gainedMoney/5;
                PlayerScreen.xp += gainedXP / 2;

                Dragon_HP = 100;

                break;
            }
        }

        //Intent goToZone = new Intent(getApplicationContext(), FirstZone.class);
        //startActivity(goToZone);
    }
}
