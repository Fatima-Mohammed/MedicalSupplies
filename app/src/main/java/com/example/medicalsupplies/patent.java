package com.example.medicalsupplies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class patent extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapte;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
//    String t= MainActivity.email1.toString().trim();
    //public static String email ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpPost httpPost1 = new HttpPost("http://172.20.10.3/R/patient-select-specialization.php");
    //HttpPost httpPost = new HttpPost("http://172.20.10.3/R/patient-select-city.php?name="+t);
    Spinner donorReq;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patent);

        spin = (Spinner) findViewById(R.id.spinner);
        adapte=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listItem);
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
    String c;
    public void select_clinic (View v)
    {
         c = spin.getSelectedItem().toString().trim();

        Intent intent = new Intent(patent.this, patiene_select_city.class);
        intent.putExtra("clinic", c);
        startActivity(intent);

    }



    HttpPost httpPost ;
    protected void onStart()

    { super.onStart();
        String n = "Surgery";
      //  c = spin.getSelectedItem().toString().trim();

        patent.BackTas b = new patent.BackTas();
        b.execute();



    }
    private class BackTas extends AsyncTask<Void , Void , Void> {



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
                HttpPost httpPost1 = new HttpPost("http://172.20.10.3/R/patient-select-specialization.php");
                // listItems = new ArrayList<String>(1);
                // listItems.add(String.valueOf(new BasicNameValuePair(" الموعد : ",t)));
                //list.add(t);
                //System.out.println("String Send To PHP file is :::"+t);
                HttpResponse response = httpClient.execute(httpPost1);
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
                    list.add(jsonObject.getString("doctor_clinic"));

                }
                //String r = list.get(9);
                //String m = list.indexOf(jsonObject);
            } catch (JSONException e){e.printStackTrace();}
            return null;
        }
        protected void onPostExecute(Void result){
            //list.add(t);
            listItem.addAll(list);
            adapte.notifyDataSetChanged();
            // System.out.println(adapter);
        }}

        }
