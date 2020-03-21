package com.example.medicalsupplies;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;

public class doctor extends AppCompatActivity implements
        View.OnClickListener , AdapterView.OnItemSelectedListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, hospitalName,txtTime;
    Spinner s;
    String[] clinic = { "Ear,Nose,Throat", "General,and_emergency_medicine"
            , "Surgery", "ophthalmology", "Internal_Medicine","heart_clinic"
            ,"Urology_clinic","Obstetrics,and,Gynecology_Clinic","Dermatology,and,cosmetic_clinics"
            ,"Dental,and_Orthodontics_Clinics","Pediatrics"};
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);


        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.time1);
        hospitalName=(EditText)findViewById(R.id.hospital);
        s = (Spinner) findViewById(R.id.simpleSpinner);


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter arrClinic = new ArrayAdapter(this,android.R.layout.simple_spinner_item,clinic);
        arrClinic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // s = (Spinner) findViewById(R.id.simpleSpinner);
        s.setAdapter(arrClinic);
        s.setOnItemSelectedListener(this);

    }
    public void Create_appoinment (View v)
    {
        String dated = txtDate.getText().toString().trim();
        String timed = txtTime.getText().toString().trim();
        String hospitald = hospitalName.getText().toString().trim();

        // String c = city.toString();
        String st = s.getSelectedItem().toString();
      if (!dated.equals("") && !timed.equals("")&& !hospitald.equals("")&& !st.equals("")){
        Response.Listener<String> responseLisener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                  //  JSONArray jsonResponse = new JSONArray();
                    //JSONObject jsonResponse = new JSONObject(response.toString());
                    //boolean success = jsonResponse.getBoolean("sucess");
                   // boolean success = jsonResponse.getBoolean(1);
                  //  boolean succes = jsonResponse.getBoolean(0);
                  //  boolean success = true;
                    //if (success) {
                        // Log.v("hhhhhh", String.valueOf(success));
                        Toast.makeText(doctor.this, " Operation accomplished successfully ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(doctor.this, categ.class); startActivity(intent);
                  //  }
                //else {
                        //Toast.makeText(doctor.this, "no", Toast.LENGTH_SHORT).show();
                    //}
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        };
        send_data_doctor send_Datad = new send_data_doctor ( dated , timed , hospitald, st , responseLisener);
        RequestQueue queue = Volley.newRequestQueue(doctor.this);
        queue.add(send_Datad);

    }
    else {
        Toast.makeText(doctor.this, " Please complete all fields ", Toast.LENGTH_SHORT).show();

    }}



    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),clinic[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(doctor.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            txtDate.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater F = getMenuInflater();
        F.inflate(R.menu.menu_main , menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.myActions){

                Intent intent = new Intent(doctor.this , myActions.class) ;
                startActivity(intent);
            Toast.makeText(this , "My procedure" , Toast.LENGTH_SHORT).show(); }

        if(id==R.id.about){
            Intent intent2 = new Intent(doctor.this , about.class) ;
            startActivity(intent2);
            Toast.makeText(this , "About us" , Toast.LENGTH_SHORT).show(); }

        if(id==R.id.help){
            Intent intent3 = new Intent(doctor.this , poles.class) ;
            startActivity(intent3);
            Toast.makeText(this , "Privacy policy" , Toast.LENGTH_SHORT).show(); }
        return super.onOptionsItemSelected(item);
    }
}


