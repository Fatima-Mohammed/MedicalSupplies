package com.example.medicalsupplies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class verification_user extends AppCompatActivity {
    EditText SNN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_user);
        SNN = (EditText) findViewById(R.id.SNN);

    }
    public void Verification(View v) {

        final String User_SSN = SNN.getText().toString().trim();

        Response.Listener<String> responseLisener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("sucess");

                    if (success) {

                        Toast.makeText(verification_user.this, " Operation accomplished successfully ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(verification_user.this,Signup.class);
                        intent.putExtra("SNN", User_SSN);
                        startActivity(intent);
                    }

                    else{
                        Toast.makeText(verification_user.this, " This SSN is wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        };
        verification_user.send_SNN send_Data1 = new verification_user.send_SNN(User_SSN,  responseLisener);
        RequestQueue queue = Volley.newRequestQueue(verification_user.this);
        queue.add(send_Data1);
    }


    public class send_SNN extends StringRequest {

        // String t= MainActivity.email1.toString().trim();
        public static final String SEND_DATA_URL = "http://172.20.10.3/R/verification.php";
        private Map<String, String> MapData ;

        public send_SNN(String SNN , Response.Listener<String> listener)

        {
            super(Method.POST , SEND_DATA_URL , listener , null);
            MapData = new HashMap<>();
            MapData.put("SNN", SNN);
            // MapData.put("admain", t);

        }
        @Override
        public Map<String, String> getParams()  {
            return MapData;
        }
    }
}
