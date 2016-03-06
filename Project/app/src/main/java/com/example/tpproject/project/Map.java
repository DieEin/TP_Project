package com.example.tpproject.project;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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

    public void HandleMission(View view) {
        int id = view.getId();

        if (id == R.id.mission_zero) {

            //DialogFragment myFragment = new MissionZeroFragment();
            //myFragment.show(getFragmentManager(), "theDialog");
            progress = new ProgressDialog(this);
            progress.setMessage("Shit is happening");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setProgress(0);
            progress.show();

            final int totalProgressTime = 100;
            final Thread t = new Thread() {
                @Override
                public void run() {
                    int jumpTime = 0;

                    while (jumpTime < totalProgressTime) {
                        try {
                            sleep(200);
                            jumpTime += 5;
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
            t.start();
        }
    }
}
