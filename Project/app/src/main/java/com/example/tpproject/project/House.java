package com.example.tpproject.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Tomi on 14.3.2016 г..
 */
public class House extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.house_layout);

        TextView energyLevel = (TextView) findViewById(R.id.energyLevel);
        energyLevel.setText("Your energy is " + PlayerScreen.energy + "/100");
    }

    public void regainEnergy(View view) {
        PlayerScreen.energy += (100 - PlayerScreen.energy);
    }

    public void train(View view) {
        int id = view.getId();

        if (id == R.id.trainInGym) {

        } else if(id == R.id.trainOnTreadmill) {

        }
    }

    public void learn(View view) {

    }
}
