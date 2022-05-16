package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add extends AppCompatActivity {
    private DBcontacts db;
    private EditText name;
    private EditText phone;
    private EditText location;
    private EditText postcode;
    private Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = (EditText) findViewById(R.id.name);
        db = new DBcontacts(this);
        phone = (EditText) findViewById(R.id.phone);
        location = (EditText) findViewById(R.id.location);
        postcode = (EditText) findViewById(R.id.postcode);
        add_btn = (Button) findViewById(R.id.add_button);
        add_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                check_to_add();
            }
        });
    }
    private void check_to_add(){
        String t1 = null;
        String t2=null;
        String t3=null;
        int t4;
        String t5=null;
        try {
             t1 = name.getText().toString();
             t2 = phone.getText().toString();
             t3 = location.getText().toString();
             t5 = postcode.getText().toString();
             t4 = Integer.parseInt(postcode.getText().toString());
            if (t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t5.isEmpty()) {
                Comment.message(getApplicationContext(), "One of the above is missing");
            } else {
                if((t2.startsWith("050-")||t2.startsWith("052-")||t2.startsWith("055-")||t2.startsWith("053-")||t2.startsWith("058-"))&&t2.length()==11) {
                    String arr[]=t2.split("-",2);
                    if(arr[1].length()!=7){
                        Comment.message(getApplicationContext(),"Number is not a person number");
                        name.setText("");
                        phone.setText("");
                        location.setText("");
                        postcode.setText("");
                        return;
                    }
                    Contact x = new Contact(t1, t2, t3, t4);
                    long id = db.addContact(x);
                    if (id <= 0) {
                        Comment.message(getApplicationContext(), "Addition failed");
                        name.setText("");
                        phone.setText("");
                        location.setText("");
                        postcode.setText("");
                    } else {
                        Comment.message(getApplicationContext(), "Addition has been succeeded");
                        name.setText("");
                        phone.setText("");
                        location.setText("");
                        postcode.setText("");
                    }
                }
                else{
                    Comment.message(getApplicationContext(),"Number is not a person number");
                    name.setText("");
                    phone.setText("");
                    location.setText("");
                    postcode.setText("");
                }
            }
        }
        catch (NumberFormatException e){
            if (t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t5.isEmpty()) {
                Comment.message(getApplicationContext(), "One of the above is missing");
            }
            else {
                Comment.message(getApplicationContext(), "Post code need to be only digits");
                name.setText("");
                phone.setText("");
                location.setText("");
                postcode.setText("");
            }
        }
    }
}