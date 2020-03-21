package com.example.medicalsupplies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

public class Manage_request_beneficiary extends AppCompatActivity {




            ArrayList<String> listItems = new ArrayList<String>();
            ArrayAdapter<String> adapter;
            String t= MainActivity.email1.toString().trim();
            //public static String email ;
            HashMap<String,String> hashMap = new HashMap<>();

            HttpPost httpPost = new HttpPost("http://172.20.10.3/R/cancel-devises-benefichary.php?name="+t);
            Spinner donorReq;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_manage_request_beneficiary);

                donorReq = (Spinner) findViewById(R.id.B);
                adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listItems);
                donorReq.setAdapter(adapter);


            }
            public void Delet_appontmen_beneficiary (View v)
            {
                String c = donorReq.getSelectedItem().toString().trim();
                Response.Listener<String> responseLisener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject (response);
                            boolean success = jsonResponse.getBoolean("sucess");
                            if (success) {
                                Toast.makeText(Manage_request_beneficiary.this, "Operation accomplished successfully ", Toast.LENGTH_SHORT).show();
                                //Llogin.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(Manage_request_beneficiary.this, categ.class);
                                // intent.putExtra(eemail , email);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Manage_request_beneficiary.this, " There is a mistake ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Manage_request_beneficiary.send_data_doctorB send_Datad = new Manage_request_beneficiary.send_data_doctorB( c ,responseLisener);
                RequestQueue queue = Volley.newRequestQueue(Manage_request_beneficiary.this);
                queue.add(send_Datad);


            }

    public void back_appontmen_beneficiary (View v) {

        Intent intent = new Intent(Manage_request_beneficiary.this, categ.class);

        startActivity(intent);

    }
            protected void onStart()

            { super.onStart();

                Manage_request_beneficiary.BackTask bt = new Manage_request_beneficiary.BackTask();
                bt.execute();

            }
            private class BackTask extends AsyncTask<Void , Void , Void> {



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
                        // listItems = new ArrayList<String>(1);
                        // listItems.add(String.valueOf(new BasicNameValuePair(" الموعد : ",t)));
                        //list.add(t);
                        //System.out.println("String Send To PHP file is :::"+t);
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
                            list.add("id :"+jsonObject.getString("id")+
                                    " - "+jsonObject.getString("devise-name")+
                                    " - "+jsonObject.getString("donor-name"));
                            //Object r =jsonObject.get("id");

                        }
                        //String r = list.get(9);
                        //String m = list.indexOf(jsonObject);
                    } catch (JSONException e){e.printStackTrace();}
                    return null;
                }
                protected void onPostExecute(Void result){
                    //list.add(t);
                    listItems.addAll(list);
                    adapter.notifyDataSetChanged();
                    // System.out.println(adapter);
                }
            }



            public class send_data_doctorB  extends StringRequest {


                public static final String SEND_DATA_URL = "http://172.20.10.3/R/delet-devises-benefichary.php";

                //String t= MainActivity.email1.toString().trim();
                private Map<String, String> MapData ;

                public send_data_doctorB( String f,Response.Listener<String> listener)

                {


                    super(Method.POST , SEND_DATA_URL, listener , null);


                    MapData = new HashMap<>();
                    MapData.put("email", t);
                    MapData.put("val", f);

                }
                @Override
                public Map<String, String> getParams()  {
                    return MapData;
                }
            }






        }

