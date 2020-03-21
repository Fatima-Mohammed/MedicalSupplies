package com.example.medicalsupplies;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class about extends AppCompatActivity {
    TextView text , text2 , text3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        text = (TextView)findViewById(R.id.we);
        Typeface face = Typeface.createFromAsset(getAssets(),"front2.TTF");
        text.setTypeface(face);

        text2 = (TextView)findViewById(R.id.worker);
        Typeface face2 = Typeface.createFromAsset(getAssets(),"front5.TTF");
        text2.setTypeface(face2);

        text3 = (TextView)findViewById(R.id.supervisor);
        Typeface face3 = Typeface.createFromAsset(getAssets(),"front5.TTF");
        text3.setTypeface(face3);
    }
}
