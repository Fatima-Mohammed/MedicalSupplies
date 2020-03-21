package com.example.medicalsupplies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class donar extends AppCompatActivity implements devisesAdapter.Interaction {


    ListView list;
    String t= MainActivity.email1.toString().trim();
    String[] maintitle ={
            "Electric_chair","Electric_chair",
            "Electric_chair","walker",
            "Patient_shower seat", "a_chair",
            "Oxygen_concentrator" ,"Oxygen_concentrator",
            "Respirator","Respirator",
            "Oxygen_regulator","Evaporator_device",
            "Manual_lifting_device","Patient_giving_pump",

    };

    String[] subtitle ={


            "Electric_wheelchair                             "," with_special_specifications              ",
            "for_heavy_weights_patients               ","Treadmill_with_wheels_in_front         ",
            "Patient_shower_seat                          ","Chair_to_support_the_spine               ",
            "High_concentration                            ","Low-concentration                             ",
            "Mechanical_pulmonary_respirator   ","Dual_pressure_breathing_apparatus",
            "Portable_oxygen_regulato                 ","Evaporator_device                              ",
            "Manual_lifting_device                         ","Patient_giving_pump                           ",
    };

    Integer[] imgid = {
            R.drawable.d1, R.drawable.d2,
            R.drawable.d3, R.drawable.d4,
            R.drawable.d5, R.drawable.d6,
            R.drawable.d7, R.drawable.d8,
            R.drawable.d9, R.drawable.d10,
            R.drawable.d11, R.drawable.d12,
            R.drawable.d13, R.drawable.d14,
    };

    /*int [] b = {R.id.buReq};
     * For DB late Time Comlet*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);

        devisesAdapter adapter1 = new devisesAdapter(this, this, maintitle, subtitle, imgid);
        list = (ListView) findViewById(R.id.list1);
        //list.setAdapter(adapter);
        list.setAdapter(adapter1);
        //list.setOnItemClickListener(listClick);



    }
    public void donate(String position ) {


                final Response.Listener<String> responseLisener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response.toString());
                            boolean success = jsonResponse.getBoolean("sucess");
                            if (success) {
                                Toast.makeText(donar.this, " Operation accomplished successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(donar.this, Manage_request_donor.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(donar.this, " There is a mistake ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }


                };
           send_data_donor send_Datad = new donar.send_data_donor(position, responseLisener);
            RequestQueue queue = Volley.newRequestQueue(donar.this);
            queue.add(send_Datad);
    }












    public class send_data_donor  extends StringRequest {


        public static final String SEND_DATA_URL = "http://172.20.10.3/R/donor.php";
        //String r= Signup.email.toString().trim();

        private Map<String, String> MapData ;

        public send_data_donor (String b ,Response.Listener<String> listener)

        {
            super(Method.POST , SEND_DATA_URL , listener , null);

            MapData = new HashMap<>();
            MapData.put("n", t);
            MapData.put("nn", String.valueOf(b));
        }
        @Override
        public Map<String, String> getParams()  {
            return MapData;
        }
    }}




