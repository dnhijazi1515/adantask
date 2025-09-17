package com.example.adanhijazicorrect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity3 extends AppCompatActivity {
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        name=findViewById(R.id.nameu);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", "user");
        String loggedUser = getIntent().getStringExtra("loggedUser");
        if (loggedUser != null) {
            name.setText("Hello " + loggedUser);
        } else {
            name.setText("Hello user");
        }
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
    public void out(View view){
        name.setText("Hello user");
    }
    public void ext(View view){
        finishAffinity();
    }
}