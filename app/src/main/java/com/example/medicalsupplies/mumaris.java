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

public class mumaris extends AppCompatActivity {
    EditText mumaris_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mumaris);
        mumaris_id = (EditText) findViewById(R.id.email_user);
    }
    public void mumaris(View v) {

        String id = mumaris_id.getText().toString().trim();

        Response.Listener<String> responseLisener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("sucess");

                    if (success) {

                        Toast.makeText(mumaris.this, " Operation accomplished successfully ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mumaris.this,doctor.class);
                        startActivity(intent);
                    }

                    else{
                        Toast.makeText(mumaris.this, " This user does not exist", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        };
        mumaris.send_ID_Mumaris send_Data1 = new mumaris.send_ID_Mumaris( id,  responseLisener);
        RequestQueue queue = Volley.newRequestQueue(mumaris.this);
        queue.add(send_Data1);
    }


    public class send_ID_Mumaris extends StringRequest {

       // String t= MainActivity.email1.toString().trim();
        public static final String SEND_DATA_URL = "http://172.20.10.3/R/mumaris.php";
        private Map<String, String> MapData ;

        public send_ID_Mumaris(String id , Response.Listener<String> listener)

        {
            super(Method.POST , SEND_DATA_URL , listener , null);
            MapData = new HashMap<>();
            MapData.put("id", id);
           // MapData.put("admain", t);

        }
        @Override
        public Map<String, String> getParams()  {
            return MapData;
        }
    }
}
