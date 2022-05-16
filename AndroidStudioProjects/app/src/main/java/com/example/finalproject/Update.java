package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {
    private DBcontacts db;
    private EditText name;
    private EditText phone;
    private EditText location;
    private EditText password;
    private EditText postcode;
    private Button update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name = (EditText) findViewById(R.id.update_name);
        db = new DBcontacts(this);
        phone = (EditText) findViewById(R.id.update_phone);
        password=(EditText)findViewById(R.id.password_update);
        location = (EditText) findViewById(R.id.update_location);
        postcode = (EditText) findViewById(R.id.update_postcode);
        update_btn = (Button) findViewById(R.id.update_button);
        update_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!password.getText().toString().equals(MainActivity.password)){
                    Comment.message(getApplicationContext(),"Wrong password try again if you are the admin!");
                    password.setText("");
                    return;
                }
                check_to_update();
            }
        });
    }
    private void check_to_update(){
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
                    long id = db.updateContact(x);
                    if (id <= 0) {
                        Comment.message(getApplicationContext(), "Update has failed");
                        name.setText("");
                        phone.setText("");
                        location.setText("");
                        postcode.setText("");
                    } else {
                        Comment.message(getApplicationContext(), "Update has been succeeded");
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