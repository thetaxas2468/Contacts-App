package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Viewall extends AppCompatActivity {
    private DBcontacts db;
    private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        db = new DBcontacts(this);
        view=(TextView) findViewById(R.id.AllInfo);
        String result=db.getAllContacts();
        view.setText(result);
    }
}