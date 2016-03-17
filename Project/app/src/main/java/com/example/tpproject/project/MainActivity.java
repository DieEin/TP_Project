package com.example.tpproject.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tpproject.project.shopping.Shop;

public class MainActivity extends AppCompatActivity {
    // SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] mainMenuList = {"Map", "Shop"};

        ListAdapter mainMenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mainMenuList);

        ListView mainMenuListView = (ListView) findViewById(R.id.mainMenuView);
        mainMenuListView.setAdapter(mainMenuAdapter);

        mainMenuListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mainMenuOptionClicked = "You selected " + String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(MainActivity.this, mainMenuOptionClicked, Toast.LENGTH_SHORT).show();

                if (String.valueOf(parent.getItemAtPosition(position)) == "Map") {
                    Intent goToMapScreen = new Intent(view.getContext(), Map.class);
                    startActivity(goToMapScreen);
                } else if (String.valueOf(parent.getItemAtPosition(position)) == "Shop") {
                    Intent goToShopScreen = new Intent(view.getContext(), Shop.class);

                    startActivity(goToShopScreen);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.main_menu_player) {
            Intent goToPlayerScreen = new Intent(getApplicationContext(), PlayerScreen.class);
            //goToPlayerScreen.putExtra("xp", xp);
            //goToPlayerScreen.putExtra("money", money);

            startActivity(goToPlayerScreen);
        } else if (id == R.id.exit_app) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
