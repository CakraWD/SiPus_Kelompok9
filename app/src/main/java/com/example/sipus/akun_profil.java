package com.example.sipus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class akun_profil extends AppCompatActivity {
    private ImageView edit_profil;
    private ImageView SyaratKetentuan;
    private ImageView btn_keluar;
    private ImageView linimasa;
    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_akun_profil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit_profil = findViewById(R.id.edit_profil);
        edit_profil.setOnClickListener(v -> {
            // Ambil email dari SharedPreferences (atau tempat lain kamu menyimpannya)
            SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
            String emailUser = prefs.getString("email", null);

            if (emailUser != null) {
                Intent i = new Intent(akun_profil.this, Edit_Profil.class);
                i.putExtra("email", emailUser); // Kirim email
                startActivity(i);
            } else {
                Toast.makeText(akun_profil.this, "Email tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
        });

        SyaratKetentuan=findViewById(R.id.SyaratKetentuan);
        SyaratKetentuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(akun_profil.this, Syarat_dan_Ketentuan.class);
                startActivity(i);
            }
        });
        btn_keluar=findViewById(R.id.btn_keluar);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(akun_profil.this, halaman_login.class);
                startActivity(i);
            }
        });
        linimasa=findViewById(R.id.linimasa);
        linimasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(akun_profil.this, linimasa.class);
                startActivity(i);
            }
        });
        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(akun_profil.this, Beranda.class);
                startActivity(i);
            }
        });
    }
}