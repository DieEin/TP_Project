package com.example.tpproject.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
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
import java.util.List;

/**
 * Created by Tomi on 14.5.2016 г..
 */
public class Rankings extends AppCompatActivity {

    public static List<String> rankingsList = new ArrayList<String>();
    private ProgressDialog pDialog;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rankings_layout);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        text = (TextView) findViewById(R.id.testTextView);

        storePlayerData();
        /*
        ArrayAdapter<String> rankingsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rankingsList);

        ListView rankingsListView = (ListView) findViewById(R.id.rankings_listView);
        rankingsListView.setAdapter(rankingsAdapter);*/
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

                        String result = new String();
                        for(int i = 0; i < all_IsLogged.length; i++) {
                            result += allNames[i] + " " + all_IsLogged[i];
                            result += '\n';
                        }
                        text.setText(result);

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
}
