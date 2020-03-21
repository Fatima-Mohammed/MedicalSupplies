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

public class manage_request_patient extends AppCompatActivity {



    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String t= MainActivity.email1.toString().trim();
    //public static String email ;
    HashMap<String,String> hashMap = new HashMap<>();

    HttpPost httpPost = new HttpPost("http://172.20.10.3/R/cancel-appointment-patient.php?name_patient="+t);
    Spinner pateineReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_request_patient);


        //String t= MainActivity.email1.toString().trim();
        //hashMap.put("name",t);

        pateineReq = (Spinner) findViewById(R.id.pp);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listItems);
        pateineReq.setAdapter(adapter);
        //String f = doctorReq.getSelectedItem().toString();

    }
    public void Delet_appontmen_patient (View v)
    {
        String c = pateineReq.getSelectedItem().toString().trim();

        Response.Listener<String> responseLisener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject (response);
                    boolean success = jsonResponse.getBoolean("sucess");
                    if (success) {
                        Toast.makeText(manage_request_patient.this, "Operation accomplished successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(manage_request_patient.this, categ.class);

                        startActivity(intent);

                    } else {
                        Toast.makeText(manage_request_patient.this, "There is a mistake ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        manage_request_patient.send_data_doctorP send_Datad = new manage_request_patient.send_data_doctorP( c ,responseLisener);
        RequestQueue queue = Volley.newRequestQueue(manage_request_patient.this);
        queue.add(send_Datad);
        // Toast.makeText(Manage_request_doctor.this, "تم تسجيلك بنجاح ", Toast.LENGTH_SHORT).show();
        //  Intent intent = new Intent(Manage_request_doctor.this, categ.class);
        //  startActivity(intent);

    }
    public void back_appontmen_patient (View v) {

        Intent intent = new Intent(manage_request_patient.this, categ.class);

        startActivity(intent);

    }


    protected void onStart()

    { super.onStart();

        manage_request_patient.BackTask bt = new manage_request_patient.BackTask();
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
                            " - date :"+jsonObject.getString("date")+"- time :"
                            +jsonObject.getString("date")+" -"
                            +jsonObject.getString("doctor_hospital"));

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



    public class send_data_doctorP  extends StringRequest {


        public static final String SEND_DATA_URL = "http://172.20.10.3/R/delet-appointment-patient.php";

        //String t= MainActivity.email1.toString().trim();
        private Map<String, String> MapData ;

        public send_data_doctorP( String f,Response.Listener<String> listener)

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
