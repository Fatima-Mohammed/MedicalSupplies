package com.example.medicalsupplies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText et_email, et_password;
    LinearLayout Llogin;
    public static String email1 ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Llogin = (LinearLayout) findViewById(R.id.Llogin);
        et_email = (EditText) findViewById(R.id.email);
        et_password = (EditText) findViewById(R.id.password);
        //if(pref.getBoolean(Constants.IS_LOGGED_IN,true)){
    }
    public void bSignin(View v) {
        Intent intent = new Intent(MainActivity.this, verification_user.class);
        startActivity(intent);
    }

    public void loginVisable(View v) {

        Llogin.setVisibility(View.VISIBLE);

    }

    public void log(View v) {

         email1 = et_email.getText().toString().trim();
        String pass1 = et_password.getText().toString().trim();

        Response.Listener<String> responseLisener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("sucess");
                    boolean s = jsonResponse.getBoolean("type");
                    // سكسس ثانيه تنقلني ع صفحة الأدمن
                    // boolean admain = jsonResponse.getBoolean("admain");
                    if (success) {
                        if (s) {
                        // Log.v("hhhhhh", String.valueOf(success));
                        Toast.makeText(MainActivity.this, "Welcome , System Administrator ", Toast.LENGTH_SHORT).show();
                        Llogin.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(MainActivity.this, Admain.class);
                        startActivity(intent);
                    }

                        else {

                            Toast.makeText(MainActivity.this, "Welcome ", Toast.LENGTH_SHORT).show();
                            Llogin.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(MainActivity.this, categ.class);
                            startActivity(intent);
                        }
                    }
                    /*
                    else if (success.equals("admain")) {
                        // Log.v("hhhhhh", String.valueOf(success));
                        Toast.makeText(MainActivity.this, "Welcamo Admain ", Toast.LENGTH_SHORT).show();
                        //Llogin.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(MainActivity.this, Admain.class);
                        startActivity(intent);
                    }
*/

                    else {
                        Toast.makeText(MainActivity.this, "not Foun", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        };
        send_data_Login send_Data = new send_data_Login(email1, pass1, responseLisener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(send_Data);
    }

}

