package com.example.tpproject.project.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tpproject.project.PlayerScreen;
import com.example.tpproject.project.R;

/**
 * Created by Tomi on 18.3.2016 г..
 */
public class Buy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.buy_layout);

        Bundle extras = getIntent().getExtras();
        final int[] money = {extras.getInt("money")};

        String[] buyerList =
                    {"Item1 - costs 15 money",
                    "Item2 - costs 20 money",
                    "Item3 - costs 100 money"};

        ListAdapter shopAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, buyerList);

        ListView buyerListView = (ListView) findViewById(R.id.buyersList);
        buyerListView.setAdapter(shopAdapter);

        buyerListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String mainMenuOptionClicked = "You selected " + String.valueOf(parent.getItemAtPosition(position));

            Toast.makeText(Buy.this, mainMenuOptionClicked, Toast.LENGTH_SHORT).show();

            String secondHalf = String.valueOf(parent.getItemAtPosition(position)).split("\\-")[1];
            String itemToAdd = String.valueOf(parent.getItemAtPosition(position)).split("\\ ")[0];
            String moneyCostString = secondHalf.replaceAll("\\D+", "");
            int moneyCost = Integer.parseInt(moneyCostString);

            if (money[0] < moneyCost) {
                //Toast.makeText(Buy.this, "You do not have enough money to buy that item!", Toast.LENGTH_SHORT).show();
                Toast.makeText(Buy.this, moneyCostString, Toast.LENGTH_SHORT).show();
            } else {
                PlayerScreen.itemsList.add(itemToAdd);
                money[0] -= moneyCost;
            }
            }
        });
    }
}
