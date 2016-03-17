package com.example.tpproject.project.shopping;

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
public class Sell extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sell_layout);

        ListAdapter shopAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PlayerScreen.itemsList);

        final ListView sellerListView = (ListView) findViewById(R.id.sellersList);
        sellerListView.setAdapter(shopAdapter);

        sellerListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = String.valueOf(parent.getItemAtPosition(position));
                String mainMenuOptionClicked = "You selected " + itemName;

                Toast.makeText(Sell.this, mainMenuOptionClicked, Toast.LENGTH_SHORT).show();

                PlayerScreen.itemsList.remove(itemName);
                PlayerScreen.money += 10;

                ListAdapter shopAdapter = new ArrayAdapter<String>(Sell.this, android.R.layout.simple_list_item_1, PlayerScreen.itemsList);
                sellerListView.setAdapter(shopAdapter);
            }
        });
    }
}
