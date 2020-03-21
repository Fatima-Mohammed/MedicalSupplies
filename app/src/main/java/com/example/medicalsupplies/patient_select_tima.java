package com.example.medicalsupplies;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class patient_select_tima extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {




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
        setContentView(R.layout.activity_patient_select_tima);

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
     String time;
    public void select_time (View v)
    {
          time = spin.getSelectedItem().toString().trim();




       // Intent intent2 = new Intent(patient_select_tima.this, categ.class);
        //intent.putExtra("message", c);


        Intent intent = getIntent();
        //String m = intent.getStringExtra("message");
        //intent.putExtra("message", m);
        String clinic = intent.getStringExtra("clinic");
        String city = intent.getStringExtra("city");
        String doctor = intent.getStringExtra("doctor");
        //intent2.putExtra("clinic", c);
        //intent2.putExtra("city", c2);
        //intent2.putExtra("time", time);
        //startActivity(intent2);

       // String c = doctorReq.getSelectedItem().toString().trim();
        //String c =doctorReq.getOnItemSelectedListener().toString().trim();
        // String c = doctorReq.getTransitionName().toString().trim();
        Response.Listener<String> responseLisener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject (response);
                    boolean success = jsonResponse.getBoolean("sucess");
                    if (success) {
                        Toast.makeText(patient_select_tima.this, "تمت العملية بنجاح ", Toast.LENGTH_SHORT).show();
                        //Llogin.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(patient_select_tima.this, manage_request_patient.class);
                        // intent.putExtra(eemail , email);
                        startActivity(intent);

                    } else {
                        Toast.makeText(patient_select_tima.this, " هناك خطأ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        patient_select_tima.send_data_doctorP send_Datad = new patient_select_tima.send_data_doctorP( clinic ,city,doctor,time,responseLisener);
        RequestQueue queue = Volley.newRequestQueue(patient_select_tima.this);
        queue.add(send_Datad);




    }



    //HttpPost httpPost ;
    protected void onStart()

    { super.onStart();
        String n = "Surgery";
        //  c = spin.getSelectedItem().toString().trim();

        patient_select_tima.BackTa b = new patient_select_tima.BackTa();
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
                Intent intent3 = getIntent();

                String c2 = intent3.getStringExtra("clinic");
                String city2 = intent3.getStringExtra("city");
                String doctor = intent3.getStringExtra("doctor");
                //intent.putExtra("message", m);
                HttpPost httpPost = new HttpPost("http://172.20.10.3/R/patient-select-time.php?name="+c2+
                        "&name2="+city2+"&name3="+doctor);
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
                    list.add(jsonObject.getString("date")+"_"+jsonObject.getString("time"));

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
        }
}

    public class send_data_doctorP  extends StringRequest {


        public static final String SEND_DATA_URL = "http://172.20.10.3/R/patent-create-appointment.php";

        //String t= MainActivity.email1.toString().trim();
        private Map<String, String> MapData ;

        public send_data_doctorP( String clinic,String city,String doctor,String time,Response.Listener<String> listener)

        {


            super(Method.POST , SEND_DATA_URL, listener , null);


            MapData = new HashMap<>();
            MapData.put("email", t);
            MapData.put("clinic", clinic);
            MapData.put("city", city);
            MapData.put("doctor", doctor);
            MapData.put("time", time);

        }
        @Override
        public Map<String, String> getParams()  {
            return MapData;
        }
    }


}
