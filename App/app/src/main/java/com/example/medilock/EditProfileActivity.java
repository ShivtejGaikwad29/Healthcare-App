package com.example.medilock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditProfileActivity extends AppCompatActivity {
    UserModel user = new UserModel();
    ProfileModel profile = new ProfileModel();
    Database db;

    SharedPreferences sharedPreferences;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new Database(getApplicationContext(), "MediLockDB", null, 1);


        sharedPreferences = getSharedPreferences("Current user", MODE_PRIVATE);
        id = sharedPreferences.getInt("id", 1); // CHANGE TO SUITABLE DEFAULT VALUE LATER

        user = db.getUser(id);
        profile = db.getProfile(id);

        TextView ed1 = findViewById(R.id.get_username);
        TextView ed2 = findViewById(R.id.get_email);
        TextView ed3 = findViewById(R.id.get_password);
        TextView ed4 = findViewById(R.id.get_gender);
        TextView ed5 = findViewById(R.id.get_height);
        TextView ed6 = findViewById(R.id.get_weight);
        TextView ed7 = findViewById(R.id.get_blood_type);
        TextView ed8 = findViewById(R.id.get_diabetes_status);
        TextView ed9 = findViewById(R.id.get_blood_pressure);
        TextView ed10 = findViewById(R.id.get_asthma);

        ed1.setText(user.username);
        ed2.setText(user.email);
        ed3.setText(user.password);
        ed4.setText(profile.gender);
        ed5.setText(String.valueOf(profile.height));
        ed6.setText(String.valueOf(profile.weight));
        ed7.setText(profile.blood_type);
        ed8.setText(profile.diabetes_status);
        ed9.setText(profile.blood_pressure);
        ed10.setText(profile.asthma);

        Button btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Edit", "onClick: ");
                user.username = ed1.getText().toString();
                user.email = ed2.getText().toString();
                user.password = ed3.getText().toString();
                profile.gender = ed4.getText().toString();
                profile.height = ed5.getText().toString();
                profile.weight = ed6.getText().toString();;
                profile.blood_type = ed7.getText().toString();
                profile.diabetes_status = ed8.getText().toString();
                profile.blood_pressure = ed9.getText().toString();
                profile.asthma = ed10.getText().toString();
                Log.d("Edit", "onClick: 2");

                db.updateUser(user);
                db.updateProfile(profile);
                db.close();

                startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));


                finish();
            }
        });



    }
    // onCreate ends here

}
