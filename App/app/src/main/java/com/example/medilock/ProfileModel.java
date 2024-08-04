package com.example.medilock;

public class ProfileModel {

    // Name, email, password, gender, blood type, height, weight, diabetes status, blood pressure, asthma
    // allergies (postponed due to multiple allergy problem)

    // NOTE that blood pressure is high, low or normal, it does not have accurate blood pressure here
    int id;
    String username;
    String email;
    String gender;
    String blood_type;
    String height;
    String weight;
    String diabetes_status;
    String blood_pressure;
    String asthma;

    ProfileModel(){
        id = 0;
        username = "";
        email = ""; // Should we keep it null? Since email column is not null, the SQLite will give error if email is not set somehow
        gender = "";
        blood_type = "";
        height = "";
        weight = "";
        diabetes_status = "";
        blood_pressure = "";
        asthma = "";

    }

    // Copy constructor
    ProfileModel (ProfileModel p){
        username = p.username;
        email = p.email;
        gender = p.gender;
        blood_type = p.blood_type;
        height = p.height;
        weight = p.weight;
        diabetes_status = p.diabetes_status;
        blood_pressure = p.blood_pressure;
        asthma = p.asthma;


    }


}
