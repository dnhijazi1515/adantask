package com.example.adanhijazicorrect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {
    EditText user, pass;
    Button buttonSignUp, buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user = findViewById(R.id.u_name);
        pass = findViewById(R.id.pass);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = user.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if (!name.isEmpty() && !password.isEmpty()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.putString("password", password);
                    editor.apply();

                    Toast.makeText(MainActivity2.this, "تم التسجيل بنجاح ✅", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "الرجاء إدخال البريد وكلمة المرور", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String savedName = sharedPreferences.getString("name", null);
                String savedPass = sharedPreferences.getString("password", null);
                String inputName = user.getText().toString().trim();
                String inputPassword = pass.getText().toString().trim();
                if (savedName != null && savedPass != null) {
                    if (savedName.equals(inputName) && savedPass.equals(inputPassword)) {
                        Toast.makeText(MainActivity2.this, "تسجيل الدخول ناجح 🎉", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                        intent.putExtra("loggedUser", savedName);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity2.this, "الإيميل أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "لا يوجد حساب مسجل، الرجاء التسجيل أولاً", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BottomNavigationView btnav = findViewById(R.id.btnav);
        btnav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent intent = null;

            if (id == R.id.main) {
                intent = new Intent(this, MainActivity.class);
            } else if (id == R.id.login) {
                intent = new Intent(this, MainActivity2.class);
            } else if (id == R.id.dash) {
                intent = new Intent(this, MainActivity3.class);
            }
            if (intent != null) {
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}