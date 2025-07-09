package com.example.sipus;

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

    public class tentang_buku1MetodePenelitianKuantitatif extends AppCompatActivity {

        private Button btn_pinjamkan;
        private ImageView kembali1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_tentang_buku1_metode_penelitian_kuantitatif);

            // Mengatur padding otomatis agar tidak bentrok dengan status bar/navigation bar
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            // Inisialisasi tombol pinjamkan
            btn_pinjamkan = findViewById(R.id.btn_pinjamkan);
            btn_pinjamkan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Mengarahkan ke halaman TerimaKasih
                    Intent intent = new Intent(tentang_buku1MetodePenelitianKuantitatif.this, TerimaKasih.class);
                    startActivity(intent);
                    finish(); // Opsional: supaya tidak bisa kembali ke halaman buku dengan tombol back
                }
            });

            // Inisialisasi tombol kembali
            kembali1 = findViewById(R.id.kembali1);
            kembali1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Mengarahkan ke halaman Beranda
                    Intent intent = new Intent(tentang_buku1MetodePenelitianKuantitatif.this, Beranda.class);
                    startActivity(intent);
                    finish(); // Opsional
                }
            });
        }
    }