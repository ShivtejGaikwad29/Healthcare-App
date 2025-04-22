package com.example.medilock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    // Constructor used by the app
    public Database(Context context) {
        super(context, "medilock", null, 1);
    }

    // Optional full constructor (not used)
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users table
        String qry1 = "CREATE TABLE users(username TEXT, email TEXT, password TEXT)";
        db.execSQL(qry1);

        // Profiles table
        String qry2 = "CREATE TABLE profiles(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "gender TEXT, height TEXT, weight TEXT, " +
                "blood_type TEXT, diabetes_status TEXT, blood_pressure TEXT, asthma TEXT)";
        db.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Optional
    }

    // Registration
    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    // Login
    public int login(String username, String password) {
        int result = 0;
        String[] str = new String[]{username, password};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        c.close();
        db.close();
        return result;
    }

    // Get user by rowid (SQLite uses rowid if no primary key specified)
    public UserModel getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid, username, email, password FROM users WHERE rowid = ?", new String[]{String.valueOf(id)});
        UserModel user = new UserModel();
        if (cursor.moveToFirst()) {
            user.id = cursor.getInt(0);
            user.username = cursor.getString(1);
            user.email = cursor.getString(2);
            user.password = cursor.getString(3);
        }
        cursor.close();
        db.close();
        return user;
    }

    // Update user
    public void updateUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user.username);
        cv.put("email", user.email);
        cv.put("password", user.password);
        db.update("users", cv, "rowid = ?", new String[]{String.valueOf(user.id)});
        db.close();
    }

    // Get profile
    public ProfileModel getProfile(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM profiles WHERE id = ?", new String[]{String.valueOf(id)});
        ProfileModel profile = new ProfileModel();
        if (cursor.moveToFirst()) {
            profile.id = cursor.getInt(0);
            profile.gender = cursor.getString(1);
            profile.height = cursor.getString(2);
            profile.weight = cursor.getString(3);
            profile.blood_type = cursor.getString(4);
            profile.diabetes_status = cursor.getString(5);
            profile.blood_pressure = cursor.getString(6);
            profile.asthma = cursor.getString(7);
        }
        cursor.close();
        db.close();
        return profile;
    }

    // Update profile
    public void updateProfile(ProfileModel profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("gender", profile.gender);
        cv.put("height", profile.height);
        cv.put("weight", profile.weight);
        cv.put("blood_type", profile.blood_type);
        cv.put("diabetes_status", profile.diabetes_status);
        cv.put("blood_pressure", profile.blood_pressure);
        cv.put("asthma", profile.asthma);
        db.update("profiles", cv, "id = ?", new String[]{String.valueOf(profile.id)});
        db.close();
    }
}
