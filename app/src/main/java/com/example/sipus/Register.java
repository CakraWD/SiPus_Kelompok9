package com.example.sipus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.sipus.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    DataBaseHelperSipus databaseHelper;
    private TextView text_21;
    ImageView arrow2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper= new DataBaseHelperSipus(this);
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= binding.edtEmailsignup.getText().toString();
                String password = binding.edtPass.getText().toString();
                String confirmPassword = binding.edtKonfirpass.getText().toString();

                if (email.equals("") || password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(Register.this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if (checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email, password);

                            if (insert == true){
                                Toast.makeText(Register.this, "Daftar akun sukses", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), halaman_login.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(Register.this, "Daftar akun gagal", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this, "Akun sudah ada coba ulangi", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Register.this, "Password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        text_21 = findViewById(R.id.text_21);
        text_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent text_21 = new Intent(Register.this, halaman_login.class);
                startActivity(text_21);
            }
        });
        arrow2 = findViewById(R.id.arrow2);
        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Register.this, halaman_login.class);
                startActivity(i);
            }
        });
    }
}