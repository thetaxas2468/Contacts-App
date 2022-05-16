package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Delete extends AppCompatActivity {
    private DBcontacts db;
    private EditText name;
    private EditText password;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        name=(EditText) findViewById(R.id.postcode_delete);
        db=new DBcontacts(this);
        password=(EditText)findViewById(R.id.password_delete);
        btn=(Button) findViewById(R.id.delete_button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int postcode;
                if(!password.getText().toString().equals(MainActivity.password)){
                    Comment.message(getApplicationContext(),"Wrong password try again if you are the admin!");
                    password.setText("");
                    return;
                }
                try{
                    postcode=Integer.parseInt(name.getText().toString());
                    int count=db.deleteContact(postcode);
                    if (count<=0){
                        Comment.message(getApplicationContext(),"Deletion has failed please try again with correct one");
                        name.setText("");
                    }
                    else{
                        Comment.message(getApplicationContext(),"Deletion has succeeded");
                        name.setText("");
                    }
                }
                catch(NumberFormatException e){
                    Comment.message(getApplicationContext(),"Incorrect post code");
                    name.setText("");
                }
            }
        });
    }
}