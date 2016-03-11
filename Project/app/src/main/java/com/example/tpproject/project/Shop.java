package com.example.tpproject.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Tomi on 9.3.2016 г..
 */
public class Shop extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shop_layout);

        String[] shopList = {"Buy", "Sell"};

        ListAdapter shopAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shopList);

        ListView shopListView = (ListView) findViewById(R.id.shopView);
        shopListView.setAdapter(shopAdapter);

        shopListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mainMenuOptionClicked = "You selected " + String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(Shop.this, mainMenuOptionClicked, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
