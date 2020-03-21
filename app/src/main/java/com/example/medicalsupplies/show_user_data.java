package com.example.medicalsupplies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
public class show_user_data extends AppCompatActivity {

    RequestQueue rq;
    TextView sNN , name1 , e_mail , phone , city;
    String SNN , Name , Email , Phone , City;
    String r1 ="k";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_data);
        rq = Volley.newRequestQueue(this);
        sNN = (TextView)findViewById(R.id.ssn1);
        name1 = (TextView)findViewById(R.id.name1);
        e_mail = (TextView)findViewById(R.id.email1);
        phone = (TextView)findViewById(R.id.phone1);
        city = (TextView)findViewById(R.id.city1);
        sendjsonerequest();
    }
    public void sendjsonerequest() {

        Intent intent = getIntent();

        String email = intent.getStringExtra("email");

        String url= "http://172.20.10.3/R/show_user_data.php?email="+email;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            SNN = response.getString("SNN");
                            Name = response.getString("name");
                            Email = response.getString("email");
                            Phone = response.getString("mobile");
                            City = response.getString("city");

                            sNN.setText(SNN);
                            name1.setText(Name);
                            e_mail.setText(Email);
                            phone.setText(Phone);
                            city.setText(City);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                }}, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });rq.add(jsonObjectRequest);

        }
    public void back(View v) {
        Intent intent = new Intent(show_user_data.this, Admain.class);
        startActivity(intent);

    }
    }


