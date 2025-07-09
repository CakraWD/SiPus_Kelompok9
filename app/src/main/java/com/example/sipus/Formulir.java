package com.example.sipus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Formulir extends AppCompatActivity {
    private EditText isi_nama, isi_id, isi_email, isi_no_telepon, isi_tgl_pinjam, isi_tgl_kembali;
    private Button btn_konfirmasi;
    private ImageView kembali4;
    private DataBaseHelperSipus dbHelper;
    private String emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir);

        // Inisialisasi komponen
        isi_nama = findViewById(R.id.isi_nama);
        isi_id = findViewById(R.id.isi_id);
        isi_email = findViewById(R.id.isi_email);
        isi_no_telepon = findViewById(R.id.isi_no_telepon);
        isi_tgl_pinjam = findViewById(R.id.isi_tgl_pinjam);
        isi_tgl_kembali = findViewById(R.id.isi_tgl_kembali);
        btn_konfirmasi = findViewById(R.id.btn_konfirmasi);
        kembali4 = findViewById(R.id.kembali4);

        dbHelper = new DataBaseHelperSipus(this);

        // Ambil email user dari SharedPreferences
        SharedPreferences sp = getSharedPreferences("user_session", MODE_PRIVATE);
        emailUser = sp.getString("email", null);

        if (emailUser != null) {
            // Ambil data user dari database
            Cursor user = dbHelper.getUserByEmail(emailUser);
            if (user != null && user.moveToFirst()) {
                isi_nama.setText(user.getString(user.getColumnIndexOrThrow("nama")));
                isi_email.setText(user.getString(user.getColumnIndexOrThrow("email")));
                isi_no_telepon.setText(user.getString(user.getColumnIndexOrThrow("telepon")));
                user.close();
            }

            // Ambil data pinjaman jika ada
            Cursor pinjaman = dbHelper.getPinjamanByEmail(emailUser);
            if (pinjaman != null && pinjaman.moveToFirst()) {
                isi_id.setText(pinjaman.getString(pinjaman.getColumnIndexOrThrow("id_anggota")));
                isi_tgl_pinjam.setText(pinjaman.getString(pinjaman.getColumnIndexOrThrow("tanggal_pinjam")));
                isi_tgl_kembali.setText(pinjaman.getString(pinjaman.getColumnIndexOrThrow("tanggal_kembali")));
                pinjaman.close();
            }
        }

        // Tombol konfirmasi ditekan
        btn_konfirmasi.setOnClickListener(v -> {
            String idAnggota = isi_id.getText().toString().trim();
            String tglPinjam = isi_tgl_pinjam.getText().toString().trim();
            String tglKembali = isi_tgl_kembali.getText().toString().trim();

            if (idAnggota.isEmpty() || tglPinjam.isEmpty() || tglKembali.isEmpty()) {
                Toast.makeText(Formulir.this, "Lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = dbHelper.insertOrUpdatePinjaman(emailUser, idAnggota, tglPinjam, tglKembali);
            if (result) {
                Toast.makeText(Formulir.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Formulir.this, TerimaKasih2.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(Formulir.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
            }
        });

        // Tombol kembali
        kembali4.setOnClickListener(v -> finish());
    }
}