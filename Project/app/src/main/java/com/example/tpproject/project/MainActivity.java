package com.example.tpproject.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tpproject.project.activity.LoginActivity;
import com.example.tpproject.project.app.AppConfig;
import com.example.tpproject.project.app.AppController;
import com.example.tpproject.project.helper.SQLiteHandler2;
import com.example.tpproject.project.shopping.Shop;

import com.example.tpproject.project.helper.SQLiteHandler;
import com.example.tpproject.project.helper.SessionManager;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
/*
public class MainActivity extends Activity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

*/    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
/*    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
*/

public class MainActivity extends AppCompatActivity {
    // SQLiteDatabase database = null;

    private Button btnLogout;
    private Button exitButton;
    private Button settingsButton;

    private ProgressDialog pDialog;

    private SQLiteHandler db;
    //private SQLiteHandler2 db2;
    private SessionManager session;

    public static final String SAVE = "ProjectGameSaveFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*SharedPreferences load_game = getSharedPreferences(SAVE, MODE_PRIVATE);
        PlayerScreen.money = load_game.getInt("PlayerMoney",0);
        PlayerScreen.xp = load_game.getInt("PlayerXP",0);
        PlayerScreen.intelligence = load_game.getInt("PlayerInt",10);
        PlayerScreen.power = load_game.getInt("PlayerPower",10);
        PlayerScreen.speed = load_game.getInt("PlayerSpeed",10);
        PlayerScreen.level = load_game.getInt("PlayerLevel",1);*/

        String[] mainMenuList = {"Player", "Map", "Shop", "Achievments", "Rankings"};

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
                } else if (selected == "Rankings") {
                    Intent goToRankingsScreen = new Intent(view.getContext(), Rankings.class);

                    startActivity(goToRankingsScreen);
                }
            }
        });

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
        //db2 = new SQLiteHandler2(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        PlayerScreen.playerName = user.get("name");
        String a = user.get("email");
        String b = user.get("uid");


        HashMap<String, String> player_stats = db.getPlayerStats();
        PlayerScreen.playerName = player_stats.get("name");
        String money = player_stats.get("money");
        String xp = player_stats.get("xp");
        String level = player_stats.get("level");
        PlayerScreen.money = Integer.parseInt(money);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }

        });

        exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolLogoutUser(PlayerScreen.playerName);
                storePlayerData(PlayerScreen.playerName, String.valueOf(PlayerScreen.money),
                        String.valueOf(PlayerScreen.xp), String.valueOf(PlayerScreen.level));
                finish();
            }
        });

        settingsButton = (Button) findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //storePlayerData(PlayerScreen.playerName, "10", "0", "1");
            }
        });
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void logoutUser() {
        boolLogoutUser(PlayerScreen.playerName);
        storePlayerData(PlayerScreen.playerName, String.valueOf(PlayerScreen.money),
                        String.valueOf(PlayerScreen.xp), String.valueOf(PlayerScreen.level));
        session.setLogin(false);

        db.deleteUsers();
        db.deletePlayerStats();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void boolLogoutUser(final String name) {
        String tag_string_req = "req_StoreUserLoggedOut";

        //pDialog.setMessage("Storing data ...");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGOUTBOOL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //hideDialog();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected java.util.Map<String, String> getParams() {
                // Posting params to register url
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void storePlayerData(final String name, final String money, final String xp, final String level) { {
        // Tag used to cancel the request
        String tag_string_req = "req_storePlayerData";

        pDialog.setMessage("Storing data ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_UPDATE, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    hideDialog();

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    //Log.e(TAG, "Registration Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            }) {

                @Override
                protected java.util.Map<String, String> getParams() {
                    // Posting params to register url
                    java.util.Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name);
                    params.put("money", money);
                    params.put("xp", xp);
                    params.put("level", level);

                    return params;
                }

            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }
    }
/*
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
    */
}
