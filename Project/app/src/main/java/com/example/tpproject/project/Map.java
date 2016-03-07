package com.example.tpproject.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.tpproject.project.zones.FirstZone;
import com.example.tpproject.project.zones.FourthZone;
import com.example.tpproject.project.zones.SecondZone;
import com.example.tpproject.project.zones.ThirdZone;

import java.util.Random;

/**
 * Created by Tomi on 5.3.2016 г..
 */
public class Map extends AppCompatActivity {
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map_layout);

        Toolbar mapToolBar = (Toolbar) findViewById(R.id.map_toolbar);
        setSupportActionBar(mapToolBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //new MenuInflater(this).inflate(R.menu.menu_map, menu);

        //return super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.go_back_to_main_menu) {
            Intent goBackToMainMenu = new Intent();
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int randomizeInteger(int minValue, int maxValue) {
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt((maxValue - minValue) + 1) + minValue;

        return randomNumber;
    }

    public void HandleZone(View view) {
        int id = view.getId();

        if (id == R.id.first_zone) {
            Intent goToFirstZone = new Intent(this, FirstZone.class);

            startActivity(goToFirstZone);
        } else if (id == R.id.second_zone) {
            Intent goToSecondZone = new Intent(this, SecondZone.class);

            startActivity(goToSecondZone);
        } else if (id == R.id.third_zone) {
            Intent goToThirdZone = new Intent(this, ThirdZone.class);

            startActivity(goToThirdZone);
        } else if (id == R.id.fourth_zone) {
            Intent goToFourthZone = new Intent(this, FourthZone.class);

            startActivity(goToFourthZone);
        }
    }

    public void ProgressDialogForMission(String message, final int totalProgressTime, final long sleepTime, final int jump) {
        progress = new ProgressDialog(this);
        progress.setMessage(message);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setCancelable(true);
        progress.setProgress(0);
        progress.show();

        final Thread thread = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(sleepTime);
                        jumpTime += jump;
                        progress.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (jumpTime >= totalProgressTime) {
                        progress.dismiss();
                    }
                }
            }
        };
        thread.start();
    }

    public void HandleMission(View view) {
        int id = view.getId();

        int samplePlayerLevel = 1;
        int level = samplePlayerLevel;
        int sleepTime = 200;

        int timeJump;
        if (level <= 5) {
            timeJump = randomizeInteger(level, level*10);
        } else if (level <= 10) {
            timeJump = randomizeInteger(1, level - 5);
        } else if (level <= 15) {
            timeJump = randomizeInteger(1, level - 10);
            sleepTime += level*200;
        } else {
            timeJump = randomizeInteger(1, level - 15);
            sleepTime += level*50;
        }

        if (id == R.id.first_zone_mission_zero) {
            ProgressDialogForMission("Shit is happening", 100, sleepTime, timeJump);

        } else if (id == R.id.second_zone_mission_zero) {
            ProgressDialogForMission("...", 100, sleepTime, timeJump);
        } else if (id == R.id.third_zone_mission_zero) {

        }
    }
}
