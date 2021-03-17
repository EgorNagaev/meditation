package com.example.meditation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.meditation.Network.AuthLog;
import com.example.meditation.Network.MyRetrofit;
import com.example.meditation.Network.RetrofitCalls;
import com.example.meditation.Network.Tokens;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
EditText email,password;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        if(sharedPreferences.getString("token",null)!=null) {
            Intent log = new Intent(MainActivity.this, Menu.class);
            startActivity(log);
        }
        else {
            setContentView(R.layout.activity_main);
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);
        }

    }

    public void signup(View view) {
        Intent signup = new Intent(MainActivity.this,RegActivity.class);
        startActivity(signup);

    }

    public void signin(View view) {

        if (!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
            Retrofit loging = MyRetrofit.getretofit();
            RetrofitCalls loginadapt  = loging.create(RetrofitCalls.class);
            HashMap<String,String> has = new HashMap<>();
            has.put("email",email.getText().toString());
            has.put("password",password.getText().toString());

            Call<Tokens> lcall = loginadapt.login(has);
            lcall.enqueue(new Callback<Tokens>() {
                @Override
                public void onResponse(Call<Tokens> call, Response<Tokens> response) {
                    if (response.body() != null)
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", response.body().getToken());
                        editor.putString("name",response.body().getNickName());
                        editor.putString("avatar",response.body().getAvatar());
                        editor.apply();
                        Intent log = new Intent(MainActivity.this,Menu.class);
                        startActivity(log);
                    }
                    else {
//                        AlertDialog.Builder logs = new AlertDialog.Builder(getApplicationContext());
//                        logs.setTitle("Ошибка входа");
//                        logs.setNeutralButton("Окей",null);
//                        logs.create().show();
                    }
                }

                @Override
                public void onFailure(Call<Tokens> call, Throwable t) {
                    Log.d("app",t.getMessage());
                }
            });
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Ошибка входа");
            dialog.setMessage("Возможно у вас есть пустые поля \nПроверьте логин и пароль на правильность заполнения");
            dialog.setNeutralButton("Окей", null);
            dialog.create().show();
        }
    }
}