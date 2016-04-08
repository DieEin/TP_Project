package com.example.tpproject.project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent.*;

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

        if (MainActivity.level <= 2) {
            gainedMoney = randomizeInteger(10, 100);
            gainedXP = randomizeInteger(5, 30);

            MainActivity.money += gainedMoney;
            MainActivity.xp += gainedXP;
        } else if (MainActivity.level <= 4) {
            gainedMoney = randomizeInteger(20, 150);
            gainedXP = randomizeInteger(5, 35);

            MainActivity.money += gainedMoney;
            MainActivity.xp += gainedXP;
        } else if (MainActivity.level <= 9) {
            gainedMoney = randomizeInteger(50, 250);
            gainedXP = randomizeInteger(10, 50);

            MainActivity.money += gainedMoney;
            MainActivity.xp += gainedXP;
        }

        Toast.makeText(myContext, "You gained " + gainedXP + " XP and " + gainedMoney + " money!", Toast.LENGTH_SHORT).show();
    }

    public void chooseLevel() {

        int tempLevel = MainActivity.level;

        if (MainActivity.xp >= 30) {

            if (MainActivity.xp >= 100) {

                if (MainActivity.xp >= 250) {

                    if (MainActivity.xp >= 500) {

                        if (MainActivity.xp >= 1000) {
                            MainActivity.level = 6;
                        } else {
                            MainActivity.level = 5;
                        }
                    } else {
                        MainActivity.level = 4;
                    }
                } else {
                    MainActivity.level = 3;
                }
            } else {
                MainActivity.level = 2;
            }
        }

        if (tempLevel != MainActivity.level) {
            Toast.makeText(myContext, "You have leveled up!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mySleepTime = 200;

        if (MainActivity.level <= 5) {
            myJump = randomizeInteger(MainActivity.level, MainActivity.level*10);
        } else if (MainActivity.level <= 10) {
            myJump = randomizeInteger(1, MainActivity.level - 5);
        } else if (MainActivity.level <= 15) {
            myJump = randomizeInteger(1, MainActivity.level - 10);
            mySleepTime += MainActivity.level*200;
        } else {
            myJump = randomizeInteger(1, MainActivity.level - 15);
            mySleepTime += MainActivity.level*50;
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
