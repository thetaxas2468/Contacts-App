package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {
    private DBcontacts db;
    private TextView view;
    private EditText name;
    private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db = new DBcontacts(this);
        view=(TextView) findViewById(R.id.SearchInfo);
        search=(Button) findViewById(R.id.search_button);
        name=(EditText) findViewById(R.id.name_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result=db.searchContacts(name.getText().toString());
                view.setText(result);
                if(result.isEmpty()){
                    view.setText("There is no contacts with that name");
                }
                name.setText("");
            }
        });
    }
}
