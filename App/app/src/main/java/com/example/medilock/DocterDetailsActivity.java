package com.example.medilock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DocterDetailsActivity extends AppCompatActivity {

    private String [][]  docter_Details1 =
            {
                    {"Docter name : Ajit Sasate","Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9987657854","700"},
                    {"Docter name : Soham Pawar","Hospital Address : Pune", "Exp : 7yrs", "Mobile No : 7689554673","1000"},
                    {"Docter name : Pravin Deshmukh","Hospital Address : Nigdi", "Exp : 8yrs", "Mobile No : 7685940387","900"},
                    {"Docter name : Rajesh Kale","Hospital Address : Katraj", "Exp : 3yrs", "Mobile No : 9878675665","500"},
                    {"Docter name : Amit Sanap","Hospital Address : Chinchwad", "Exp : 4yrs", "Mobile No : 7887968657","600"},

            };

    private String [][]  docter_Details2 =
            {
                    {"Docter name : Amay kale","Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9987657854","700"},
                    {"Docter name : Satish Nale","Hospital Address : Pune", "Exp : 7yrs", "Mobile No : 7689554673","1000"},
                    {"Docter name : Manisha Mule","Hospital Address : Nigdi", "Exp : 8yrs", "Mobile No : 7685940387","900"},
                    {"Docter name : Ravi Kale","Hospital Address : Katraj", "Exp : 3yrs", "Mobile No : 9878675665","500"},
                    {"Docter name : Aman Mane","Hospital Address : Chinchwad", "Exp : 4yrs", "Mobile No : 7887968657","600"},

            };

    private String [][]  docter_Details3 =
            {
                    {"Docter name : Yash Mandlik","Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9987657854","700"},
                    {"Docter name : Sagar Sane","Hospital Address : Pune", "Exp : 7yrs", "Mobile No : 7689554673","1000"},
                    {"Docter name : Saurabh Ghuge","Hospital Address : Nigdi", "Exp : 8yrs", "Mobile No : 7685940387","900"},
                    {"Docter name : Arush Darade","Hospital Address : Katraj", "Exp : 3yrs", "Mobile No : 9878675665","500"},
                    {"Docter name : Mukund Bhise","Hospital Address : Chinchwad", "Exp : 4yrs", "Mobile No : 7887968657","600"},

            };

    private String [][]  docter_Details4 =
            {
                    {"Docter name : Sarthak Ghuge","Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9987657854","700"},
                    {"Docter name : Shivtej Gaikwad","Hospital Address : Pune", "Exp : 7yrs", "Mobile No : 7689554673","1000"},
                    {"Docter name : Avdhoot Gole","Hospital Address : Nigdi", "Exp : 8yrs", "Mobile No : 7685940387","900"},
                    {"Docter name : Aditya Gaikwad","Hospital Address : Katraj", "Exp : 3yrs", "Mobile No : 9878675665","500"},
                    {"Docter name : Tejas pande","Hospital Address : Chinchwad", "Exp : 4yrs", "Mobile No : 7887968657","600"},

            };

    private String [][]  docter_Details5 =
            {
                    {"Docter name : Amol Sasate","Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9987657854","700"},
                    {"Docter name : Pravin Mali","Hospital Address : Pune", "Exp : 7yrs", "Mobile No : 7689554673","1000"},
                    {"Docter name : Prasad Gaikwad","Hospital Address : Nigdi", "Exp : 8yrs", "Mobile No : 7685940387","900"},
                    {"Docter name : Aditya Kale","Hospital Address : Katraj", "Exp : 3yrs", "Mobile No : 9878675665","500"},
                    {"Docter name : Manish pande","Hospital Address : Chinchwad", "Exp : 4yrs", "Mobile No : 7887968657","600"},

            };

    TextView tv;
    Button btnBack;
    ArrayList list;
    SimpleAdapter sa;

    String [][] docter_details = {};
    HashMap<String, String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docter_details);

        tv = findViewById(R.id.textViewDDTitle);
        btnBack = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0){
            docter_details=docter_Details1;
        } else if (title.compareTo("Dietician")==0) {
            docter_details = docter_Details2;
        } else if (title.compareTo("Dentist")==0) {
            docter_details = docter_Details3;
        } else if (title.compareTo("Surgeon")==0) {
            docter_details = docter_Details4;
        } else{
            docter_details = docter_Details5;
        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DocterDetailsActivity.this,FindDocterActivity.class));
                finish();
            }
        });

        list = new ArrayList();
        for(int i = 0;i<docter_details.length;i++){
            item = new HashMap<String ,String>();
            item.put("line1",docter_details[i][0]);
            item.put("line2",docter_details[i][1]);
            item.put("line3",docter_details[i][2]);
            item.put("line4",docter_details[i][3]);
            item.put("line5","Cons Fees : " + docter_details[i][4]+"/-");
            list.add(item);

        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int []{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

//        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
//                Intent it = new Intent(DocterDetailsActivity.this, BookAppointmentActivity.class);
//                it.putExtra("text1",title);
//                it.putExtra("text2",docter_details[i][0]);
//                it.putExtra("text3",docter_details[i][1]);
//                it.putExtra("text4",docter_details[i][3]);
//                it.putExtra("text5",docter_details[i][4]);
//                startActivity(it);
//        finish();
//            }
//        });
    }
}