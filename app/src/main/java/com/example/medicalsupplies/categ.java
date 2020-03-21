package com.example.medicalsupplies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class categ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categ);

    }
    public void doctor (View v)
    {
        Intent intent = new Intent(categ .this , mumaris.class) ;
        startActivity(intent);
    }

    public void patent (View v)
    {
        Intent intent = new Intent(categ .this , patent.class) ;
        startActivity(intent);
    }

    public void donar (View v)
    {
        Intent intent = new Intent(categ .this , donar .class) ;
        startActivity(intent);
    }

    public void beneficiary (View v)
    {
        Intent intent = new Intent(categ .this , beneficiary.class) ;
        startActivity(intent);
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

            Intent intent = new Intent(categ.this , myActions.class) ;
            startActivity(intent);
            Toast.makeText(this , "My procedure" , Toast.LENGTH_SHORT).show(); }

        if(id==R.id.about){
            Intent intent2 = new Intent(categ.this , about.class) ;
            startActivity(intent2);
            Toast.makeText(this , "About us" , Toast.LENGTH_SHORT).show(); }

        if(id==R.id.help){
            Intent intent3 = new Intent(categ.this , poles.class) ;
            startActivity(intent3);
            Toast.makeText(this , "Privacy policy" , Toast.LENGTH_SHORT).show(); }
        return super.onOptionsItemSelected(item);
    }
}
