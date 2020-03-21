package com.example.medicalsupplies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class myActions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_actions);
    }

    public void Manage_requests_doctor (View v)
    {
        Intent intent = new Intent(myActions.this , Manage_request_doctor.class) ;
        startActivity(intent);
    }
    public void Manage_requests_patient (View v)
    {
        Intent intent = new Intent(myActions.this , manage_request_patient.class) ;
        startActivity(intent);
    }
    public void Manage_requests_donor (View v)
    {
        Intent intent = new Intent(myActions.this , Manage_request_donor.class) ;
        startActivity(intent);
    }

    public void Manage_requests_beneficiry (View v)
    {
        Intent intent = new Intent(myActions.this , Manage_request_beneficiary.class) ;
        startActivity(intent);
    }




}
