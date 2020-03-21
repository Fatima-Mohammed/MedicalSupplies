package com.example.medicalsupplies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //to spiner implements AdapterView.OnItemSelectedListener
    EditText s_name, s_phone, s_email, pass, pass2;
    Spinner city;
   public static String email ;

    String[] Citys = {"Makkah", "Abha", "jeddah"
            , "Almadena", "Dmama", "Algasem"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // to spiner



        s_name = (EditText) findViewById(R.id.name);
        s_phone = (EditText) findViewById(R.id.phone);
        s_email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        pass2 = (EditText) findViewById(R.id.password2);
        city = (Spinner) findViewById(R.id.myCity);

        //Spinner spin = (Spinner) findViewById(R.id.myCity);
        //spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter arrClinic = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Citys);
        arrClinic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        city.setAdapter(arrClinic);
        city.setOnItemSelectedListener(this);
    }


    public void register(View v) {

        String name = s_name.getText().toString().trim();
        String phone = s_phone.getText().toString().trim();
        email = s_email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String password2 = pass2.getText().toString().trim();
       // String c = city.toString();
        String c = city.getSelectedItem().toString();
        if (!password.equals(password2)) {
            Toast.makeText(Signup.this, " defrint password ", Toast.LENGTH_SHORT).show();
        } else {


            Response.Listener<String> responseLisener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("sucess");
                        if (success) {
                            // Log.v("hhhhhh", String.valueOf(success));
                            Toast.makeText(Signup.this, "Operation accomplished successfully", Toast.LENGTH_SHORT).show();
                            //Llogin.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(Signup.this, MainActivity.class);

                           // intent.putExtra(eemail , email);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Signup.this, "There is a mistake ", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
            };
            Intent intent2 = getIntent();
            //String m = intent.getStringExtra("message");
            //intent.putExtra("message", m);
            String SNN = intent2.getStringExtra("SNN");
            send_data_registerion send_Data1 = new send_data_registerion(SNN,name, phone, email, password, c, responseLisener);
            RequestQueue queue = Volley.newRequestQueue(Signup.this);
            queue.add(send_Data1);
        }
    }


// to spiner

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),Citys[position] , Toast.LENGTH_LONG).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub


}}