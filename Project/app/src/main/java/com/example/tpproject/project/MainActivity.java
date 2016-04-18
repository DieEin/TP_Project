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
import android.content.SharedPreferences;

import com.example.tpproject.project.shopping.Shop;


public class MainActivity extends AppCompatActivity {
    // SQLiteDatabase database = null;

    public static final String SAVE = "ProjectGameSaveFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences load_game = getSharedPreferences(SAVE, MODE_PRIVATE);
        PlayerScreen.money = load_game.getInt("PlayerMoney",0);
        PlayerScreen.xp = load_game.getInt("PlayerXP",0);
        PlayerScreen.intelligence = load_game.getInt("PlayerInt",10);
        PlayerScreen.power = load_game.getInt("PlayerPower",10);
        PlayerScreen.speed = load_game.getInt("PlayerSpeed",10);
        PlayerScreen.level = load_game.getInt("PlayerLevel",1);

        String[] mainMenuList = {"Player", "Map", "Shop", "Achievments"};

        ListAdapter mainMenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mainMenuList);

        ListView mainMenuListView = (ListView) findViewById(R.id.mainMenuView);
        mainMenuListView.setAdapter(mainMenuAdapter);

        mainMenuListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = String.valueOf(parent.getItemAtPosition(position));
                String mainMenuOptionClicked = "You selected " + selected;

                Toast.makeText(MainActivity.this, mainMenuOptionClicked, Toast.LENGTH_SHORT).show();

                if (selected == "Player") {
                    Intent goToPlayerScreen = new Intent(getApplicationContext(), PlayerScreen.class);
                    startActivity(goToPlayerScreen);
                } else if (selected == "Map") {
                    Intent goToMapScreen = new Intent(view.getContext(), Map.class);
                    startActivity(goToMapScreen);
                } else if (selected == "Shop") {
                    Intent goToShopScreen = new Intent(view.getContext(), Shop.class);

                    startActivity(goToShopScreen);
                } else if (selected == "Achievments") {
                    Intent goToAchievmentsScreen = new Intent(view.getContext(), Achievments.class);

                    startActivity(goToAchievmentsScreen);
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

        switch (id){
            case R.id.action_settings:
                return true;

            case R.id.exit_app:
                finish();

                return true;

            case R.id.save:
                SharedPreferences save_game = getSharedPreferences(SAVE, MODE_PRIVATE);
                SharedPreferences.Editor editor = save_game.edit();
                editor.putInt("PlayerMoney", PlayerScreen.money)
                      .putInt("PlayerXP", PlayerScreen.xp)
                      .putInt("PlayerInt", PlayerScreen.intelligence)
                      .putInt("PlayerPower", PlayerScreen.power)
                      .putInt("PlayerSpeed", PlayerScreen.speed)
                      .putInt("PlayerLevel", PlayerScreen.level);
                editor.apply();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
