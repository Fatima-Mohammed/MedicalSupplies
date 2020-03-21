package com.example.medicalsupplies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

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

public class patiene_select_city extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    ArrayList<String> listItem1 = new ArrayList<String>();
    ArrayAdapter<String> adapte;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String t= MainActivity.email1.toString().trim();
    //public static String email ;
    HashMap<String,String> hashMap = new HashMap<>();

    //HttpPost httpPost = new HttpPost("http://172.20.10.3/R/patient-select-city.php?name="+t);
    Spinner donorReq;
    Spinner spin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patiene_select_city);

        spin = (Spinner) findViewById(R.id.spinner);
        adapte=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listItem1);
        spin.setAdapter(adapte);
        // c = spin.getSelectedItem().toString().trim();




    }







    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        // Toast.makeText(getApplicationContext(),clinic[position] , Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),doctor[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    String city;
    public void select_city (View v)
    {
        city = spin.getSelectedItem().toString().trim();

       Intent intent2 = new Intent(patiene_select_city.this, patiene_select_doctor.class);
        Intent intent = getIntent();
        //String m = intent.getStringExtra("message");
        //intent.putExtra("message", m);
        String c = intent.getStringExtra("clinic");
        intent2.putExtra("clinic", c);
        intent2.putExtra("city", city);
        startActivity(intent2);

    }



    //HttpPost httpPost ;
    protected void onStart()

    { super.onStart();
        String n = "Surgery";
        //  c = spin.getSelectedItem().toString().trim();

        patiene_select_city.BackTa b = new patiene_select_city.BackTa();
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
                Intent intent = getIntent();
                String c = intent.getStringExtra("clinic");
                HttpPost httpPost = new HttpPost("http://172.20.10.3/R/patient-select-city.php?name="+c);
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
                    list.add(jsonObject.getString("city"));
                }
            } catch (JSONException e){e.printStackTrace();}
            return null;
        }
        protected void onPostExecute(Void result){
            //list.add(t);
            listItem1.addAll(list);
            adapte.notifyDataSetChanged();
            // System.out.println(adapter);
        }}
}
