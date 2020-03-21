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

public class Admain extends AppCompatActivity {

    EditText email_User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admain);

        email_User = (EditText) findViewById(R.id.email_user);
    }
    public void AdmainB(View v) {

        String email = email_User.getText().toString().trim();
        if (!email.equals("")) {
            Response.Listener<String> responseLisener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("sucess");
                        if (success) {
                            // Log.v("hhhhhh", String.valueOf(success));
                            Toast.makeText(Admain.this, " The user has been deleted ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Admain.this, Admain.class);
                            startActivity(intent);
                        }

                             else{
                            Toast.makeText(Admain.this, " This user does not exist", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
            };
            send_delet_user send_Data1 = new send_delet_user( email,  responseLisener);
            RequestQueue queue = Volley.newRequestQueue(Admain.this);
            queue.add(send_Data1);}
        else
        {
            Toast.makeText(Admain.this, " User's e-mail not specified ", Toast.LENGTH_SHORT).show();
        }

        }
    public void Show_User(View v) {
                        String email = email_User.getText().toString().trim();
                      if (!email.equals("")) {
                        Intent intent = new Intent(Admain.this, show_user_data.class);
                        intent.putExtra("email", email);
                        startActivity(intent); }
                      else
                      {
                          Toast.makeText(Admain.this, " User's e-mail not specified ", Toast.LENGTH_SHORT).show();
                      }

    }

    public class send_delet_user extends StringRequest {

        String t= MainActivity.email1.toString().trim();
        public static final String SEND_DATA_URL = "http://172.20.10.3/R/Admain-delet-u.php";
        private Map<String, String> MapData ;

        public send_delet_user(String email_U , Response.Listener<String> listener)

        {
            super(Method.POST , SEND_DATA_URL , listener , null);
            MapData = new HashMap<>();
            MapData.put("email_user", email_U);
            MapData.put("admain", t);

        }
        @Override
        public Map<String, String> getParams()  {
            return MapData;
        }
    }



}
