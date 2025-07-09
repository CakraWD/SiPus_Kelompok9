package com.example.sipus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class tentang_buku3PesonaWisataBiak extends AppCompatActivity {

    private Button btn_pinjam2;
    private ImageView kembali3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tentang_buku3_pesona_wisata_biak);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tombol untuk meminjam buku → langsung ke halaman Terima Kasih
        btn_pinjam2 = findViewById(R.id.btn_pinjam2);
        btn_pinjam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tentang_buku3PesonaWisataBiak.this, TerimaKasih.class);
                startActivity(intent);
            }
        });

        // Tombol kembali → ke halaman Beranda
        kembali3 = findViewById(R.id.kembali3);
        kembali3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tentang_buku3PesonaWisataBiak.this, Beranda.class);
                startActivity(intent);
            }
        });
    }
}