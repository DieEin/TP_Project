package com.example.tpproject.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tpproject.project.app.AppConfig;
import com.example.tpproject.project.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tomi on 14.5.2016 г..
 */
public class Rankings extends AppCompatActivity {

    //private String[] all_IsLogged = new String[4];//{"1", "0", "0", "0"};
    public static int logged = 0;

    public List<Integer> imageId;
    public List<String> rankingsList;
    private ProgressDialog pDialog;
    TextView text;
    ImageView theImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rankings_layout);

        rankingsList = new ArrayList<String>();
        imageId = new ArrayList<Integer>();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        storePlayerData();

        ArrayAdapter<String> rankingsAdapter = new MyAdapter(this, rankingsList, imageId);

        ListView rankingsListView = (ListView) findViewById(R.id.rankings_listView);
        rankingsListView.setAdapter(rankingsAdapter);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void storePlayerData() { {
        // Tag used to cancel the request
        String tag_string_req = "req_getAllUsers";

        pDialog.setMessage("Getting users ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GETUSERS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session

                        // Now store the user in SQLite
                        JSONObject user_logged_in = jObj.getJSONObject("user_logged_in");
                        String names = user_logged_in.getString("names");
                        names = names.replaceAll("\\[", "");
                        names = names.replaceAll("]", "");
                        names = names.replaceAll("\"", "");
                        String[] allNames = names.split(",");

                        String is_logged = user_logged_in.getString("is_logged");
                        is_logged = is_logged.replaceAll("\\[", "");
                        is_logged = is_logged.replaceAll("]", "");
                        is_logged = is_logged.replaceAll("\"", "");
                        String[] all_IsLogged = is_logged.split(",");

                        for(int i = 0; i < all_IsLogged.length; i++) {
                            if (!rankingsList.contains(allNames[i])) {
                                rankingsList.add(allNames[i]);
                            }

                            if (all_IsLogged[i].equals("1")) {
                                imageId.add(R.drawable.logged_in);
                            } else {
                                imageId.add(R.drawable.logged_out);
                            }
                        }

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
    }

    public void ReloadRankings(View view) {
    }
}
