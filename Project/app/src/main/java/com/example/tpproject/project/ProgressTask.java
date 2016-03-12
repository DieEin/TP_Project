package com.example.tpproject.project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Tomi on 11.3.2016 г..
 */
public class ProgressTask extends AsyncTask<Void, Void, String> {
    private Context myContext;
    ProgressDialog progress;

    String myMessage;
    final int totalProgressTime = 100;
    long mySleepTime;
    int myJump;

    public ProgressTask(Context context, String message) {
        super();

        myContext = context;
        progress = new ProgressDialog(myContext);
        myMessage = message;
    }

    public int randomizeInteger(int minValue, int maxValue) {
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt((maxValue - minValue) + 1) + minValue;

        return randomNumber;
    }

    public void increaseByLevel() {
        int gainedMoney = 0;
        int gainedXP = 0;

        if (PlayerScreen.level <= 2) {
            gainedMoney = randomizeInteger(10, 100);
            gainedXP = randomizeInteger(5, 30);

            PlayerScreen.money += gainedMoney;
            PlayerScreen.xp += gainedXP;
        } else if (PlayerScreen.level <= 4) {
            gainedMoney = randomizeInteger(20, 150);
            gainedXP = randomizeInteger(5, 35);

            PlayerScreen.money += gainedMoney;
            PlayerScreen.xp += gainedXP;
        } else if (PlayerScreen.level <= 9) {
            gainedMoney = randomizeInteger(50, 250);
            gainedXP = randomizeInteger(10, 50);

            PlayerScreen.money += gainedMoney;
            PlayerScreen.xp += gainedXP;
        }

        Toast.makeText(myContext, "You gained " + gainedXP + " XP and " + gainedMoney + " money!", Toast.LENGTH_SHORT).show();
    }

    public void chooseLevel() {

        int tempLevel = PlayerScreen.level;

        if (PlayerScreen.xp >= 30) {

            if (PlayerScreen.xp >= 100) {

                if (PlayerScreen.xp >= 250) {

                    if (PlayerScreen.xp >= 500) {

                        if (PlayerScreen.xp >= 1000) {
                            PlayerScreen.level = 6;
                        } else {
                            PlayerScreen.level = 5;
                        }
                    } else {
                        PlayerScreen.level = 4;
                    }
                } else {
                    PlayerScreen.level = 3;
                }
            } else {
                PlayerScreen.level = 2;
            }
        }

        if (tempLevel != PlayerScreen.level) {
            Toast.makeText(myContext, "You have leveled up!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mySleepTime = 200;

        if (PlayerScreen.level <= 5) {
            myJump = randomizeInteger(PlayerScreen.level, PlayerScreen.level*10);
        } else if (PlayerScreen.level <= 10) {
            myJump = randomizeInteger(1, PlayerScreen.level - 5);
        } else if (PlayerScreen.level <= 15) {
            myJump = randomizeInteger(1, PlayerScreen.level - 10);
            mySleepTime += PlayerScreen.level*200;
        } else {
            myJump = randomizeInteger(1, PlayerScreen.level - 15);
            mySleepTime += PlayerScreen.level*50;
        }

        progress.setMessage(myMessage);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setCancelable(true);
        progress.setProgress(0);
        progress.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        int jumpTime = 0;

        while (jumpTime < totalProgressTime) {
            try {
                Thread.sleep(mySleepTime);
                jumpTime += myJump;
                progress.setProgress(jumpTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        increaseByLevel();
        chooseLevel();

        progress.dismiss();
    }
}
