package com.example.sipus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Beranda extends AppCompatActivity {
    private ImageView linimasa;
    private ImageView sistem_pengaturan;
    private ImageView buku1, buku2, buku3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beranda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sistem_pengaturan=findViewById(R.id.sistem_pengaturan);
        sistem_pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Beranda.this, akun_profil.class);
                startActivity(i);
            }
        });
        linimasa=findViewById(R.id.linimasa);
        linimasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Beranda.this, linimasa.class);
                startActivity(i);
            }
        });
        buku1=findViewById(R.id.buku1);
        buku1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Beranda.this, tentang_buku1MetodePenelitianKuantitatif.class);
                startActivity(i);
            }
        });
        buku2=findViewById(R.id.buku2);
        buku2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Beranda.this, tentang_buku2EkonomiIndustri.class);
                startActivity(i);
            }
        });
        buku3=findViewById(R.id.buku3);
        buku3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Beranda.this, tentang_buku3PesonaWisataBiak.class);
                startActivity(i);
            }
        });
    }
}