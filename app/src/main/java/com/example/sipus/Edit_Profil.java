package com.example.sipus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;


public class Edit_Profil extends AppCompatActivity {

    EditText etNama, etEmail, etTelepon, etAlamat;
    Spinner spinnerJenisKelamin;
    Button btnBack;
    Button btnKonfirmasi;

    DataBaseHelperSipus dbHelper;
    String emailUser; // email sebagai ID user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        // Inisialisasi View
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etTelepon = findViewById(R.id.etTelepon);
        etAlamat = findViewById(R.id.etAlamat);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        btnKonfirmasi = findViewById(R.id.btnKonfirmasi);

        // Inisialisasi database
        dbHelper = new DataBaseHelperSipus(this);

        // Inisialisasi spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jenis_kelamin_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(adapter);

        // Ambil email user yang login dari Intent
        emailUser = getIntent().getStringExtra("email");

        // Ambil data user dan tampilkan
        Cursor cursor = dbHelper.getUserByEmail(emailUser);
        if (cursor != null && cursor.moveToFirst()) {
            etNama.setText(cursor.getString(cursor.getColumnIndexOrThrow("nama")));
            etEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow("email"))); // readonly
            etTelepon.setText(cursor.getString(cursor.getColumnIndexOrThrow("telepon")));
            etAlamat.setText(cursor.getString(cursor.getColumnIndexOrThrow("alamat")));
            String jenisKelaminDb = cursor.getString(cursor.getColumnIndexOrThrow("jenis_kelamin"));
            if (jenisKelaminDb != null) {
                int position = adapter.getPosition(jenisKelaminDb);
                spinnerJenisKelamin.setSelection(position);
            }
        }

        // Saat tombol diklik → update data
        btnKonfirmasi.setOnClickListener(v -> {
            boolean updated = dbHelper.updateUserProfile(
                    emailUser,
                    etNama.getText().toString(),
                    etTelepon.getText().toString(),
                    etAlamat.getText().toString(),
                    spinnerJenisKelamin.getSelectedItem().toString()
            );

            if (updated) {
                Toast.makeText(this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();
                finish(); // kembali ke halaman sebelumnya
            } else {
                Toast.makeText(this, "Gagal memperbarui profil", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

