package com.example.sipus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sipus.databinding.ActivityHalamanLoginBinding;

public class halaman_login extends AppCompatActivity {
    ActivityHalamanLoginBinding binding;
    DataBaseHelperSipus databaseHelper;
    private TextView text_8;
    private Button btn_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_halaman_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        binding = ActivityHalamanLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DataBaseHelperSipus(this);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                if (email.equals("") || password.equals(""))
                    Toast.makeText(halaman_login.this, "Kolom wajib diisi", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                    if (checkCredentials == true){
                        // ✅ SIMPAN EMAIL KE SHARED PREFERENCES
                        getSharedPreferences("user_session", MODE_PRIVATE)
                                .edit()
                                .putString("email", email)
                                .apply();

                        Toast.makeText(halaman_login.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(halaman_login.this, Beranda.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(halaman_login.this, "Password tidak sesuai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        text_8 = findViewById(R.id.text_8);
        text_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(halaman_login.this, Register.class);
                startActivity(i);
            }
        });
    }
}