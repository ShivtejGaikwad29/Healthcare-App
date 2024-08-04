package com.example.medilock;

public class UserModel {
    int id;
    String username;
    String email;
    String password;

    UserModel(){
        id = 0;
        username = "";
        email = "";
        password = "";
    }

    UserModel(UserModel u){
        username = u.username;
        email = u.email;
        password = u.password;

    }


}
