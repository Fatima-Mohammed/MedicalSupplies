package com.example.medicalsupplies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

public class patiene_select_doctor extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {




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
        setContentView(R.layout.activity_patiene_select_doctor);

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
     String d;
    public void select_doctor  (View v)
    {
         d = spin.getSelectedItem().toString().trim();




        Intent intent3 = new Intent(patiene_select_doctor.this, patient_select_tima.class);
        Intent intent2 = getIntent();

        String c2 = intent2.getStringExtra("clinic");
        String city2 = intent2.getStringExtra("city");
        intent3.putExtra("clinic", c2);
        intent3.putExtra("city", city2);
        intent3.putExtra("doctor", d);
        startActivity(intent3);

    }



    //HttpPost httpPost ;
    protected void onStart()

    { super.onStart();
        String n = "Surgery";
        //  c = spin.getSelectedItem().toString().trim();

        patiene_select_doctor.BackTa b = new patiene_select_doctor.BackTa();
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

                //HttpPost httpPost1 = new HttpPost("http://172.20.10.3/R/patient-select-specialization.php?name="+t);
                // listItems = new ArrayList<String>(1);
                // listItems.add(String.valueOf(new BasicNameValuePair(" الموعد : ",t)));
                //list.add(t);
                //System.out.println("String Send To PHP file is :::"+t);
                Intent intent = getIntent();

                String c2 = intent.getStringExtra("clinic");
                String city2 = intent.getStringExtra("city");

                //intent.putExtra("message", m);
                HttpPost httpPost = new HttpPost("http://172.20.10.3/R/patient-select-doctor.php?name="+c2+"&name2="+city2);
                HttpResponse response = httpClient.execute(httpPost);
                //String t= MainActivity.email1.toString().trim();
                // hashMap.put("name",t);
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

                    //list.add(jsonObject.getString("date","time"));
                    list.add(jsonObject.getString("id")+"_hospital_"+jsonObject.getString("doctor_hospital")+"_dr_"+jsonObject.getString("name-doctor"));

                }
                //String r = list.get(9);
                //String m = list.indexOf(jsonObject);
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
