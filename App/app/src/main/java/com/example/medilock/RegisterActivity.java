package com.example.medilock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername , edtEmail , edtPassword , edtConfPassword;
    Button btnReg;
    TextView txtReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.editTextLTBFullname);
        edtEmail = findViewById(R.id.editTextLTBAddress);
        edtPassword = findViewById(R.id.editTextLTBPinCode);
        edtConfPassword = findViewById(R.id.editTextLTBContact);
        btnReg = findViewById(R.id.buttonkLTBBooking);
        txtReg = findViewById(R.id.textViewNewUserReg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String confPassword = edtConfPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(username.length() == 0 || email.length() == 0 || password.length() == 0 || confPassword.length() == 0){
                    Toast.makeText(RegisterActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confPassword) == 0){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(RegisterActivity.this, "record inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "password must contain atLeast 8 characters , having digit , letter and special character", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(RegisterActivity.this, "Password and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

    }

    public static boolean isValid(String passwordHere){
        int f1=0 ,f2=0;
        if(passwordHere.length()<8){
            return false;
        }else{
            for(int p =0;p<passwordHere.length(); p++){
                if(Character.isLetter(passwordHere.charAt(p)) || Character.isDigit(passwordHere.charAt(p))){
                    f1=1;
                }
            }


            for (int s = 0 ; s<passwordHere.length() ; s++){
                char c = passwordHere.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    f2=1;
                }
            }

            if(f1==1 && f2==1){
                return true;
            }
            return false;
        }

    }
}