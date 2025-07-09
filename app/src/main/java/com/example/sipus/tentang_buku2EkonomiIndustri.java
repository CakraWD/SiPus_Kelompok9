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

public class tentang_buku2EkonomiIndustri extends AppCompatActivity {

    private Button btn_pinjam1;
    private ImageView kembali2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tentang_buku2_ekonomi_industri);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi tombol pinjam
        btn_pinjam1 = findViewById(R.id.btn_pinjam1);
        btn_pinjam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tentang_buku2EkonomiIndustri.this, TerimaKasih.class);
                startActivity(intent);
            }
        });

        // Inisialisasi tombol kembali
        kembali2 = findViewById(R.id.kembali2);
        kembali2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tentang_buku2EkonomiIndustri.this, Beranda.class);
                startActivity(intent);
            }
        });
    }
}