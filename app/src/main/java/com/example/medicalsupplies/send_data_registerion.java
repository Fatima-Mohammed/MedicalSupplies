package com.example.medicalsupplies;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class send_data_registerion extends StringRequest {

    public static final String SEND_DATA_URL = "http://172.20.10.3/R/validate_regiter.php";
    private Map<String, String> MapData ;

    public send_data_registerion(String SSN,String r_name,String r_phone, String r_email, String r_password, String r_city, Response.Listener<String> listener)

    {
        super(Method.POST , SEND_DATA_URL , listener , null);
        MapData = new HashMap<>();
        MapData.put("SNN", SSN);
        MapData.put("Name", r_name);
        MapData.put("Phone", r_phone);
        MapData.put("email", r_email);
        MapData.put("password", r_password);
        MapData.put("city", r_city);

    }
    @Override
    public Map<String, String> getParams()  {
        return MapData;
    }
}


