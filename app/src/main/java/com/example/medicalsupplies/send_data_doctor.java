package com.example.medicalsupplies;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class send_data_doctor  extends StringRequest {


    public static final String SEND_DATA_URL = "http://172.20.10.3/R/doctor_valid.php";
    //String r= Signup.email.toString().trim();
    String t= MainActivity.email1.toString().trim();
    private Map<String, String> MapData ;

    public send_data_doctor(String data ,String time, String hospital, String clinic, Response.Listener<String> listener)

    {


        super(Method.POST , SEND_DATA_URL , listener , null);

        MapData = new HashMap<>();
        MapData.put("doctor_name", t);
        MapData.put("Time", time);
        MapData.put("doctor_hospital", hospital);
        MapData.put("clinic_doctor", clinic);
        MapData.put("date", data);


    }
    /*
    public static final String SEND_DATA_URL2 = "http://172.20.10.3/R/cancel-appointment-doctor.php";
    public send_data_doctor(Response.Listener<String> listener)

    {


        super(Method.POST , SEND_DATA_URL2 , listener , null);

        MapData = new HashMap<>();
        MapData.put("doctor_name", t);

    }

     */
    @Override
    public Map<String, String> getParams()  {
        return MapData;
    }
}


