package com.example.medicalsupplies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class benefichary_show_donar extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


    ArrayList<String> listItem1 = new ArrayList<String>();
    ArrayAdapter<String> adapte;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String t= MainActivity.email1.toString().trim();
    //HashMap<String,String> hashMap = new HashMap<>();

   // Spinner donorReq;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefichary_show_donar);

        spin = (Spinner) findViewById(R.id.spinner);
        adapte=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listItem1);
        spin.setAdapter(adapte);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    String donor;
    public void select_dev (View v)
    {
        donor = spin.getSelectedItem().toString().trim();
        Intent intent = getIntent();
        String clinic = intent.getStringExtra("devise");
        Response.Listener<String> responseLisener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject (response);
                    boolean success = jsonResponse.getBoolean("sucess");
                    if (success) {
                        Toast.makeText(benefichary_show_donar.this, " Operation accomplished successfully ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(benefichary_show_donar.this, Manage_request_donor.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(benefichary_show_donar.this, " There is a mistake , please confirm the operation", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        benefichary_show_donar.send_data_doctorP send_Datad = new benefichary_show_donar.send_data_doctorP(clinic,donor ,responseLisener);
        RequestQueue queue = Volley.newRequestQueue(benefichary_show_donar.this);
        queue.add(send_Datad);

    }

    protected void onStart()

    { super.onStart();
       // String n = "Surgery";
        benefichary_show_donar.BackTa b = new benefichary_show_donar.BackTa();
        b.execute();
    }
    private class BackTa extends AsyncTask<Void , Void , Void> {



        ArrayList<String> list ;
        protected void onPreExecute(){
            super.onPreExecute();
            list = new ArrayList<>();

        }
        protected Void doInBackground(Void...params) {
            //hashMap.put("name",t);
            InputStream is = null;
            String result = "";

            try {

                HttpClient httpClient = new DefaultHttpClient();

                Intent intent3 = getIntent();

                String c2 = intent3.getStringExtra("devise");
                HttpPost httpPost = new HttpPost("http://172.20.10.3/R/benefichary-show-devises.php?name="+c2);
                HttpResponse response = httpClient.execute(httpPost);

                HttpEntity entity =response.getEntity();
                is=entity.getContent();

            }
            catch (Exception e){

                e.printStackTrace();
            }

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = " ";
                while ((line = bufferedReader.readLine()) != null) {
                    // list.add(t);
                    result += line;

                }
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                JSONArray jsonResponse = new JSONArray(result);
                for(int i =0; i<jsonResponse.length(); i++){
                    JSONObject jsonObject =jsonResponse.getJSONObject(i);
                    list.add(jsonObject.getString("id")+"_"+jsonObject.getString("donor-name")+","+jsonObject.getString("donor-city"));

                }

            } catch (JSONException e){e.printStackTrace();}
            return null;
        }
        protected void onPostExecute(Void result){
            //list.add(t);
            listItem1.addAll(list);
            adapte.notifyDataSetChanged();

        }
    }
    public class send_data_doctorP  extends StringRequest {


        public static final String SEND_DATA_URL = "http://172.20.10.3/R/benefichary-booking-devises.php";

        //String t= MainActivity.email1.toString().trim();
        private Map<String, String> MapData ;

        public send_data_doctorP(String devise,String booking,Response.Listener<String> listener)

        {


            super(Method.POST , SEND_DATA_URL, listener , null);


            MapData = new HashMap<>();
            MapData.put("email", t);
            MapData.put("devise", devise);
            MapData.put("booking", booking);
        }
        @Override
        public Map<String, String> getParams()  {
            return MapData;
        }
    }



}
