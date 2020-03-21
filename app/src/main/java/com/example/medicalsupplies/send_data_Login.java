package com.example.medicalsupplies;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class send_data_Login extends StringRequest {

    public static final String SEND_DATA_URL = "http://172.20.10.3/R/validate_login.php";
   private Map<String ,String>MapData ;

   public send_data_Login (String Login_email , String Login_password , Response.Listener<String> listener)

   {
super(Method.POST , SEND_DATA_URL , listener , null);
        MapData = new HashMap<>();
        MapData.put("email", Login_email);
       MapData.put("password", Login_password );

   }
    @Override
    public Map<String, String> getParams()  {
        return MapData;
    }
}
