package com.example.medilock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Package1: Full Body Checkup","","","","999"},
                    {"Package2: Blood Glucose Fasting","","","","299"},
                    {"Package3: COVID-19 Antibody","","","","899"},
                    {"Package4: Thyroid Check","","","","499"},
                    {"Package5: Immunity Check","","","","699"}
            };

    private String [] package_Datails = {
            "Blood glucose fasting\n "+
                    "complete hemogram\n"+
                    "HbA1c\n"+
                    "Iron Studies\n"+
                    "Kidney function test\n"+
                    "Liver Function test",
            "blood Glucose fasting ",
            "COVID 19 Antibody -IgG",
            "Thyroid Profile",
            "Complete HEmogram\n"+
                    "Lipid Profile"

    };

    HashMap<String ,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button  btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);


        btnBack = findViewById(R.id.buttonLTDDBack);
        listView = findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
                finish();
            }
        });

        list = new ArrayList();
        for(int i = 0; i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","total cost : "+ packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,R.layout.multi_lines,
                new String []{"line1","line2","line3","line4","line5"},
                new int []{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",package_Datails[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);
                finish();
            }
        });
    }
}