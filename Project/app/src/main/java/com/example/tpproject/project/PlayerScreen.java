package com.example.tpproject.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Tomi on 5.3.2016 г..
 */
public class PlayerScreen extends AppCompatActivity {
    public static String playerName = "Player Name";
    public static int level = 1;
    public static int money = 0;
    public static int xp = 0;
    public static int energy = 100;
    public static int health = 50;
    public static int intelligence = 10;
    public static int power = 10;
    public static int speed = 10;
    public static List<String> itemsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Emo
        setContentView(R.layout.player_screen_layout);

        TextView playerNameField = (TextView) findViewById(R.id.player_name);
        playerNameField.setText(playerName);

        TextView MoneyView = (TextView) findViewById(R.id.textViewMoney);
        MoneyView.setText("Money: " + money);
        TextView XpView = (TextView) findViewById(R.id.textViewXp);
        XpView.setText("Xp: " + xp);

        TextView LevelView = (TextView) findViewById(R.id.textViewLevel);
        LevelView.setText("You are level " + level);

        ProgressBar energyProgressBar = (ProgressBar) findViewById(R.id.energyBar);
        energyProgressBar.setProgress(energy);

        ProgressBar healthBar = (ProgressBar) findViewById(R.id.healthBar);
        healthBar.setProgress(health);;

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsList);

        ListView itemsListView = (ListView) findViewById(R.id.itemsList);
        itemsListView.setAdapter(itemsAdapter);

        TextView intelligenceTextView = (TextView) findViewById(R.id.intelligenceSkill);
        intelligenceTextView.setText("Intelligence: " + intelligence);

        TextView powerTextView = (TextView) findViewById(R.id.powerSkill);
        powerTextView.setText("Power: " + power);

        TextView speedTextView = (TextView) findViewById(R.id.speedSkill);
        speedTextView.setText("Speed: " + speed);
    }
    public void buttonIntOnClick(View Intelligence){
        int IntCostCounter = intelligence + intelligence/2;
        if(money >= IntCostCounter){
            intelligence++;
            money -= IntCostCounter;
            TextView intelligenceTextView = (TextView) findViewById(R.id.intelligenceSkill);
            intelligenceTextView.setText("Intelligence: " + String.valueOf(intelligence));
            TextView MoneyView = (TextView) findViewById(R.id.textViewMoney);
            MoneyView.setText("Money: " + money);
            Toast.makeText(PlayerScreen.this, "You spend " + IntCostCounter + " money!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(PlayerScreen.this, "You don't have enought money!", Toast.LENGTH_SHORT).show();
        }
    }

    public void buttonPowerOnClick(View Power){
        int PowerCostCounter = power + power/2;
        if(money >= PowerCostCounter){
            power++;
            money -= PowerCostCounter;
            TextView powerTextView = (TextView) findViewById(R.id.powerSkill);
            powerTextView.setText("Power: " + String.valueOf(power));
            TextView MoneyView = (TextView) findViewById(R.id.textViewMoney);
            MoneyView.setText("Money: " + money);
            Toast.makeText(PlayerScreen.this, "You spend " + PowerCostCounter + " money!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(PlayerScreen.this, "You don't have enought money!", Toast.LENGTH_SHORT).show();
        }
    }

    public void buttonSpeedOnClick(View Speed){
        int SpeedCostCounter = speed + speed/2;
        if(money >= SpeedCostCounter){
            speed++;
            money -= SpeedCostCounter;
            TextView speedTextView = (TextView) findViewById(R.id.speedSkill);
            speedTextView.setText("Speed: " + String.valueOf(speed));
            TextView MoneyView = (TextView) findViewById(R.id.textViewMoney);
            MoneyView.setText("Money: " + money);
            Toast.makeText(PlayerScreen.this, "You spend " + SpeedCostCounter + " money!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(PlayerScreen.this, "You don't have enought money!", Toast.LENGTH_SHORT).show();
        }
    }
}
