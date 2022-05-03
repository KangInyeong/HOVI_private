package com.example.hovi_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SignupActivity extends AppCompatActivity {

    ArrayList createAccountInputsArray = new ArrayList<EditText>();
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText editEmail = (EditText) findViewById(R.id.editEmail);
        EditText editPw = (EditText) findViewById(R.id.editPw);
        EditText editPw2 = (EditText) findViewById(R.id.editPw2);
        EditText editName = (EditText) findViewById(R.id.editName);

        createAccountInputsArray.add(editEmail);
        createAccountInputsArray.add(editPw);
        createAccountInputsArray.add(editPw2);

        Button btnSignup = (Button) findViewById(R.id.btnSignup);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = editName.getText().toString().trim();
//                Intent intent = new Intent(SignupActivity.this, Mainactivity.class);
//                intent.putExtra("usernaem",userName);
//                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                Toast.makeText(getApplicationContext(), "please sign into your account", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
