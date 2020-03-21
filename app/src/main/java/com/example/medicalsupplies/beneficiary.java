package com.example.medicalsupplies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class beneficiary extends AppCompatActivity  implements MyListAdapter.Interaction2 {

    ListView list;

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

    Integer[] imgid={
            R.drawable.d1,R.drawable.d2,
            R.drawable.d3,R.drawable.d4,
            R.drawable.d5,R.drawable.d6,
            R.drawable.d7,R.drawable.d8,
            R.drawable.d9,R.drawable.d10,
            R.drawable.d11,R.drawable.d12,
            R.drawable.d13,R.drawable.d14,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary);

        MyListAdapter adapter =new MyListAdapter(this, this, maintitle, subtitle, imgid);
        list=(ListView)findViewById(R.id.list);
        //list.setAdapter(adapter);
        list.setAdapter(adapter);

    }
    public void Req(String Position) {

       Toast.makeText(beneficiary.this, " Operation accomplished successfully ", Toast.LENGTH_SHORT).show();
       Intent intent = new Intent(beneficiary.this, benefichary_show_donar.class);
        intent.putExtra("devise", Position);
       startActivity(intent);

    }
}