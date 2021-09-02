package com.example.covitracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //url for json api
    private static String JSON_URL = "https://data.covid19india.org/state_district_wise.json";

    List<Model> list;
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        extract();
    }

    //method to extract data from json url by json parsing.
    private void extract() {
        RequestQueue queue = Volley.newRequestQueue(this);
        //making the jsonObject
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                try {
//                    Model model = new Model();
//                    model.setState(response.getString("Andaman and Nicobar Islands"));
//                    list.add(model);
//                    Model model2 = new Model();
//                    model2.setState(response.keys().next());
//                    list.add(model2);
//                    Log.d("myapp", "The response is" + response.getString("Andaman and Nicobar Islands"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                int tot = 0, tot_confirmed = 0, tot_deceased = 0, tot_recovered = 0;
                Iterator<String> keys = response.keys(); //making iterator of keys to iterate through the json
                String key = keys.next();
                while(keys.hasNext()){
                    key = keys.next();
                    try {
                        JSONObject districtData = response.getJSONObject(key);  //this is the object stored in state name
                        JSONObject districtName = districtData.getJSONObject("districtData"); // this is the object stored in districtData
                        Iterator<String> subkeys = districtName.keys(); //this iterator is of the keys(names of district) in districtData

                        //iterating all the districts to calculate total cases according to state
                        while(subkeys.hasNext()){
                            String subkey = subkeys.next();
                            JSONObject obj3 = districtName.getJSONObject(subkey);
                            tot += obj3.getInt("active");
                            tot_confirmed += obj3.getInt("confirmed");
                            tot_deceased += obj3.getInt("deceased");
                            tot_recovered += obj3.getInt("recovered");
                        }

                        //making object of model class to store the calculated data according to state and adding the model object to the list
                        Model model = new Model();
                        model.setState(key);
                        Log.d("myapp", "The response is" + tot);
                        model.setTotalActive(tot+"");
                        model.setTotalConfirmed(tot_confirmed+"");
                        model.setTotalDeceased(tot_deceased+"");
                        model.setTotalRecovered(tot_recovered+"");
                        list.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                //configuring the recycler view to take the list and setting the adapter
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),list);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);

    }
}