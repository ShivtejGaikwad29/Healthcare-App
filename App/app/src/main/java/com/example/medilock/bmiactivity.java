package com.example.medilock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bmiactivity extends AppCompatActivity {

    private Button recalculateBmiButton;
    private float heightInCm, weightInKg, calculatedBmi;
    private Intent intent;
    private TextView bmiDisplay, bmiCategory, genderDisplay;
    private ImageView bmiImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        // Initialize UI components
        bmiCategory = findViewById(R.id.bmicategorydispaly);
        bmiImageView = findViewById(R.id.imageview);
        bmiDisplay = findViewById(R.id.bmidisplay);
        genderDisplay = findViewById(R.id.genderdisplay);
        recalculateBmiButton = findViewById(R.id.recalculatebmi);

        // Retrieve data passed from previous activity
        intent = getIntent();
        String heightStr = intent.getStringExtra("height");
        String weightStr = intent.getStringExtra("weight");

        // Ensure proper parsing of height and weight values
        if (heightStr != null && weightStr != null) {
            heightInCm = Float.parseFloat(heightStr);
            weightInKg = Float.parseFloat(weightStr);
        } else {
            // Handle error in case values are not passed
            heightInCm = 0;
            weightInKg = 0;
        }

        // Convert height from cm to meters
        float heightInMeters = heightInCm / 100;

        // Calculate BMI
        calculatedBmi = weightInKg / (heightInMeters * heightInMeters);

        // Set up the recalculate button to navigate back
        recalculateBmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Correct Intent for MainBmiActivity
                startActivity(new Intent(bmiactivity.this, mainbmiactivity.class));
                finish();
            }
        });

        // Display BMI value rounded to 2 decimal places
        bmiDisplay.setText(String.format("%.2f", calculatedBmi));

        // Update BMI category and image based on calculated BMI
        updateBmiCategoryAndImage(calculatedBmi);

        // Display the gender of the user
        genderDisplay.setText(intent.getStringExtra("gender"));
    }

    // Helper method to determine BMI category and set corresponding image
    private void updateBmiCategoryAndImage(float bmi) {
        if (bmi < 16) {
            bmiCategory.setText("Severe Thinness");
            bmiImageView.setImageResource(R.drawable.crosss);
        } else if (bmi >= 16 && bmi < 17) {
            bmiCategory.setText("Moderate Thinness");
            bmiImageView.setImageResource(R.drawable.warning);
        } else if (bmi >= 17 && bmi < 18.5) {
            bmiCategory.setText("Mild Thinness");
            bmiImageView.setImageResource(R.drawable.warning);
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiCategory.setText("Normal");
            bmiImageView.setImageResource(R.drawable.ok);
        } else if (bmi >= 25 && bmi < 30) {
            bmiCategory.setText("Overweight");
            bmiImageView.setImageResource(R.drawable.warning);
        } else if (bmi >= 30 && bmi < 35) {
            bmiCategory.setText("Obese Class I");
            bmiImageView.setImageResource(R.drawable.warning);
        } else {
            bmiCategory.setText("Obese Class II");
            bmiImageView.setImageResource(R.drawable.crosss);
        }
    }
}
