package com.example.sipus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class linimasa extends AppCompatActivity {
    private ImageView home, sistem_pengaturan, hapus, edit;
    private TextView txtNama, txtTelepon;

    DataBaseHelperSipus dbHelper;
    String emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linimasa);

        txtNama = findViewById(R.id.txtNama);
        txtTelepon = findViewById(R.id.txtTelepon);
        home = findViewById(R.id.home);
        sistem_pengaturan = findViewById(R.id.sistem_pengaturan);
        hapus = findViewById(R.id.hapus);
        edit = findViewById(R.id.tempat_edit);

        dbHelper = new DataBaseHelperSipus(this);

        SharedPreferences sp = getSharedPreferences("user_session", MODE_PRIVATE);
        emailUser = sp.getString("email", null);

        if (emailUser != null) {
            Cursor user = dbHelper.getUserByEmail(emailUser);
            if (user.moveToFirst()) {
                txtNama.setText("Nama: " + user.getString(user.getColumnIndexOrThrow("nama")));
                txtTelepon.setText("Telepon: " + user.getString(user.getColumnIndexOrThrow("telepon")));
            }
        }

        edit.setOnClickListener(v -> {
            Intent intent = new Intent(linimasa.this, Formulir.class);
            startActivity(intent);
        });

        hapus.setOnClickListener(v -> {
            boolean deleted = dbHelper.deletePinjamanByEmail(emailUser);
            if (deleted) {
                Toast.makeText(this, "Data pinjaman dihapus", Toast.LENGTH_SHORT).show();
                txtNama.setText("Nama: ");
                txtTelepon.setText("Telepon: ");
            } else {
                Toast.makeText(this, "Tidak ada data untuk dihapus", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(v -> {
            startActivity(new Intent(linimasa.this, Beranda.class));
        });

        sistem_pengaturan.setOnClickListener(v -> {
            startActivity(new Intent(linimasa.this, akun_profil.class));
        });
    }
}