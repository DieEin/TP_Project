package com.example.tpproject.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpproject.project.zones.FirstZone;
import com.example.tpproject.project.zones.FourthZone;
import com.example.tpproject.project.zones.SecondZone;
import com.example.tpproject.project.zones.ThirdZone;

import java.util.Random;
import java.util.logging.Handler;

/**
 * Created by Tomi on 5.3.2016 г..
 */
public class Map extends AppCompatActivity {
    private ProgressDialog progress;

    private String[] mission_text = {"Fight a Red Dragon", "Fight a Blue Dragon", "Fight a Green Dragon"};
    int[] mission_icons = {R.drawable.house, R.drawable.sample_mission_zero, R.drawable.house};


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

    /*
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
                //PlayerScreen.money += randomizeInteger(10, 100);
                //PlayerScreen.xp += randomizeInteger(5, 30);
                increaseByLevel();
                chooseLevel();
            }
        };
        thread.start();
    }
    */

    public int randomizeInteger(int minValue, int maxValue) {
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt((maxValue - minValue) + 1) + minValue;

        return randomNumber;
    }

    public void missionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Map.this);

        int messageID = randomizeInteger(0, mission_text.length - 1);

        builder.setTitle(R.string.mission_title)
            .setMessage(mission_text[messageID])
            .setIcon(mission_icons[messageID])
            .setPositiveButton(R.string.accept_mission, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProgressTask task = new ProgressTask(Map.this, "Shit is happening");
                    task.execute();
                }
            })
            .setNegativeButton(R.string.decline_mission, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // just cancels
                }
            }).create().show();
    }

    public void HandleMission(View view) {
        int id = view.getId();

        if (id == R.id.first_zone_mission_zero) {
            // ProgressDialogForMission("Shit is happening", 100, sleepTime, timeJump);
            missionDialog();
        } else if (id == R.id.second_zone_mission_zero) {
            // ProgressDialogForMission("...", 100, sleepTime, timeJump);
            missionDialog();
        } else if (id == R.id.third_zone_mission_zero) {
            // ProgressDialogForMission(".........", 100, sleepTime, timeJump);
            missionDialog();
        } else if (id == R.id.fourth_zone_mission_zero) {
            // ProgressDialogForMission(".........", 100, sleepTime, timeJump);
            missionDialog();
        }
    }

    public void jobDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Map.this);

        final int overallTime = randomizeInteger(1000, 30000);
        final int moneyMade = randomizeInteger(5, 150);

        builder.setTitle("Job")
                .setMessage("You can make " + moneyMade + " money for " + overallTime/1000 + " seconds!")
                .setPositiveButton(R.string.accept_mission, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(Map.this).create();

                        alertDialog.setTitle("Job");
                        alertDialog.setMessage("");
                        alertDialog.show();

                        new CountDownTimer(overallTime, 1000) {
                            public void onTick(long millisUntilFinished) {
                                alertDialog.setMessage("00:" + (millisUntilFinished/1000));
                            }

                            public void onFinish() {
                                alertDialog.cancel();

                                PlayerScreen.money += moneyMade;
                                Toast.makeText(Map.this, "You made " + moneyMade + " money!", Toast.LENGTH_SHORT).show();
                            }
                        }.start();
                    }
                })
                .setNegativeButton(R.string.decline_mission, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // just cancels
                    }
                }).create().show();



    }

    public void handleJob(View view) {
        int id = view.getId();

        if (id == R.id.first_zone_job) {


            jobDialog();
        }
    }

}