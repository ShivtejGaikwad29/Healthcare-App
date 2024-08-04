package com.example.medilock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        UserModel user = new UserModel();
        ProfileModel profile = new ProfileModel();
        Database db = new Database(getApplicationContext());


        SharedPreferences sharedPreferences = getSharedPreferences("Current user", MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 1); // CHANGE TO SUITABLE DEFAULT VALUE LATER
//        Log.d("Profile check", "onCreate: 1");


        user = db.getUser(id);
        profile = db.getProfile(id);

        TextView tv_username = (TextView) findViewById(R.id.intro_username);
        TextView tv_email = (TextView) findViewById(R.id.intro_email);

        tv_username.setText(user.username);
        tv_email.setText(user.email);

        ((TextView) findViewById(R.id.val_username)).setText(user.username);
        ((TextView) findViewById(R.id.val_email)).setText(user.email);
        ((TextView) findViewById(R.id.val_password)).setText(user.password);
        ((TextView) findViewById(R.id.val_gender)).setText(profile.gender);
        ((TextView) findViewById(R.id.val_height)).setText(profile.height);
        ((TextView) findViewById(R.id.val_weight)).setText(profile.weight);
        ((TextView) findViewById(R.id.val_blood_type)).setText(profile.blood_type);
        ((TextView) findViewById(R.id.val_diabetes_status)).setText(profile.diabetes_status);
        ((TextView) findViewById(R.id.val_blood_pressure)).setText(profile.blood_pressure);
        ((TextView) findViewById(R.id.val_asthma)).setText(profile.asthma);


        Button editprofile = (Button) findViewById(R.id.btn_edit_profile);


        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);


                startActivity(intent);
                finish();


            }
        });





    }
}