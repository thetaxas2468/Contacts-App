package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button viewall;
    private Button add;
    private Button update;
    private Button delete;
    private Button search;
    public static final String password="172";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewall=(Button)findViewById(R.id.View);
        add=(Button)findViewById(R.id.Add);
        delete=(Button)findViewById(R.id.Delete);
        update=(Button)findViewById(R.id.Update);
        search=(Button)findViewById(R.id.Search);
        viewall.setOnClickListener(this);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        search.setOnClickListener(this);
    }
    public void onClick(View v){
//        dont forget break at the end;
        switch (v.getId()){
            case R.id.Add:
                Intent main1 = new Intent(this,Add.class);
                startActivity(main1);
                break;

            case R.id.Delete:
                Intent main2 = new Intent(this,Delete.class);
                startActivity(main2);
                break;

            case R.id.View:
                Intent main3 = new Intent(this,Viewall.class);
                startActivity(main3);
                break;
            case R.id.Update:
                Intent main4 = new Intent(this,Update.class);
                startActivity(main4);
                break;
            case R.id.Search:
                Intent main5 = new Intent(this,Search.class);
                startActivity(main5);
                break;

        }
    }
}