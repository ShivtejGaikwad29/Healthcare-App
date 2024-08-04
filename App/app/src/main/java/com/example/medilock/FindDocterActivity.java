package com.example.medilock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDocterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_docter);

        CardView back = findViewById(R.id.cardFDBack);
        CardView familyPhysician = findViewById(R.id.cardFDFamilyPhysician);
        CardView dentist = findViewById(R.id.cardFDDentist);
        CardView cardiologists = findViewById(R.id.cardFDCardiologists);
        CardView dietician = findViewById(R.id.cardFDDietician);
        CardView surgeon = findViewById(R.id.cardFDSurgeon);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDocterActivity.this, HomeActivity.class));
                finish();
            }
        });


        familyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                it.putExtra("title","Family Physician");
                startActivity(it);
                finish();
            }
        });


        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
                finish();
            }
        });

        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                it.putExtra("title","Cardiologists");
                startActivity(it);
                finish();

            }
        });

        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                it.putExtra("title","Dietician");
                startActivity(it);
                finish();

            }
        });

        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
                finish();

            }
        });
    }
}