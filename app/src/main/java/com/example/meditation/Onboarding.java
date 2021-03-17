package com.example.meditation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Onboarding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
    }

    public void login(View view) {
        Intent login = new Intent(Onboarding.this,MainActivity.class);
        startActivity(login);
    }

    public void reg(View view) {
        Intent reg = new Intent(Onboarding.this,RegActivity.class);
        startActivity(reg);
    }
}